package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartLeftTree
{
  protected static String nl;
  public static synchronized DartLeftTree create(String lineSeparator)
  {
    nl = lineSeparator;
    DartLeftTree result = new DartLeftTree();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ";" + NL + " " + NL + "import '../";
  protected final String TEXT_3 = "';" + NL + "" + NL + "@CustomTag('left-tree')" + NL + "class LeftTree extends PolymerElement {" + NL;
  protected final String TEXT_4 = NL + NL + "  static List sServiceMethods = toObservable([";
  protected final String TEXT_5 = "]);" + NL + "  static List sDaoMethods = toObservable([";
  protected final String TEXT_6 = "]);" + NL + "  " + NL + "  List serviceMethods = sServiceMethods;" + NL + "  List daoMethods = sDaoMethods;" + NL + "  " + NL + "  void handleClick(MouseEvent e) {" + NL + "    ButtonElement btn = e.currentTarget;" + NL + "    var cmd = btn.text;" + NL + "    Pubsub.publish(";
  protected final String TEXT_7 = ".EVENT_MENU_CLICK, cmd);" + NL + "  }" + NL + "  " + NL + "  LeftTree.created() : super.created() {" + NL + "  }" + NL + "" + NL + "}";
  protected final String TEXT_8 = NL;

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
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(dartlib);
    stringBuffer.append(TEXT_3);
     String srvmethods = ""; String daomethods = "";
     for (String srv : tu.getAllServiceMethods()) {  srvmethods += "'" + srv + "',"; } 
     for (Class clz: tu.getAllModelEntities()) {  
       if (tu.hasAnnotation(clz, mda.annotation.RestService.class)) daomethods += "'" + clz.getSimpleName() + "',"; 
     } 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(nu.trimRight(srvmethods,","));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(nu.trimRight(daomethods,","));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
