package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;
import mda.annotation.*;

public class AppRoute
{
  protected static String nl;
  public static synchronized AppRoute create(String lineSeparator)
  {
    nl = lineSeparator;
    AppRoute result = new AppRoute();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + " " + NL + "object ";
  protected final String TEXT_6 = " {" + NL + "  " + NL + "\t// should we inject these !?";
  protected final String TEXT_7 = NL + "\tval ";
  protected final String TEXT_8 = " = new ";
  protected final String TEXT_9 = "() ";
  protected final String TEXT_10 = NL + "\tval ";
  protected final String TEXT_11 = " = new ";
  protected final String TEXT_12 = "() ";
  protected final String TEXT_13 = NL + "    " + NL + "\tdef route(req: HttpRequest): Future[HttpResponse] = req match {";
  protected final String TEXT_14 = NL + "\t\tcase HttpRequest(";
  protected final String TEXT_15 = ", Uri.Path(\"/";
  protected final String TEXT_16 = "\"), _, _, _) => ";
  protected final String TEXT_17 = ".";
  protected final String TEXT_18 = "(req)";
  protected final String TEXT_19 = NL + "  ";
  protected final String TEXT_20 = NL + "\t\tcase HttpRequest(GET, Uri.Path(\"/find";
  protected final String TEXT_21 = "\"), _, _, _) => ";
  protected final String TEXT_22 = ".find";
  protected final String TEXT_23 = "(req)" + NL + "\t\tcase HttpRequest(GET, Uri.Path(\"/retrieve";
  protected final String TEXT_24 = "\"), _, _, _) => ";
  protected final String TEXT_25 = ".retrieve";
  protected final String TEXT_26 = "(req)" + NL + "\t\tcase HttpRequest(POST, Uri.Path(\"/save";
  protected final String TEXT_27 = "\"), _, _, _) => ";
  protected final String TEXT_28 = ".save";
  protected final String TEXT_29 = "(req)";
  protected final String TEXT_30 = NL + "    case _: HttpRequest                                       => unknownReq(req)" + NL + "  }" + NL + "  " + NL + "  private def unknownReq(req: HttpRequest) = {" + NL + "    val future: Future[HttpResponse] = Future { HttpResponse(entity = \"unknown request \" + req.uri.path) }" + NL + "    future.map { result => result }" + NL + "  }" + NL + "}";
  protected final String TEXT_31 = NL;

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
     impu.addImport("akka.http.model.*");
     impu.addImport("akka.http.model.HttpMethods.*");
     impu.addImport("scala.concurrent.*");
     impu.addImport("scala.concurrent.ExecutionContext.Implicits.global");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
     List<Class<?>> services1 = tu.getAllModelServices(); 
     for (Class srv : services1) { 
       String srvReqHandler = tplu.getClassName(srv, Tid.SCALA_SERVICE_REQHANDLER.name()); 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(nu.uncapName(srvReqHandler));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_9);
     } 
     List<Class<?>> entities1 = tu.getAllModelEntities(); 
     for (Class ent : entities1) { 
       if (tu.generateRestService(ent)) { 
         String srvReqHandler = tplu.getClassName(ent, Tid.SCALA_DAO_REQHANDLER.name()); 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(nu.uncapName(srvReqHandler));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_12);
       } 
     } 
    stringBuffer.append(TEXT_13);
     List<Class<?>> services = tu.getAllModelServices(); 
     for (Class srv : services) { 
       String srvReqHandler = nu.uncapName(tplu.getClassName(srv, Tid.SCALA_SERVICE_REQHANDLER.name())); 
       for (Method m : srv.getMethods()) { 
    		RestMethod restanno = (RestMethod) tu.getAnnotation(m, RestMethod.class); 
    		String action = (restanno!=null && restanno.POST()) ? "POST" : "GET"; 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(action);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_18);
       } 
    stringBuffer.append(TEXT_19);
     } 
     List<Class<?>> entities = tu.getAllModelEntities(); 
     for (Class ent : entities) { 
       if (tu.generateRestService(ent)) { 
         String entname = ent.getSimpleName(); 
         String srvReqHandler = nu.uncapName(tplu.getClassName(ent, Tid.SCALA_DAO_REQHANDLER.name())); 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(srvReqHandler);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(entname);
    stringBuffer.append(TEXT_29);
       } 
     } 
    stringBuffer.append(TEXT_30);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_31);
    return stringBuffer.toString();
  }
}
