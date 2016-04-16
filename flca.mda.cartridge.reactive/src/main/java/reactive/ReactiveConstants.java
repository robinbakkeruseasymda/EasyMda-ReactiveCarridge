package reactive;

import java.util.List;

import mda.type.IApplicationType;
import mda.type.IDtoType;
import mda.type.IEntityType;
import mda.type.IServiceType;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.ITemplate;

public interface ReactiveConstants {


	// Constants for this cartridge SubsValue's, 
	// see also RegisterTempplates and XxxConstans where these are used.
	String WEBAPP = "webapp"; //will be used as prefix to make all webapp subsvals unique
	String SCR_GEN = "src-gen";
	String SCR_GEN_TEST = "src-gen-test";
	String BACKEND = "Backend";
	String FRONTEND_ZK = "FrontendZk";
	String FRONTEND_FLEX = "FrontendFlex";
	String FRONTEND_DOJO = "FrontendDojo";
	String FRONTEND_DART = "FrontendDart";
	String FRONTEND_WEB = "FrontendWeb";
	String REST_BASE_URL = "rest-base-url";
	String SERVER = "server";
	String SERVER_TOMCAT = "tomcat";
	String SERVER_JETTY = "jetty";	String REACTIVE = "reactive"; 
	String REACTIVE_CARTRIDGE_NAME = "reactive-backend";

	String SPRAY = "spray";
	String SLICK = "slick";

	String LE = "<%=";
	String RI = "%>";
	String APPNAME = LE + CodegenConstants.APP_NAME + RI;
	String APP_PACKAGE = LE + CodegenConstants.APP_PACKAGE + RI;
	String CLS = LE + CodegenConstants.CLASSNAME + RI;
	String PCK = LE + CodegenConstants.PACKAGE + RI;
	String NA = CodegenConstants.NOT_APPLICABLE;
	
//	String COMMON_PCK = "com.easymda.util";

	Class<?> [] ENTITIES = new Class<?> [] {IEntityType.class};
	Class<?> [] SERVICES = new Class<?> [] {IServiceType.class};
	Class<?> [] APP = new Class<?> [] {IApplicationType.class};
	Class<?> [] DTOS = new Class<?> [] {IDtoType.class};
	Class<?> [] ENUMS = new Class<?> [] {Enum.class};
	Class<?> [] NONE = new Class<?> [] {};
	
	/**
	 * this must return all the templates that belong this reactive backend cartridge branch
	 * @return
	 */
	List<ITemplate> getAllTemplates();
	
}
