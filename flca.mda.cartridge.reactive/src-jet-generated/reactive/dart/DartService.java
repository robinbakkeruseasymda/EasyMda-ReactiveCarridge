package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartService
{
  protected static String nl;
  public static synchronized DartService create(String lineSeparator)
  {
    nl = lineSeparator;
    DartService result = new DartService();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL + "library ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ";" + NL + " " + NL + "import '";
  protected final String TEXT_5 = "';" + NL + "" + NL + "class ";
  protected final String TEXT_6 = " extends ";
  protected final String TEXT_7 = " {" + NL;
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = "  " + NL + "    void ";
  protected final String TEXT_10 = "(";
  protected final String TEXT_11 = ") { ";
  protected final String TEXT_12 = NL + "       ";
  protected final String TEXT_13 = "(\"";
  protected final String TEXT_14 = "\", ";
  protected final String TEXT_15 = ", callback);";
  protected final String TEXT_16 = NL + "       ";
  protected final String TEXT_17 = "(\"";
  protected final String TEXT_18 = "\", null, callback);";
  protected final String TEXT_19 = NL + "    }" + NL + "    ";
  protected final String TEXT_20 = NL + "}";

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

     DartTypeUtils tu = new DartTypeUtils();
     ScalaInterfaceUtils iu = new ScalaInterfaceUtils(); 
    stringBuffer.append(TEXT_1);
     NameUtils nu = new NameUtils();
     TemplateUtils tplu = new TemplateUtils();
     FilenameHelper fnh = new FilenameHelper(); 
     AppUtils au = new AppUtils(); 
     Class intf = arg.getElementClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     String uncapname = nu.uncapName(classname); 
     Class cc = tu.currentClass(); 
     String idname = tu.getIdName(cc); String idtype = tu.getIdTypeName(); 
     String serviceImpl = tplu.getClassName(Tid.DART_SERVICE_IMPL.name());
     String serviceImplDir = tplu.getPackage(Tid.DART_SERVICE_IMPL.name());
     String constantsName = tplu.getClassName(Tid.DART_APP_CONSTANTS.name());
     String serviceBase = tplu.getClassName(Tid.DART_APP_SERVICE_BASE.name());
     String appname = au.getApplicationName(); 
     String dartlib = fnh.getRelativeToRootPath(intf, arg.getTemplate()) + appname.toLowerCase() + "_library.dart"; 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(dartlib);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(serviceBase);
    stringBuffer.append(TEXT_7);
     for (Method m : iu.getMethods()) { 
       String restMethod = (iu.isRestPostMethod(m)) ? "postData" : "getData"; 
    	 String arguments = nu.join(iu.getArguments(m), ",");
    	 String params = "";
    	 for (int i=0; i < iu.getParameterCount(m); i++) { 
    		 params += iu.getParameterName(m, i) ; 
    		 if (iu.moreParameters(m,i)) { params += ", ";} 
    stringBuffer.append(TEXT_8);
    	 } 
       String s1 = params; if (iu.getParameterCount(m)>0) s1 += ", "; s1 += "Function callback"; 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(s1);
    stringBuffer.append(TEXT_11);
       if (params.length() > 0) { 
    stringBuffer.append(TEXT_12);
    stringBuffer.append(restMethod);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(params);
    stringBuffer.append(TEXT_15);
       } else { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(restMethod);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_18);
       } 
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
