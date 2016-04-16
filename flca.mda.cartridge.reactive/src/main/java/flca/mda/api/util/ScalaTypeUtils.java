package flca.mda.api.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipFile;

import mda.annotation.RestService;
import mda.annotation.jpa.DiscriminatorColumn;
import mda.annotation.scala.Val;
import mda.type.IEntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactive.Tid;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.helpers.AnnotationsHelper;
import flca.mda.codegen.helpers.ShellUtils;
import flca.mda.codegen.helpers.ZipFileHelper;
import flca.mda.common.api.helpers.ImportHelper;

public class ScalaTypeUtils extends ReactiveTypeUtils implements TypeConstants, ScalaTypeConstants {

	public static Logger logger = LoggerFactory.getLogger(ScalaTypeUtils.class);

	/**
	 * return all parameter names in a comma seperated string, of the given
	 * class, that will be used for Row classes
	 * 
	 * @return String
	 */
	public String getRowCaseClassParams(Class<?> aClass) {
		return th.getRowCaseClassParams(aClass);
	}

	@Override
	protected Fw makeFw(Field fld, Class<?> ownerClass) {
		return new ScalaFw(fld, ownerClass);
	}
	
	@Override
	protected Fw makeFw(SpecialField fld, Class<?> ownerClass) {
		return new ScalaFw(fld, ownerClass);
	}
	

	@Override
	protected Fw makeIdFieldWrapper(Class<?> ownerClass) {
		return new ScalaIdFieldWrapper(ownerClass);
	}
	/**
	 * return all parameter names in a comma seperated string, of the given
	 * class, that will be used for Row copy method
	 * 
	 * @return String
	 */
	public String getRowCloneParams(Class<?> aClass) {
		return th.getRowCloneParams(aClass);
	}

	/**
	 * returns the name of the dicscriminator used for entity classes with inheritance.
	 * If looks for the @DiscriminatorColumn annotation. If not the found a (string) property 'discriminator' will be used.
	 * @param aClass
	 * @return
	 */
	public String getDiscriminatorName(Class<?> aClass) {
		String result = "discriminator";
		DiscriminatorColumn discAnno = (DiscriminatorColumn) getAnnotation(aClass, DiscriminatorColumn.class);
		if (discAnno != null && discAnno.name() != null) {
			result = discAnno.name();
		}
		return result;
	}

	public String getDiscriminatorColumnName(Class<?> aClass) {
		String result = "DISCRIMINATOR";
		DiscriminatorColumn discAnno = (DiscriminatorColumn) getAnnotation(aClass, DiscriminatorColumn.class);
		if (discAnno != null) {
			if (discAnno.columnName() != null) {
				result = discAnno.columnName();
			} else if (discAnno.name() != null) {
				result = discAnno.name().toUpperCase();
			}
		}
		return result;
	}

	/**
	 * returns the slick projection string to (un)map a sql query to a row ex:
	 * def * = (id, cname, a) <> (CRow.tupled, CRow.unapply)
	 * 
	 * @param aClass
	 * @return
	 */
	public String getRowProjectionParams(Class<?> aClass) {
		List<String> list = new ArrayList<String>();

		list.addAll(getRowProjectionParamsExclOneTo(aClass));
		list.addAll(getRowProjectionParamsOneToOne(aClass));
		if (hasInheritance(aClass)) {
			list.add(getDiscriminatorName(aClass));
		}

		return nu.join(list, "", ",", "");
	}

	private List<String> getRowProjectionParamsExclOneTo(Class<?> aClass) {
		List<String> result = new ArrayList<String>();
		for (Fw fieldwrapper : getFieldsExc(aClass, O2M_FLD, O2O_FLD, OFD_FLD, DISC_FLD)) {
			ScalaFw fw = (ScalaFw) fieldwrapper;
			StringBuffer sb = new StringBuffer();
			String fldname = fw.rowFieldname();
			sb.append(fldname);
			if (fw.isId()) {
				sb.append(".?");
			}
			result.add(sb.toString());
		}
		return result;
	}

	private List<String> getRowProjectionParamsOneToOne(Class<?> aClass) {
		List<String> result = new ArrayList<String>();
		for (Fw fieldwrapper : getFieldsInc(aClass, O2O_FLD)) {
			ScalaFw fw = (ScalaFw) fieldwrapper;
			String fldname = fw.rowFieldname();
			result.add(fldname);
		}
		return result;
	}


	/**
	 * This returns all the code to find out if a (nested) object should be
	 * fetched. ex: val fetchOrderOrderStatus = fd.isDefined &&
	 * fd.get.order.isDefined && fd.get.order.get.orderStatus.isDefined This code
	 * recursive loops through all (deeply) nested objects
	 * 
	 * @param aClass
	 * @return
	 */
	public String makeIsFetchDefinedStr(Class<?> aClass) {
		return th.makeIsFetchDefinedStr(aClass);
	}

	/**
	 * This returns all the code to retrieve all (deeply) nested object if needed
	 * ex: if (fetchUserPicture(fetchDepth)) user.picture =
	 * retrieveUserPicture(user) This code recursive loops through all (deeply)
	 * nested objects
	 * 
	 * @param aClass
	 * @return
	 */
	public String genRetrieveNestedObjectsStr(Class<?> aClass) {
		return th.genRetrieveNestedObjectsStr(aClass);
	}


	public String retrieveNestedObjDef(Class<?> clz, Fw fw, Class<?> aType) {
		return th.retrieveNestedObjDef(clz, fw, aType);
	}

	/**
	 * Return true if this field us marked with @Val or it is the Id field
	 * 
	 * @param fw
	 * @return boolean
	 */
	@Override
	public boolean isValType(Fw fw) {
		return AnnotationsHelper.hasAnnotation(fw, Val.class) || fw.isId();
	}

	/**
	 * Return true if this field us marked with @Val
	 * 
	 * @param fw
	 * @return boolean
	 */
	@Override
	public boolean isVarType(Fw fw) {
		return !isValType(fw);
	}


	/**
	 * Returns the Scala class name for the given Type. Class names will be added
	 * as imports to the GenModel's ImportManager, and the imported form will be
	 * returned.
	 */
	@Override
	public String getTypeName(Class<?> aClass, Class<?> aGenericType) {
		importIfNeeded(aClass, aGenericType);

		if (aClass.isPrimitive()) {
			return ScalaDataTypes.getTypeName(aClass);
		} else if (isCollection(aClass)) {
			if (aGenericType != null) {
				return ScalaDataTypes.getTypeName(aClass) + "[" + aGenericType.getSimpleName() + "]";
			} else {
				return ScalaDataTypes.getTypeName(aClass);
			}
		} else if (aClass.isEnum()) {
			return aClass.getSimpleName() + ENUM_VALUE;
		} else {
			return ScalaDataTypes.getTypeName(aClass);
		}
	}

	@Override
	public void importIfNeeded(Class<?> aClass, Type aGenericType) {
		if (!aClass.isPrimitive()) {
			ImportHelper.addImport(ScalaDataTypes.getImportString(aClass));
		}
		if (aGenericType != null && !aGenericType.equals(aClass)) {
			if (aGenericType instanceof ParameterizedType) {
				ParameterizedType typeimpl = (ParameterizedType) aGenericType;
				for (Type type : typeimpl.getActualTypeArguments()) {
					ImportHelper.addImport(ScalaDataTypes.getImportString(type.getClass()));
				}
			}
		}
	}

	/**
	 * This returns something like: fetchOrder:Some(FetchOrder),
	 * fetchPicture:Some(FetchPicture)
	 * 
	 * @param aClass
	 * @return
	 */
	public String getFetchModelParams(Class<?> aClass) {
		StringBuffer sb = new StringBuffer();

		List<Fw> fields = getFieldsInc(aClass, O2M_FLD, M2O_FLD, O2O_FLD);

		for (Fw fw : fields) {
			if (fw.isOwner()) {
				sb.append(fw.name() + ":");
				Class<?> clz = fw.genericType();
				ImportHelper.addImport(clz);
				String typename = clz.getSimpleName() + FD;
				ImportHelper.addScalaImport(typename, clz, Tid.SCALA_ENTITY.name());
				sb.append(OPTION + typename + "]");
				sb.append("=" + SOME + typename + "())");
				sb.append(", ");
			}
		}

		return nu.trimRightComma(sb.toString());
	}

	/**
	 * this return something like with OrderDao with OrderStatusDao
	 * 
	 * @return
	 */
	public String getProfileMixins() {
		StringBuffer sb = new StringBuffer();

		Set<Class<?>> allclz = new HashSet<>();

		for (Fw fw : getFieldsInc(currentClass(), REL_FLD)) {
			allclz.add(fw.genericType());
			Class<?> ownerMappedByClz = fw.getOwnerClassMappedByFkField();
			if (ownerMappedByClz != null) {
				allclz.add(ownerMappedByClz);
			}
		}

		for (NestedObject nested : getNestedObjects(currentClass()).getNestedObjects()) {
			allclz.add(nested.lastField().genericType());
		}

		for (Class<?> clz : allclz) {
			if (isEntity(clz)) {
				sb.append(" with ");
				String typename = tplu.getClassName(clz, Tid.SCALA_ENTITY_DAO.name());
				sb.append(typename);
			}
		}

		return sb.toString();
	}

	@Override
	/**
	 * Returns the primitive or class name for the given Type. Class names will
	 * be added as imports to the GenModel's ImportManager, and the imported
	 * form will be returned.
	 */
	public String getTypeName(Class<?> aClass) {
		if (!aClass.isPrimitive()) {
			ImportHelper.addImport(ScalaDataTypes.getImportString(aClass));
		}

		return ScalaDataTypes.getTypeName(aClass);
	}

	/**
	 * return a default Row value for the given FieldWrapper
	 * 
	 * @param fw
	 * @return
	 */
	public String getDefaultRowValue(Fw fw) {
		return th.getDefaultRowValue(fw);
	}

	@Override
	public String getIdTypeName(Class<?> aClass) {
		Class<?> pktyp = getIdType(aClass);
		return ScalaDataTypes.getTypeName(pktyp);
	}

	/**
	 * returns boolean to indicatie that this field needs to be surounden with an
	 * Option[]
	 * 
	 * @param fw
	 * @return
	 */
	@Override
	public boolean needsOption(Fw fw) {
		if (fw.isOneToManyField() || fw.isManyToManyField()) {
			return false;
		} else {
			return !fw.isRequired() || fw.isId();
		}
	}

	/**
	 * return all the entities as mixins ex:
	 * "with TelcoDao with OrderDao with OrderStatusDao "
	 * 
	 * @return
	 */
	public String getAllDaoMixins() {
		StringBuffer sb = new StringBuffer();
		List<Class<?>> entities = ModelClassesUtils.findModelClassesWithInterface(IEntityType.class);
		for (Class<?> entity : entities) {
			String entityDao = tplu.getClassName(entity, Tid.SCALA_ENTITY_DAO.name());
			sb.append(" with " + entityDao);
		}
		return sb.toString();
	}

	/**
	 * return all the entities as mixins ex:
	 * "with TelcoDao with OrderDao with OrderStatusDao "
	 * 
	 * @return
	 */
	public String getAllDaoQueries() {
		StringBuffer sb = new StringBuffer();
		List<Class<?>> entities = ModelClassesUtils.findModelClassesWithInterface(IEntityType.class);
		for (Class<?> entity : entities) {
			String rowQuery = nu.uncapName(entity.getSimpleName()) + QUERY;
			sb.append(" " + rowQuery + ".ddl " + " ++");
		}
		return nu.trimRight(sb.toString(), "++");
	}

	/**
	 * this returns all the mixims needed for the receptionist ex: with
	 * ReverseRoute with DaoServicesRoute
	 * 
	 * @param aClass
	 * @return
	 */
	public String getReceptionMixins() {
		StringBuffer sb = new StringBuffer();

		String mixin = tplu.getAppClassName(Tid.SCALA_APP_STATICROUTE.name());
		sb.append(" with " + mixin);

		List<Class<?>> entities = ModelClassesUtils.findModelClassesWithInterface(IEntityType.class);
		for (Class<?> entity : entities) {
			if (generateRestService(entity)) {
				mixin = tplu.getClassName(entity, Tid.SCALA_DAO_REQHANDLER.name());
				sb.append(" with " + mixin);
			}
		}
		List<Class<?>> services = getAllModelServices();
		for (Class<?> srv : services) {
			mixin = tplu.getClassName(srv, Tid.SCALA_SERVICE_REQHANDLER.name());
			sb.append(" with " + mixin);
		}
		return sb.toString();
	}

	/**
	 * indicates if a Json/Rest service should be generated for the given entity
	 * @param aEntity
	 * @return
	 */
	public boolean generateRestService(Class<?> aEntity) {
		RestService annot = (RestService) getAnnotation(aEntity, RestService.class);
		if (annot == null) {
			return false;
		} else {
			return annot.generateService();
		}
	}
	
	/**
	 * this returns all request handlers for the receptionist ex: 
	*  case HttpRequest(GET, Uri.Path("/findTsta"), _, _, _)     => DemoRequestHandler.handleFindTsta()
	 * 
	 * @param aClass
	 * @return
	 */
	public String getReceptionRoutes() {
		return th.getReceptionRoutes();
	}

	// @formatter:off
	/**
	 * 
	 * This generates something like this for all nexted objects in the given
	 * class: private def retrieveTstAtstes(obj:TstA) (implicit session:
	 * Session): Set[TstE] = { val joinQuery = for { tstA <- tstAQuery if tstA.id
	 * === obj.id tstcs <- tstCQuery if tstA.id === tstcs.tstaId tste <-
	 * tstEQuery if tstcs.id === tste.tstCId } yield (tste) val rowlist =
	 * joinQuery.list rowlist.map(mapFromTstERow(_)).to[Set] }
	 * 
	 * @param aClass
	 * @return
	 */
	// @formatter:on
	public String genRetrieveAndMergeNestedObjects(Class<?> aClass) {
		return th.genRetrieveAndMergeNestedObjects(aClass);
	}

	/**
	 * In generated entity classes, fields that denote a onetoone or manytoone
	 * relation also have a cooresponding _xxxId var and xxxId def that holds the
	 * corresponding id of the relation. But manytoone relations, may be defined
	 * as not bi-directional. In this case, this method will return false
	 * otherwise it will return true
	 * 
	 * @param fw
	 * @return
	 */
	public boolean useCorrIdField(Fw fw) {
		if (fw == null) {
			return false;
		} else {
			return !fw.isSimple();
		}
	}

	/**
	 * In generated entity classes, fields that denote a onetoone or manytoone
	 * relation also have a cooresponding _xxxId var and xxxId def that holds the
	 * corresponding id of the relation. But manytoone relations, may be defined
	 * as not bi-directional. In this case, this method will simply return the
	 * fieldname otherwise it will return something like: "_xxxId"
	 * 
	 * @param fw
	 * @return
	 */
	public String getCorrIdFieldname(Fw fw) {
		if (fw == null) {
			return "?corrIdFieldname?";
		} else {
			if (useCorrIdField(fw)) {
				return "_" + fw.name() + ID_SUFFIX;
			} else {
				return fw.name();
			}
		}
	}

	/**
	 * return a lsit of static prefix paths
	 * 
	 * @return
	 */
	public List<String> getStaticPaths() {
		List<String> result = new ArrayList<>();
		result.add("\"web\"");
		result.add("\"css\"");
		result.add("\"img\"");
		result.add("\"js\"");
		return result;
	}

	/**
	 * This will do a copy + search/replace of a zipfile to setup the target Dart
	 * project
	 */
	public void setupScalaProject() {
		try {
			ZipFile zipfile = getScalaProjectZipfile();
			File targetdir = getTargetDir();
			Map<String, String> fromtos = getScalaProjectFromTos();
			ZipFileHelper ziphelper = new ZipFileHelper(zipfile, fromtos, targetdir);
			ziphelper.doGenerate();
		} catch (Exception ex) {
			String msg = "error in setupDartProject " + ex;
			logger.error(msg);
		}
	}

	private File getTargetDir() {
		String target = getSubsvalueFromDataStore("Backend");
		if (target == null) {
			logger.warn("SubsValue 'Backend' not set, will use " + ShellUtils.getTempDir());
			return ShellUtils.getTempDir();
		} else {
			return new File(target);
		}
	}

	private ZipFile getScalaProjectZipfile() throws Exception {
		String cartdir = DataStore.getInstance().getValue(CodegenConstants.DATASTORE_CARTRIDGE_DIR);
		File zipfile = new File(cartdir + ShellUtils.getPathDelim() + "scala_project.zip");
		if (zipfile.exists()) {
			return new ZipFile(zipfile);
		} else {
			throw new Exception("zip file 'scala_project.zip' not found in cartridges folder");
		}
	}

	private Map<String, String> getScalaProjectFromTos() {
		Map<String, String> r = new HashMap<>();
		r.put("APPNAME", getSubsvalueFromDataStore(CodegenConstants.APP_NAME));
		r.put("appname", getSubsvalueFromDataStore(CodegenConstants.APP_NAME).toLowerCase());
		String mainpck = getSubsvalueFromDataStore(CodegenConstants.APP_PACKAGE);
		String maincls = getSubsvalueFromDataStore(CodegenConstants.APP_NAME);
		r.put("MAINCLASS", mainpck + "." + maincls);
		return r;
	}

	@Override
	protected List<Fw> getSpecialFields(Class<?> aClass) {
		List<Fw> result = super.getSpecialFields(aClass);
		result.add(new FetchDepthFieldWrapper(aClass));
//		result.add(new DiscriminatorFieldWrapper(aClass));
		return result;
	}

	@Override
	protected List<Fw> getSpecialFields(Class<?> aClass, FwSelectType... selectOpts) {
		List<Fw> result = new ArrayList<Fw>();
		getSpecialOfdField(aClass, result, selectOpts);
		getSpecialDiscriminatorField(aClass, result, selectOpts);
		return result;
	}

	private void getSpecialOfdField(Class<?> aClass, List<Fw> result, FwSelectType... selectOpts) {
		// add the ofd field if needed
		if (!hasSelectOption(OFD_FLD, selectOpts) && !hasSelectOption(VAL_FLD, selectOpts) 
				&& isEntity(aClass) ) {
			result.add(new FetchDepthFieldWrapper(aClass));
		}
	}

	private void getSpecialDiscriminatorField(Class<?> aClass, List<Fw> result, FwSelectType... selectOpts) {
		// add the dicriminator if needed
		if (isEntity(aClass) && hasInheritance(aClass) && 
			 !hasSelectOption(DISC_FLD, selectOpts) && !hasSelectOption(VAL_FLD, selectOpts)) {
			result.add(new DiscriminatorFieldWrapper(aClass));
		}
	}
	
	public String getFetchDepthTypeName(Class<?> aClass) {
		if (isBaseClass(aClass) || isSubClass(aClass)) {
			return FetchDepth.class.getSimpleName();
		} else {
			return aClass.getSimpleName() + ScalaTypeUtils.FD;
		}
	}
	
	public NestedObjects getNestedObjects(Class<?> aClass) {
		return new NestedObjects(aClass);
	}
	
}
