package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartComponents
{
  protected static String nl;
  public static synchronized DartComponents create(String lineSeparator)
  {
    nl = lineSeparator;
    DartComponents result = new DartComponents();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html>" + NL + "<html>" + NL + "  <head>" + NL + "\t  <!-- @generated import polymer components -->";
  protected final String TEXT_2 = NL + "\t\t<link rel=\"import\" href=\"";
  protected final String TEXT_3 = "\">" + NL + "\t\t<link rel=\"import\" href=\"";
  protected final String TEXT_4 = "\">";
  protected final String TEXT_5 = NL + "  </head>" + NL + "</html>";
  protected final String TEXT_6 = NL;

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
    stringBuffer.append(TEXT_1);
     for (Class<?> clz : tu.getAllModelEntities()) { 
       if (tu.hasAnnotation(clz, mda.annotation.Gui.class)) { 
       flca.mda.codegen.data.ITemplate t1 = tplu.getTemplate(Tid.DART_ENTITY_FORM_HTML.name()); 
       flca.mda.codegen.data.ITemplate t2 = tplu.getTemplate(Tid.DART_ENTITY_BROWSE_HTML.name()); 
       String imp1 = tu.getLibraryImport(clz, t1); 
       String imp2 = tu.getLibraryImport(clz, t2); 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(imp1);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(imp2);
    stringBuffer.append(TEXT_4);
       } 
     } 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
