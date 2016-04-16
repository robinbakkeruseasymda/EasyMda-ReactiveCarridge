package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ServiceImpl
{
  protected static String nl;
  public static synchronized ServiceImpl create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceImpl result = new ServiceImpl();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_4 = ";" + NL + "\t";
  protected final String TEXT_5 = NL + NL;
  protected final String TEXT_6 = NL + "/**" + NL + " * ";
  protected final String TEXT_7 = NL + " *" + NL + " * @author ";
  protected final String TEXT_8 = NL + " * @version $Id: ServiceIntf.jet,v 1.1 rbakkerus Exp $" + NL + " * @generated" + NL + " */" + NL + "" + NL + "object ";
  protected final String TEXT_9 = " extends ";
  protected final String TEXT_10 = " {" + NL + " ";
  protected final String TEXT_11 = NL + NL + "\t/**" + NL + "\t * ";
  protected final String TEXT_12 = NL + "\t */" + NL + "\tdef ";
  protected final String TEXT_13 = "(";
  protected final String TEXT_14 = ") : ";
  protected final String TEXT_15 = " = {";
  protected final String TEXT_16 = NL + "    ";
  protected final String TEXT_17 = NL + "\t}";
  protected final String TEXT_18 = NL + "}";
  protected final String TEXT_19 = NL;

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
     impu.addImport("scala.slick.jdbc.JdbcBackend.Database");
     String srvintf = tplu.getClassName(Tid.SCALA_SERVICE_INTF.name()); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(tu.getDocumentation());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(System.getProperty("user.name"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(srvintf );
    stringBuffer.append(TEXT_10);
    for (Method m : iu.getMethods()) { 
      String arguments = nu.join(iu.getArguments(m), ","); String params = iu.getParameters(m);
    String returnType = iu.getReturn(m); String returnParam = iu.getReturn(m);  
    stringBuffer.append(TEXT_11);
    stringBuffer.append((iu.getDocumentation(m)) != null ? (iu.getDocumentation(m)) : "todo" );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(params);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(returnParam);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    stringBuffer.append((iu.isVoid(m)) ? "" : "null");
    stringBuffer.append(TEXT_17);
    }//for
    stringBuffer.append(TEXT_18);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
