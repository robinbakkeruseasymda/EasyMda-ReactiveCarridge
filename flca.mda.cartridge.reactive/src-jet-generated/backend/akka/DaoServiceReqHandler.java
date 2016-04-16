package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DaoServiceReqHandler
{
  protected static String nl;
  public static synchronized DaoServiceReqHandler create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoServiceReqHandler result = new DaoServiceReqHandler();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " " + NL + "class ";
  protected final String TEXT_5 = "\t{" + NL + "\t" + NL + "\timplicit val system = ActorSystem(\"";
  protected final String TEXT_6 = "\")" + NL + "\timplicit val mat = FlowMaterializer()" + NL + "\tval logger = Logging.getLogger(system, this);" + NL + "\t" + NL + "\tval ";
  protected final String TEXT_7 = " = system.actorOf(";
  protected final String TEXT_8 = ".props, ";
  protected final String TEXT_9 = ".name)" + NL + "\t" + NL + "\tdef find";
  protected final String TEXT_10 = "(req:HttpRequest): Future[HttpResponse] = {" + NL + "\t\tlogger.debug(\"start handle find";
  protected final String TEXT_11 = " ....\")" + NL + "\t\timplicit val timeout = Timeout(30 seconds)" + NL + "\t" + NL + "\t\tval p = Promise[HttpResponse]()" + NL + "\t\tval future = tstaDaoSrvActor.ask(Find";
  protected final String TEXT_12 = "Req).mapTo[Find";
  protected final String TEXT_13 = "Res]" + NL + "\t\tfuture onComplete {" + NL + "\t\t\t\tcase Success(Find";
  protected final String TEXT_14 = "Res(value)) => p success HttpResponse(entity=JsonUtils.toJson(value)) " + NL + "\t\t\t\tcase Failure(t) => println(\"fout\"); p success HttpResponse(500, entity=t.getMessage)" + NL + "\t\t}" + NL + "\t\tp.future" + NL + "\t}" + NL + "\t" + NL + "\tdef retrieve";
  protected final String TEXT_15 = "(req: HttpRequest): Future[HttpResponse] = {" + NL + "\t\tlogger.debug(\"start handleRetrieve";
  protected final String TEXT_16 = " ....\")" + NL + "\t\timplicit val timeout = Timeout(30 seconds)" + NL + "\t" + NL + "\t\tval p = Promise[HttpResponse]()" + NL + "\t\tval id = JsonUtils.getReqParamAsInt(req, \"id\")" + NL + "\t\tif (id.isDefined) {" + NL + "\t\t\tval future = tstaDaoSrvActor.ask(Retrieve";
  protected final String TEXT_17 = "Req(id.get)).mapTo[Retrieve";
  protected final String TEXT_18 = "Res]" + NL + "\t\t\tfuture onComplete {" + NL + "\t\t\t\t\tcase Success(Retrieve";
  protected final String TEXT_19 = "Res(value)) => p success HttpResponse(entity=JsonUtils.toJson(value)) " + NL + "\t\t\t\t\tcase Failure(t) => println(\"fout\"); p success HttpResponse(500, entity=t.getMessage)" + NL + "\t\t\t}" + NL + "\t\t} else {" + NL + "\t\t\tp success HttpResponse(500, entity=\"retrieveTsta requires parameter id\")" + NL + "\t\t}" + NL + "\t\tp.future" + NL + "\t}" + NL + "\t" + NL + "\tdef save";
  protected final String TEXT_20 = "(req: HttpRequest): Future[HttpResponse] = {" + NL + "\t\tlogger.debug(\"start handleSave";
  protected final String TEXT_21 = " ....\")" + NL + "\t\timplicit val timeout = Timeout(30 seconds)" + NL + "\t\t" + NL + "\t\tval p = Promise[HttpResponse]()\t\t" + NL + "\t\tval json = JsonUtils.getPostData(req)" + NL + "\t\tif (json != null) {" + NL + "\t\t\tval tsta: ";
  protected final String TEXT_22 = " = JsonUtils.fromJson[";
  protected final String TEXT_23 = "](json)" + NL + "\t\t\tval future = tstaDaoSrvActor.ask(Save";
  protected final String TEXT_24 = "Req(tsta)).mapTo[Save";
  protected final String TEXT_25 = "Res]" + NL + "\t\t\tfuture onComplete {" + NL + "\t\t\t\t\tcase Success(Save";
  protected final String TEXT_26 = "Res(value)) => p success HttpResponse(entity=JsonUtils.toJson(value)) " + NL + "\t\t\t\t\tcase Failure(t) => println(\"fout\"); p success HttpResponse(500, entity=t.getMessage)" + NL + "\t\t\t}" + NL + "\t\t\tfuture.map { result => HttpResponse(entity=JsonUtils.toJson(result.value)) }" + NL + "\t\t} else {" + NL + "\t\t\tp success HttpResponse(500, entity=\"saveTsta requires POST data\")\t\t" + NL + "\t\t}" + NL + "\t\tp.future" + NL + "\t}" + NL + "} ";
  protected final String TEXT_27 = NL;

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
 
     impu.addImport("scala.concurrent.*");
     impu.addImport("scala.concurrent.ExecutionContext.Implicits.global");
     impu.addImport("scala.concurrent.duration.*");
     impu.addImport("scala.util.Failure");
     impu.addImport("scala.util.Success");
     impu.addImport("scala.language.postfixOps");
     impu.addImport("akka.actor.*");
     impu.addImport("akka.event.*");
     impu.addImport("akka.pattern.ask");
     impu.addImport("akka.http.model.*");
     impu.addImport("akka.stream.FlowMaterializer");
     impu.addImport("akka.stream.scaladsl.*");
     impu.addImport("akka.util.Timeout");
     impu.addImport("akka.event.LoggingAdapter");
     impu.addTemplateImport(Tid.SCALA_DAO_SERVICE_ACTOR.name()); 
     impu.addImport(cc);
     String actor = tplu.getClassName(Tid.SCALA_DAO_SERVICE_ACTOR.name()); 
     String actorPck = tplu.getPackage(Tid.SCALA_DAO_SERVICE_ACTOR.name());
     impu.addImport(actorPck + "." + actor + ".*");
     String jsonutils = tplu.getClassName(Tid.SCALA_JSONUTILS.name());
     boolean mustGenerate = tu.generateRestService(cc);
     String actorInstance = nu.uncapName(actor); 
     if (!mustGenerate) { return null; } 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(actorInstance);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(actor);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(actor);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
     importStringBuffer.insert(importInsertionPoint, impu.computeSortedImports());
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
