package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DataStores
{
  protected static String nl;
  public static synchronized DataStores create(String lineSeparator)
  {
    nl = lineSeparator;
    DataStores result = new DataStores();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "/**" + NL + " * The ";
  protected final String TEXT_6 = " Layer contains all components and a profile" + NL + " */";
  protected final String TEXT_7 = NL + NL + "class ";
  protected final String TEXT_8 = "(override val profile: JdbcProfile) extends Profile";
  protected final String TEXT_9 = NL + "   ";
  protected final String TEXT_10 = NL + "   with ";
  protected final String TEXT_11 = " {" + NL + "  " + NL + "   import profile.simple._" + NL + "  " + NL + "\toverride lazy val logger = LoggerFactory.getLogger(\"";
  protected final String TEXT_12 = ".";
  protected final String TEXT_13 = "\")" + NL + "  " + NL + "   // helper method to create all tables" + NL + "   def create(implicit session:Session): Unit = {" + NL + "     (";
  protected final String TEXT_14 = ").create " + NL + "   }" + NL + "  " + NL + "   // helper method to drop all tables" + NL + "   def drop(implicit session:Session): Unit = {" + NL + "    (";
  protected final String TEXT_15 = ").drop " + NL + "   }" + NL + "}";
  protected final String TEXT_16 = NL;

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
     impu.addImport("scala.slick.driver.JdbcProfile");
     impu.addImport("scala.slick.driver.H2Driver");
     impu.addImport("scala.slick.driver.SQLiteDriver");
     impu.addImport("scala.slick.util.SlickLogger");
     impu.addImport("org.slf4j.LoggerFactory");
     String rowmapper = tplu.getClassName(Tid.SCALA_ROWMAPPERS.name()); 
     impu.addTemplateImport(Tid.SCALA_SLICK_PROFILE.name());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_6);
     String daoMixins = tu.getAllDaoMixins(); 
     String daoQueries = tu.getAllDaoQueries(); 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(daoMixins);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(rowmapper);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(daoQueries);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(daoQueries);
    stringBuffer.append(TEXT_15);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
