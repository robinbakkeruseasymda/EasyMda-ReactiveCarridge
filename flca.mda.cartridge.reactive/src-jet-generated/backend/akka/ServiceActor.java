package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ServiceActor
{
  protected static String nl;
  public static synchronized ServiceActor create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceActor result = new ServiceActor();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_4 = ";" + NL + "\t";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + " " + NL + "object ";
  protected final String TEXT_7 = " {" + NL + "  def props = Props[";
  protected final String TEXT_8 = "]" + NL + "  def name = \"";
  protected final String TEXT_9 = "\"" + NL + "  " + NL + "  case class VoidSuccess()" + NL + "  case class VoidFailure(errorMsg:String)" + NL + "  ";
  protected final String TEXT_10 = NL + "  case object ";
  protected final String TEXT_11 = "Req";
  protected final String TEXT_12 = NL + "  case class ";
  protected final String TEXT_13 = "Req(";
  protected final String TEXT_14 = ")";
  protected final String TEXT_15 = "    ";
  protected final String TEXT_16 = NL + "  case class ";
  protected final String TEXT_17 = "Res(value:";
  protected final String TEXT_18 = ")";
  protected final String TEXT_19 = NL + "  ";
  protected final String TEXT_20 = NL + "}" + NL + " " + NL + "class ";
  protected final String TEXT_21 = " extends Actor {" + NL + "  import ";
  protected final String TEXT_22 = "._" + NL + "" + NL + "  import context._" + NL + "  " + NL + "  def receive: Receive = {";
  protected final String TEXT_23 = NL + "    case ";
  protected final String TEXT_24 = "Req => {";
  protected final String TEXT_25 = NL + "    case ";
  protected final String TEXT_26 = "Req(";
  protected final String TEXT_27 = ") => {";
  protected final String TEXT_28 = "    ";
  protected final String TEXT_29 = NL + "      service.";
  protected final String TEXT_30 = "()";
  protected final String TEXT_31 = NL + "    sender ! ";
  protected final String TEXT_32 = "Res(service.";
  protected final String TEXT_33 = "(";
  protected final String TEXT_34 = ")) ";
  protected final String TEXT_35 = NL + "   }";
  protected final String TEXT_36 = NL + "}" + NL + "  " + NL + "  val service = ";
  protected final String TEXT_37 = ".get" + NL + "}";
  protected final String TEXT_38 = NL;

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
     String srvfact = tplu.getClassName(Tid.SCALA_SERVICE_FACT.name()); 
     impu.addImport("akka.actor.Actor");
     impu.addImport("akka.actor.Props");
     impu.addImport(cc);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_9);
     for (Method m : iu.getMethods()) { 
      String params = iu.getParameters(m); String returnType = iu.getReturn(m); 
    boolean noargs = (iu.getParameterCount(m) == 0); 
      if (noargs) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_11);
      } else { 
    stringBuffer.append(TEXT_12);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(params);
    stringBuffer.append(TEXT_14);
      } 
    stringBuffer.append(TEXT_15);
       if (!iu.isVoid(m)) { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_18);
       } 
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_22);
     for (Method m : iu.getMethods()) { 
      String arguments = nu.join(iu.getArguments(m), ","); 
    String returnType = iu.getReturn(m); String returnParam = iu.getReturn(m);  
    boolean noargs = (iu.getParameterCount(m) == 0); 
      if (noargs) { 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_24);
      } else { 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(arguments);
    stringBuffer.append(TEXT_27);
      } 
    stringBuffer.append(TEXT_28);
     if (iu.isVoid(m)) { 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_30);
       } else { 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(nu.capName(m.getName()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(arguments);
    stringBuffer.append(TEXT_34);
       } 
    stringBuffer.append(TEXT_35);
    }//for
    stringBuffer.append(TEXT_36);
    stringBuffer.append(srvfact);
    stringBuffer.append(TEXT_37);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_38);
    return stringBuffer.toString();
  }
}
