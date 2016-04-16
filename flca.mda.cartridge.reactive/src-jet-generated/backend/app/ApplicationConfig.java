package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ApplicationConfig
{
  protected static String nl;
  public static synchronized ApplicationConfig create(String lineSeparator)
  {
    nl = lineSeparator;
    ApplicationConfig result = new ApplicationConfig();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "\t" + NL + "object ";
  protected final String TEXT_6 = " {" + NL + "\t\t\t\t" + NL + "\tlazy val logger = LoggerFactory.getLogger(";
  protected final String TEXT_7 = ".getClass.getName())" + NL + "\t\t\t" + NL + "\t//constants being uses in application.conf" + NL + "\tval APP_NAME = \"easymda-reactive\"" + NL + "\tval MODUS = \"mode\"" + NL + "\tval RUN_MODUS = \"run.\" + MODUS" + NL + "\tval DROP_TABLES = \"tables.drop\"" + NL + "\tval CREATE_TABLES = \"tables.create\"" + NL + "\tval POPULATE_TABLES = \"tables.populate\" " + NL + "\tval DATASOURCE = \"datasource\"\t" + NL + "\t" + NL + "\tval conf = ConfigFactory.load" + NL + "\tlogger.info(s\"*** running in ${runMode()} mode ******************\")" + NL + "\t " + NL + "\tdef runMode():String = if (conf.hasPath(RUN_MODUS)) conf.getString(RUN_MODUS) else \"PROD\"" + NL + "\tdef dropTables():Boolean = datasourceFlag(DROP_TABLES)" + NL + "\tdef createTables():Boolean = datasourceFlag(CREATE_TABLES)" + NL + "\tdef populateTables():Boolean = datasourceFlag(POPULATE_TABLES)" + NL + "\t" + NL + "\tprivate def datasourceFlag(suffix:String):Boolean = {" + NL + "\t\tval key = MODUS + \".\" + runMode + \".\" + DATASOURCE + \".\" + suffix\t" + NL + "\t\tprintln(key)" + NL + "\t\tif (conf.hasPath(key)) {" + NL + "\t\t\treturn conf.getBoolean(key)" + NL + "\t\t}\telse false" + NL + "\t}" + NL + "}";
  protected final String TEXT_8 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     	// here we parse the input argument(s) and create the object JetArgument:arg and Object[]:args
	JetArgument arg = null;
	Object args[] = null;
	 
	if (argument != null) { 
		if (argument instanceof JetArgument) {
			arg = (JetArgument) argument;
		} else if (argument.getClass().isArray()) { 
			args = (Object[]) argument;
			if (args[0] instanceof JetArgument) {
				arg = (JetArgument) args[0];
			}
		}
	} 

     ScalaTypeUtils tu = new ScalaTypeUtils();
     NameUtils nu = new NameUtils();
     TemplateUtils tplu = new TemplateUtils();
     ScalaInterfaceUtils iu = new ScalaInterfaceUtils(); 
     AppUtils au = new AppUtils(); 
     Object element = arg.getElement(); 
     Class cc = element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     String entity = tplu.getClassName(Tid.SCALA_ENTITY.name());
     String datastores = tplu.getClassName(Tid.SCALA_APP_DATASTORES.name());
     String datasource = tplu.getClassName(Tid.SCALA_SLICK_DATASOURCE.name());
     String uncapname = nu.uncapName(entity); 
     String idname = tu.getIdName(cc); String idtype = tu.getIdTypeName(); 
     String appName = au.getApplicationName(); 
     String appPck = au.getApplicationPackage();
     boolean isSubClass = tu.isSubClass(cc); 
     boolean isBaseClass = tu.isBaseClass(cc); 
     boolean isNormalClass = tu.isNormalClass(cc); 
     Class superClass = tu.getSuperClass(cc); //only valid when isSubClass 
     String superClassname = isSubClass ? superClass.getSimpleName() : ""; 
     List<Class<?>> subclasses = tu.getSubClasses(cc); 
     boolean isAbstract = tu.hasAbstractAnnotation(); 
     String fetchModel = tu.getFetchDepthTypeName(cc); 
     GetFieldsModus EXC = GetFieldsModus.EXCLUDE; 
     GetFieldsModus INC = GetFieldsModus.INCLUDE; 
     FwSelectType ID_FLD = FwSelectType.ID; 
     FwSelectType VAL_FLD = FwSelectType.VAL; 
     FwSelectType VAR_FLD = FwSelectType.VAR; 
     FwSelectType O2M_FLD = FwSelectType.ONETOMANY; 
     FwSelectType M2M_FLD = FwSelectType.MANYTOMANY; 
     FwSelectType M2O_FLD = FwSelectType.MANYTOONE; 
     FwSelectType O2O_FLD = FwSelectType.ONETOONE; 
     FwSelectType REL_FLD = FwSelectType.RELATIONS; 
     FwSelectType OFD_FLD = FwSelectType.OFD; 
     FwSelectType SPECIAL_FLD = FwSelectType.SPECIAL; 
     FwSelectType DISC_FLD = FwSelectType.DISCRIMINATOR; 
     FwSelectType SIMPLE_FLD = FwSelectType.SIMPLE; 
     FwSelectType ENUM_FLD = FwSelectType.ENUM; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     impu.addImport("com.typesafe.config.Config");
     impu.addImport("com.typesafe.config.ConfigFactory");
     impu.addImport("org.slf4j.LoggerFactory");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
     importStringBuffer.insert(importInsertionPoint,	impu.computeSortedImports());
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
