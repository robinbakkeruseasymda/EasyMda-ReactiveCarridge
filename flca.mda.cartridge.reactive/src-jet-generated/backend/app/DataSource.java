package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DataSource
{
  protected static String nl;
  public static synchronized DataSource create(String lineSeparator)
  {
    nl = lineSeparator;
    DataSource result = new DataSource();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = "\t" + NL + "" + NL + "object ";
  protected final String TEXT_6 = " {" + NL + "" + NL + "  lazy val dbType = \"h2\"" + NL + "    " + NL + "  lazy val datasource = {" + NL + "    val conf = ConfigFactory.load" + NL + "" + NL + "    val dbsCfg = datasourceConfig()" + NL + "    val dbUrl = dbsCfg.getString(\"dburl\")" + NL + "    val driver = dbsCfg.getString(\"driver\")" + NL + "    val dbuser = dbsCfg.getString(\"user\")" + NL + "    val dbpwd = dbsCfg.getString(\"pwd\")" + NL + "" + NL + "    val ds = new BoneCPDataSource" + NL + "    ds.setDriverClass(driver)" + NL + "    ds.setJdbcUrl(dbUrl)" + NL + "    ds.setAcquireIncrement(5)" + NL + "    ds.setUsername(dbuser)" + NL + "    ds.setPassword(dbpwd)" + NL + "    ds" + NL + "  }" + NL + "  " + NL + "  def datasourceConfig() = {" + NL + "    val conf = ConfigFactory.load()" + NL + "    val mode = ";
  protected final String TEXT_7 = ".runMode" + NL + "    val dskey = ";
  protected final String TEXT_8 = ".MODUS + \".\" + mode + \".\" + ";
  protected final String TEXT_9 = ".DATASOURCE" + NL + "    println(s\"mode = $mode getting values from: $dskey\")" + NL + "    conf.getConfig(dskey)" + NL + "  }" + NL + "  " + NL + "  def driver: JdbcDriver =" + NL + "    dbType match {" + NL + "      case \"h2\" => H2Driver" + NL + "      case \"sql-lite\" => SQLiteDriver" + NL + "      case _ => throw new IllegalArgumentException(\"dbType property must be either h2 o sql-lite\")" + NL + "    }" + NL + "}";
  protected final String TEXT_10 = NL;

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
     impu.addImport("scala.slick.driver.JdbcProfile");
     impu.addImport("scala.slick.driver.H2Driver");
     impu.addImport("scala.slick.driver.SQLiteDriver");
     impu.addImport("com.jolbox.bonecp.BoneCPDataSource");
     impu.addImport("scala.slick.driver.JdbcDriver");
     String appcfg = tplu.getAppClassName(Tid.SCALA_APP_CONFIG.name());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_9);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
