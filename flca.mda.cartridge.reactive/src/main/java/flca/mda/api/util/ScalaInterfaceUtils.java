package flca.mda.api.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import mda.annotation.RestService;
import mda.annotation.crud.CrudOperation;
import mda.annotation.crud.Search;
import mda.type.IDtoType;
import mda.type.IEntityType;
import reactive.Tid;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.helpers.StrUtil;
import flca.mda.common.api.helpers.ImportHelper;

public class ScalaInterfaceUtils extends InterfaceUtils {
	private ScalaTypeUtils tu = new ScalaTypeUtils();
	// private NameUtils nu = new NameUtils();

	@Override
	public boolean moreParameters(Method aMethod, int aIndex) {
		int cnt = getParameterCount(aMethod);
		return (aIndex < cnt - 1);
	}


	/**
	 * @param aField
	 * @return
	 */
	public Properties getSubsWithsForCrudOper(CrudMethod aCrudMethod, boolean aForFlex) {
		Class<?> crudent = aCrudMethod.getEntity();

		String entityName = tplu.getClassName(crudent, Tid.SCALA_ENTITY.name());
		String pktype = tu.getIdTypeName(crudent);
		// String validator = tplu.getClassName(crudent,
		// Tid.ENTITY_VALIDATOR.name());
		String dao = tplu.getClassName(crudent, Tid.SCALA_ENTITY_DAO.name());
		// String daoImpl = tplu.getClassName(crudent,
		// Tid.ENTITY_DAO_IMPL.name());
		String daosrvProp = "dao" + tplu.getClassName(crudent, Tid.SCALA_ENTITY.name());
		String mock = tplu.getClassName(crudent, Tid.SCALA_ENTITY_MOCK.name());
		String searchArgsDto = getSearchArgsDtoName(crudent);

		Properties result = new Properties();
		result.put("entity", entityName);
		result.put("pkType", pktype);
		// result.put("validator", validator);
		result.put("dao", dao);
		// result.put("daoImpl", daoImpl);
		result.put("daosrvProp", daosrvProp);
		result.put("mock", mock);
		result.put("searchDto", searchArgsDto);

		return result;
	}

	/**
	 * return boolean to indicate if current class is of ttype IEntityType
	 * 
	 * @return
	 */
	public boolean isEntity() {
		Class<?> currcls = JetArgument.getCurrent().getElementClass();
		return isEntity(currcls);
	}

	/**
	 * return boolean to indicate if current class is of ttype IDtoType
	 * 
	 * @return
	 */
	public boolean isDto() {
		Class<?> currcls = JetArgument.getCurrent().getElementClass();
		return isDto(currcls);
	}

	/**
	 * return boolean to indicate if current class is of ttype IEntityType
	 * 
	 * @return
	 */
	public boolean isEntity(Class<?> aClass) {
		return (tu.hasType(aClass, IEntityType.class));
	}

	/**
	 * IDtoType return boolean to indicate if current class is of ttype IDtoType
	 * 
	 * @return
	 */
	public boolean isDto(Class<?> aClass) {
		return (tu.hasType(aClass, IDtoType.class));
	}

	@Override
	public Map<String, Class<?>> getOperationMap() {
		if (sOperationMap == null) {
			sOperationMap = new HashMap<String, Class<?>>();

			super.fillOperationMap();
			fillOperationMapWithCrudOperations();
		}

		return sOperationMap;
	}

	private void fillOperationMapWithCrudOperations() {
		for (Class<?> entityClz : DataStore.getInstance().getModelClasses()) {
			if (!entityClz.isInterface()) {
				if (hasCrudOperation(entityClz)) {
					RestService crud = (RestService) tu.getAnnotation(entityClz, RestService.class);

					if (crud != null) {
						for (CrudOperation crudoper : getCrudOperations(crud)) {
							for (String prefix : crudoper.methodPrefix()) {
								String operName = prefix + entityClz.getSimpleName();
								sOperationMap.put(operName, entityClz);
							}
						}
					}
				}

			}
		}
	}

	public String getSearchArgsDtoName(Class<?> crudent) {
		String result = "";

		// hmm, can we improve this?
		result = "Search" + crudent.getSimpleName() + "Dto";

		if (tu.hasAnnotation(crudent, Search.class)) {
			String pck = StrUtil.getPackage(crudent.getName());
			ImportHelper.addImport(pck + "." + result);
		}

		return result;
	}

	@Override
	public List<ParameterData> getParametersViaReflection(Method aMethod) {
		List<ParameterData> result = new ArrayList<>();
		Class<?> params[] = aMethod.getParameterTypes();
		for (int i = 0; i < params.length; i++) {
			result.add(new ParameterData("arg" + i, params[i].getName()));
		}
		return result;
	}

	@Override
	public List<ParameterData> parseParametersString(String aArgsString) {
		List<ParameterData> result = new ArrayList<>();

		if (aArgsString != null && aArgsString.trim().length() > 0) {
			String items[] = aArgsString.split(",");
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					String tokens[] = items[i].trim().split(" ");
					if (tokens.length == 2) {
						result.add(new ParameterData(tokens[1], tokens[0]));
					} else {
						logger.error("this should not be possible in getArgumentsFromSourcecode " + aArgsString);
					}
				}
			}
		}

		return result;
	}

	@Override
	public String getReturn(Method aMethod) {
		Class<?> rettyp = aMethod.getReturnType();
		Type t = aMethod.getGenericReturnType();
		if (t instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) t;
			Object obj = pt.getActualTypeArguments()[0];
			if (obj instanceof Class) {
				ImportHelper.addImport((Class<?>) pt.getRawType());
				ImportHelper.addImport((Class<?>) obj);
				return rettyp.getSimpleName() + "[" + ((Class<?>) obj).getSimpleName() + "]";
			} else {
				return "???";
			}
		} else {
			if (rettyp.equals(Void.class) || rettyp.equals(void.class)) {
				return "Unit";
			} else {
				ImportHelper.addImport(rettyp);
				return rettyp.getSimpleName();
			}
		}
	}

	/**
	 * Returns all argument names as a formatted String
	 * 
	 * <pre>
	 * void doIt(String aaa, int bbb) results in "String aaa, int bbb"
	 * </pre>
	 * 
	 * @param aMethod
	 * @return
	 */
	@Override
	public String getParameters(Method aMethod) {
		StringBuffer sb = new StringBuffer();

		String src = getSourceCode();
		List<ParameterData> paramData = (src == null) ? getParametersViaReflection(aMethod) : getParametersFromSourcecode(aMethod, src);

		int n = 0;
		for (ParameterData parameterData : paramData) {
			sb.append(parameterData.name + ":" + parameterData.datatype);
			if (n++ < paramData.size() - 1)
				sb.append(",  ");
		}

		return sb.toString();
	}

	public Class<?> getServiceClass(Class<?> aEntity) {
		// TODO try to make this more generic
		String findoper = "find" + aEntity.getSimpleName();
		return getServiceClass(findoper);
	}
}
