package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartServiceImpl
{
  protected static String nl;
  public static synchronized DartServiceImpl create(String lineSeparator)
  {
    nl = lineSeparator;
    DartServiceImpl result = new DartServiceImpl();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
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
    return stringBuffer.toString();
  }
}
