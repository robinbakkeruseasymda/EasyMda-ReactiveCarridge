package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ServiceFact
{
  protected static String nl;
  public static synchronized ServiceFact create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceFact result = new ServiceFact();
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
  protected final String TEXT_7 = " {" + NL + "  def get() : ";
  protected final String TEXT_8 = " = {";
  protected final String TEXT_9 = NL + "    ";
  protected final String TEXT_10 = NL + "  }" + NL + "}";
  protected final String TEXT_11 = NL;

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
     String srvintf = tplu.getClassName(Tid.SCALA_SERVICE_INTF.name()); 
     String srvimpl = tplu.getClassName(Tid.SCALA_SERVICE_IMPL.name()); 
     String srvmock = tplu.getClassName(Tid.SCALA_SERVICE_MOCK.name()); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(srvintf);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(srvimpl);
    stringBuffer.append(TEXT_10);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
