package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class AppMain
{
  protected static String nl;
  public static synchronized AppMain create(String lineSeparator)
  {
    nl = lineSeparator;
    AppMain result = new AppMain();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + " " + NL + "object ";
  protected final String TEXT_6 = " extends App {" + NL + "\timplicit val system = ActorSystem(\"reactive\")" + NL + "\timplicit val materializer = FlowMaterializer()" + NL + "  " + NL + "\tval ds = ";
  protected final String TEXT_7 = ".datasource" + NL + "\tvar dal = new ";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = ".driver)" + NL + "\tval db = Database.forDataSource(ds)" + NL + "\t" + NL + "\tConsole.println(\"starting ";
  protected final String TEXT_10 = " \" + (args mkString \", \"))" + NL + "\tstartup" + NL + "\t" + NL + "\tval serverBinding = Http(system).bind(interface = \"localhost\", port = 8000)" + NL + "\t" + NL + "\tserverBinding.connections.foreach { connection => " + NL + "\t\t//println(\"Accepted new connection from \" + connection.remoteAddress)" + NL + "\t\tconnection handleWithAsyncHandler ";
  protected final String TEXT_11 = ".route" + NL + "\t}" + NL + "\t" + NL + "\tConsole.println(\"waiting for request pn localhost:8000\")" + NL + "\t" + NL + "\tdef startup() = {" + NL + "\t\tsetupDatabase(";
  protected final String TEXT_12 = ".dbType, dal, db)" + NL + "\t\t";
  protected final String TEXT_13 = ".initialize" + NL + "\t}" + NL + "\t" + NL + "\tdef setupDatabase(name: String, dal: ";
  protected final String TEXT_14 = ", db: Database) {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "\t\tprintln(\"Running test against \" + name)" + NL + "\t\tdb withSession { implicit session: Session =>" + NL + "\t\t\tif (";
  protected final String TEXT_15 = ".dropTables) {dal.drop}" + NL + "\t\t\tif (";
  protected final String TEXT_16 = ".createTables) {dal.create}" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_17 = NL;

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
     impu.addImport("akka.actor.*");
     impu.addImport("akka.stream.FlowMaterializer");
     impu.addImport("akka.http.Http");
     impu.addImport("akka.http.model.*");
     impu.addImport("akka.http.model.HttpMethods.*");
     impu.addImport("akka.stream.FlowMaterializer");
     impu.addImport("scala.slick.jdbc.JdbcBackend.Database");
     String appstart = tplu.getClassName(Tid.SCALA_APP_STARTUP.name());
     String appcfg = tplu.getClassName(Tid.SCALA_APP_CONFIG.name());
     String reception = tplu.getClassName(Tid.SCALA_APP_ROUTE.name());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(reception);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(appstart);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_16);
     importStringBuffer.insert(importInsertionPoint, impu.computeSortedImports());
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
