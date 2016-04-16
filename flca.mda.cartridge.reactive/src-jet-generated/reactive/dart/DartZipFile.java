package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DartZipFile
{
  protected static String nl;
  public static synchronized DartZipFile create(String lineSeparator)
  {
    nl = lineSeparator;
    DartZipFile result = new DartZipFile();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;

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
     NameUtils nu = new NameUtils();
     TemplateUtils tplu = new TemplateUtils();
     AppUtils au = new AppUtils(); 
     FilenameHelper fnh = new FilenameHelper(); 
     Class intf = arg.getElementClass(); 
     String appname = au.getApplicationName(); 
     String pck = nu.getCurrentPackage();
     String classname = nu.getCurrentClassname();
     String dartlib = appname.toLowerCase() + "_library.dart"; 
     String appConstants = tplu.getAppClassName(Tid.DART_APP_CONSTANTS.name());
     tu.setupDartProject(); 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
