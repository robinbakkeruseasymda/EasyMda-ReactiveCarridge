package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ServiceReqHandler
{
  protected static String nl;
  public static synchronized ServiceReqHandler create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceReqHandler result = new ServiceReqHandler();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_4 = ";" + NL + "\t";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + " " + NL + "class ";
  protected final String TEXT_7 = " {" + NL + "\t " + NL + "\timplicit val system = ActorSystem(\"";
  protected final String TEXT_8 = "\")" + NL + "\timplicit val mat = FlowMaterializer()" + NL + "\tval logger = Logging.getLogger(system, this);" + NL + " " + NL + "\tval ";
  protected final String TEXT_9 = " = system.actorOf(";
  protected final String TEXT_10 = ".props, ";
  protected final String TEXT_11 = ".name)" + NL + "\t ";
  protected final String TEXT_12 = NL + NL + "\tdef ";
  protected final String TEXT_13 = "(req:HttpRequest) : Future[HttpResponse] = {" + NL + "\t\tlogger.debug(\"start handle ";
  protected final String TEXT_14 = " ....\")" + NL + "\t\timplicit val timeout = Timeout(30 seconds)" + NL + "\t\t";
  protected final String TEXT_15 = NL + NL + "\t\t";
  protected final String TEXT_16 = " ! ";
  protected final String TEXT_17 = "Req" + NL + "\t\tvoidResponse()" + NL + "\t}";
  protected final String TEXT_18 = NL + " " + NL + " \t\t";
  protected final String TEXT_19 = " ! ";
  protected final String TEXT_20 = "Req" + NL + "\t\tvoidResponse()" + NL + "\t}";
  protected final String TEXT_21 = NL + " " + NL + "\t\tval p = Promise[HttpResponse]()" + NL + " " + NL + " \t\tval futureResponse = ";
  protected final String TEXT_22 = ".ask(";
  protected final String TEXT_23 = "Req).mapTo[";
  protected final String TEXT_24 = "Res]" + NL + "\t\tfutureResponse onComplete {" + NL + "\t\t\tcase Success(";
  protected final String TEXT_25 = "Res(value)) => p success HttpResponse(entity=";
  protected final String TEXT_26 = ")" + NL + "\t\t\tcase Failure(e) => p success HttpResponse(500, entity=e.getMessage)" + NL + "\t\t}" + NL + "\t\tp.future" + NL + "\t}";
  protected final String TEXT_27 = NL + " " + NL + "\t\tval p = Promise[HttpResponse]()" + NL + " " + NL + " \t\tval futureResponse = ";
  protected final String TEXT_28 = ".ask(";
  protected final String TEXT_29 = "Req).mapTo[";
  protected final String TEXT_30 = "Res]" + NL + "\t\tfutureResponse onComplete {" + NL + "\t\t\tcase Success(";
  protected final String TEXT_31 = "Res(value)) => p success HttpResponse(entity=";
  protected final String TEXT_32 = ")" + NL + "\t\t\tcase Failure(e) => p success HttpResponse(500, entity=e.getMessage)" + NL + "\t\t}" + NL + "\t\tp.future" + NL + "\t}";
  protected final String TEXT_33 = NL + "\t";
  protected final String TEXT_34 = NL + " " + NL + "\tdef voidResponse() : Future[HttpResponse] = {" + NL + "\t\tval future: Future[HttpResponse] = Future { HttpResponse() }" + NL + "\t\tfuture.map { result => result }" + NL + "\t}" + NL + " " + NL + "}";
  protected final String TEXT_35 = NL;

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
     ScalaInterfaceUtils iu = new ScalaInterfaceUtils(); 
    stringBuffer.append(TEXT_1);
     TemplateUtils tplu = new TemplateUtils();
     AppUtils au = new AppUtils(); 
     Class intf = arg.getElementClass(); 
     Class cc = arg.getElementClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     String uncapname = nu.uncapName(classname); 
     String idname = tu.getIdName(cc); String idtype = tu.getIdTypeName(); 
     String datastores = tplu.getClassName(Tid.SCALA_APP_DATASTORES.name());
     String datasource = tplu.getClassName(Tid.SCALA_SLICK_DATASOURCE.name());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_4);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_5);
     impu.addImport("scala.concurrent.*");
     impu.addImport("scala.concurrent.ExecutionContext.Implicits.global");
     impu.addImport("scala.concurrent.duration.*");
     impu.addImport("scala.language.postfixOps");
     impu.addImport("scala.util.Failure");
     impu.addImport("scala.util.Success");
     impu.addImport("akka.actor.*");
     impu.addImport("akka.event.*");
     impu.addImport("akka.pattern.ask");
     impu.addImport("akka.http.model.*");
     impu.addImport("akka.stream.FlowMaterializer");
     impu.addImport("akka.stream.scaladsl.*");
     impu.addImport("akka.util.Timeout");
     impu.addImport("akka.event.LoggingAdapter");
     impu.addTemplateImport(Tid.SCALA_SERVICE_ACTOR.name()); 
     impu.addImport(cc);
     String actor = tplu.getClassName(Tid.SCALA_SERVICE_ACTOR.name()); 
     String actorPck = tplu.getPackage(Tid.SCALA_SERVICE_ACTOR.name());
     impu.addImport(actorPck + "." + actor + ".*");
     String jsonutils = tplu.getClassName(Tid.SCALA_JSONUTILS.name());
     String actorInstance = nu.uncapName(actor); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(actorInstance);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(actor);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(actor);
    stringBuffer.append(TEXT_11);
     for (Method m : iu.getMethods()) { 
    	String arguments = nu.join(iu.getArguments(m), ","); String params = iu.getParameters(m);
		String returnType = iu.getReturn(m); String returnParam = iu.getReturn(m);	
		boolean simpleRettyp = tu.isSimpleField(m.getReturnType());
		String entityValue = (simpleRettyp) ? "value" : "JsonUtils.toJson(value)";
		String name = m.getName(); String ccName = nu.capName(m.getName());
		List<String> reqList = new ArrayList<String>(); 
		for (String s : iu.getArguments(m)) { reqList.add("req." + s); }
		String reqvalues = nu.join(reqList, ","); 
    stringBuffer.append(TEXT_12);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_14);
     if (iu.isVoid(m) && iu.getParameterCount(m) == 0) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(actorInstance);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_17);
     } else if (iu.isVoid(m) && iu.getParameterCount(m) > 0) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(actorInstance);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_20);
     } else if (!iu.isVoid(m) && iu.getParameterCount(m) == 0) { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(nu.uncapName(actor));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entityValue);
    stringBuffer.append(TEXT_26);
     } else { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(nu.uncapName(actor));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(ccName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(entityValue);
    stringBuffer.append(TEXT_32);
    	} 
    stringBuffer.append(TEXT_33);
     } //loop 
    stringBuffer.append(TEXT_34);
     importStringBuffer.insert(importInsertionPoint, impu.computeSortedImports());
    stringBuffer.append(TEXT_35);
    return stringBuffer.toString();
  }
}
