package flca.mda.api.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactive.ReactiveDartTemplates;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.helpers.ShellUtils;
import flca.mda.codegen.helpers.StrUtil;
import flca.mda.codegen.helpers.ZipFileHelper;


public class DartTypeUtils extends ReactiveTypeUtils {

	public static Logger logger = LoggerFactory.getLogger(DartTypeUtils.class);
	
	@Override
	public String generateIdField() {
		return "int id = null";
	}
	
	/**
	 * Returns the primitive or class name for the given Type. Class names will
	 * be added as imports to the GenModel's ImportManager, and the imported
	 * form will be returned.
	 */
	@Override
	public String getTypeName(Class<?> aClass, Class<?> aGenericType) {
		if (isCollection(aClass)) {
			if (aGenericType != null) {
				return getSimpleClassname(aClass) + "<" + getSimpleClassname(aGenericType) + ">";
			} else {
				return getSimpleClassname(aClass);
			}
		} else {
			String result = getSimpleClassname(aClass);
			return result;
		}
	}
	
	/**
	 * Returns the primitive or class name for the given Type. Class names will
	 * be added as imports to the GenModel's ImportManager, and the imported
	 * form will be returned.
	 */
	@Override
	public String getTypeName(Class<?> aClass) {
		if (isCollection(aClass)) {
			return getSimpleClassname(aClass) + "<" + ">";
		} else {
			return getSimpleClassname(aClass);
		}
	}

	@Override
	public String getSimpleClassname(Class<?> aClass) {
		String result = super.getSimpleClassname(aClass);
		return getDartClass(result);
	}

	@Override
	public String getDefaultValue(Class<?> aClass) {
		String result = super.getDefaultValue(aClass);
		return  getDartClass(result);
	}

	private String getDartClass(String aClassname) {
		if (sDartTypes.containsKey(aClassname)) {
			return sDartTypes.get(aClassname);
		} else {
			return aClassname;
		}
	}

	/**
	 * return the polymer form field given the field
	 * ex numeric field -> "int-form-field"
	 * @param aField
	 * @return
	 */
	public String getFormField(Field aField) {
		return getFormField(aField.getType());
	}

	/**
	 * return the polymer form field given the class
	 * ex numeric field -> "int-form-field"
	 * @param aClass
	 * @param aStart
	 * @return
	 */
	public String getFormField(Class<?> aClass) {
		if (isIntType(aClass)) {return "int-form-field"; }
		else if (isDecimalType(aClass)) {return "dec-form-field"; }
		else if (isDateType(aClass)) {return "date-form-field"; }
		else if (isBooleanType(aClass)) {return "bool-form-field"; }
		else return "string-form-field";
	}

	/**
	 * return the type name to be used in the browse form
	 * @param aClass
	 * @return
	 */
	public String getDartBrowseType(Class<?> aClass) {
		if (isIntType(aClass)) {return "number"; }
		else if (isDecimalType(aClass)) {return "number"; }
		else if (isDateType(aClass)) {return "date"; }
		else if (isBooleanType(aClass)) {return "boolean"; }
		else if (isEnum(aClass)) {return "enum"; }
		else return "string";		
	}

	/**
	 * return all Dart constants for all entities
	 * ex: [TSTA_BROWSE_START, TSTA_RETRIEVE]
	 * 
	 * @return
	 */
	public List<String> getAllDartConstants() {
		List<String> result = new ArrayList<>();
		
		List<Class<?>> entities = getAllModelEntities();
		for (Class<?> ent : entities) {
			getAllDartConts(result, ent);
		}
		return result;
	}

	private void getAllDartConts(List<String> result, Class<?> ent) {
		if (generateRestService(ent)) {
			for (String strval : CONST_SRV_MAP.values()) {
				result.add(StrUtil.replace(strval, "<%=entity_const%>", ent.getSimpleName().toUpperCase()));
			}
		}

		if (hasGuiAnnotation(ent)) {
			for (String strval : CONST_GUI_MAP.values()) {
				result.add(StrUtil.replace(strval, "<%=entity_const%>", ent.getSimpleName().toUpperCase()));
			}
		}
	}

	/**
	 * return the String that can be used in dart file to import another file
	 * ex: 
	 * cls = Tsta.class;
	 * ITemplate t = tplu.getTemplate(Tid.DART_ENTITY_BROWSE_HTML.name());
	 * String s = tu.getLibraryImport(clz, t);
	 * s = "com/demo/entity/view/tsta_browse.html"
	 * @param aModelclass
	 * @param aTemplate
	 * @return
	 */
	public String getLibraryImport(Class<?> aModelclass, ITemplate aTemplate) {
		String pck = fnh.formatPackage(aModelclass, aTemplate);
		String clsname = fnh.formatClassName(aModelclass, aTemplate);
		String result = pck + "." + clsname;
		if (result.startsWith(".")) {result = result.substring(1); }
		result = StrUtil.replace(result, ".", "/");
		result = nu.unCamelLower(result, '_');
		result += aTemplate.getFileExtension();
		return result;
	}
	
	/**
	 * This will do copy + search/replace of the  zipfile (cartridges/dart_project.zip) 
	 * to setup the target Dart project
	 */
	public void setupDartProject() {
		try {
			ZipFile zipfile = getDartProjectZipfile();
			File targetdir = new File(getSubsvalueFromDataStore(ReactiveDartTemplates.FRONTEND_DART));
			Map<String, String> fromtos = getDartProjectFromTos();
			ZipFileHelper ziphelper = new ZipFileHelper(zipfile, fromtos, targetdir);
			ziphelper.doGenerate();
		} catch(Exception ex) {
			String msg = "error in setupDartProject " + ex;
			logger.error(msg);
			System.out.println(msg);
			
		}
	}
	
	private ZipFile getDartProjectZipfile() throws Exception {
		String cartdir = DataStore.getInstance().getValue(CodegenConstants.DATASTORE_CARTRIDGE_DIR);
		logger.error("cartdir = " + cartdir);
		File zipfile = new File(cartdir + ShellUtils.getPathDelim() + "dart_project.zip");
		if (zipfile.exists()) {
			return new ZipFile(zipfile);
		} else {
			throw new Exception("zip file 'dart_project.zip' not found in cartridges folder");
		}
	}
	
	private Map<String, String> getDartProjectFromTos() {
		Map<String, String> r = new HashMap<>();
		r.put("APPNAME", getSubsvalueFromDataStore(CodegenConstants.APP_NAME));
		r.put("appname", getSubsvalueFromDataStore(CodegenConstants.APP_NAME).toLowerCase());
		return r;
	}
	
	//+++++++++++ static values ++++++++++++++++++++
	
	/* this Map the Java types that need to replaced by the corresponding Dart type */
	public static enum DartTypeConstants
	{
		ITEM_RETRIEVE, ITEM_RETRIEVE_SUCCESS, ITEM_RETRIEVE_ERROR, 
		ITEM_SAVE, ITEM_SAVE_SUCCESS, ITEM_SAVE_ERROR, 
		ITEM_FINDALL, ITEM_FINDALL_SUCCESS, ITEM_FINDALL_ERROR, 
		ITEM_SEARCH, ITEM_SEARCH_SUCCESS, ITEM_SEARCH_ERROR, 
		ITEM_REMOVE, ITEM_REMOVE_SUCCESS, ITEM_REMOVE_ERROR, 
		ADD_ITEM, EDIT_ITEM, SHOW_ITEM_GRID, SHOW_ITEM_DETAIL, REMOVE_ITEM_GRID, CLOSE_ITEM_DETAIL, REMOVE_ITEM_DETAIL, 
	}

	@SuppressWarnings("serial")
	private static final Map<DartTypeConstants, String> CONST_SRV_MAP = new HashMap<DartTypeConstants, String>() {
		{
			put(DartTypeConstants.ADD_ITEM, "<%=entity_const%>_ADD");
			put(DartTypeConstants.EDIT_ITEM, "<%=entity_const%>_EDIT");
			put(DartTypeConstants.ITEM_RETRIEVE, "<%=entity_const%>_RETRIEVE");
			put(DartTypeConstants.ITEM_RETRIEVE_SUCCESS, "<%=entity_const%>_RETRIEVE_SUCCESS");
			put(DartTypeConstants.ITEM_RETRIEVE_ERROR, "<%=entity_const%>_RETRIEVE_ERROR");
			put(DartTypeConstants.ITEM_SAVE, "<%=entity_const%>_SAVE");
			put(DartTypeConstants.ITEM_SAVE_SUCCESS, "<%=entity_const%>_SAVE_SUCCESS");
			put(DartTypeConstants.ITEM_SAVE_ERROR, "<%=entity_const%>_SAVE_ERROR");
			put(DartTypeConstants.ITEM_FINDALL, "<%=entity_const%>_FINDALL");
			put(DartTypeConstants.ITEM_FINDALL_SUCCESS, "<%=entity_const%>_FINDALL_SUCCESS");
			put(DartTypeConstants.ITEM_FINDALL_ERROR, "<%=entity_const%>_FINDALL_ERROR");
			put(DartTypeConstants.ITEM_SEARCH, "<%=entity_const%>_SEARCH");
			put(DartTypeConstants.ITEM_SEARCH_SUCCESS, "<%=entity_const%>_SEARCH_SUCCESS");
			put(DartTypeConstants.ITEM_SEARCH_ERROR, "<%=entity_const%>_SEARCH_ERROR");
			put(DartTypeConstants.ITEM_REMOVE, "<%=entity_const%>_REMOVE");
			put(DartTypeConstants.ITEM_REMOVE_SUCCESS, "<%=entity_const%>_REMOVE_SUCCESS");
			put(DartTypeConstants.ITEM_REMOVE_ERROR, "<%=entity_const%>_REMOVE_ERROR");
		}
	};

	@SuppressWarnings("serial")
	private static final Map<DartTypeConstants, String> CONST_GUI_MAP = new HashMap<DartTypeConstants, String>() {
		{
			put(DartTypeConstants.SHOW_ITEM_GRID, "<%=entity_const%>_SHOW_GRID");
			put(DartTypeConstants.SHOW_ITEM_DETAIL, "<%=entity_const%>_SHOW_DETAIL");
			put(DartTypeConstants.REMOVE_ITEM_GRID, "<%=entity_const%>_REMOVE_GRID");
			put(DartTypeConstants.CLOSE_ITEM_DETAIL, "<%=entity_const%>_CLOSE_DETAIL");
			put(DartTypeConstants.REMOVE_ITEM_DETAIL, "<%=entity_const%>_REMOVE_DETAIL");
		}
	};
	
	@SuppressWarnings("serial")
	private static Map<String, String> sDartTypes = new HashMap<String, String>() {
		{
			put("Integer", "int");
			put("long", "int");
			put("Long", "int");
			put("Short", "int");
			put("short", "int");
			put("Double", "double");
			put("double", "double");
			put("Double", "double");
			put("BigDecimal", "double");
			put("Date", "DateTime");
			put("Boolean", "bool");
			put("boolean", "bool");
			put("new HashSet<>()", "new Set()");
			put("new ArrayList<>()", "new List()");
		}
	};
	
	//// FR76 1810 6008 1096 7080 9222 067
}
