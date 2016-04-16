package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartMainCtrl
{
  protected static String nl;
  public static synchronized DartMainCtrl create(String lineSeparator)
  {
    nl = lineSeparator;
    DartMainCtrl result = new DartMainCtrl();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ";" + NL + " " + NL + "import '../";
  protected final String TEXT_3 = "';" + NL + "" + NL + "import '../view/left_tree.dart';" + NL + "" + NL + "class MainController {" + NL + "  " + NL + "  static MainController _instance = null;" + NL + "  " + NL + "  factory MainController() {" + NL + "    if (_instance == null) _instance = new MainController._newInstance();  " + NL + "    return _instance;" + NL + "  }" + NL + "  " + NL + "  MainController._newInstance() {" + NL + "    Pubsub.subscribe(";
  protected final String TEXT_4 = ".EVENT_MENU_CLICK, (PubsubMessage msg){" + NL + "      _handleAction(msg.args[0]);" + NL + "    });    " + NL + "    " + NL + "    // init the singleton controller to setup event listeners." + NL + "//    var dummy1 = new TestTstService();" + NL + "    var dummy2 = new TstaCtrl();" + NL + "  }" + NL + "  " + NL + "  void _handleAction(cmd) {" + NL + "    if (isServiceCmd(cmd)) {" + NL + "      Pubsub.publish(";
  protected final String TEXT_5 = ".TEST_SERVICE_ACTION, cmd);" + NL + "    } else {" + NL + "      switch(cmd) {" + NL + "        case \"Tsta\" : " + NL + "          Pubsub.publish(";
  protected final String TEXT_6 = ".TSTA_FINDALL);" + NL + "          break;" + NL + "        default :" + NL + "          window.alert(\"todo \" + cmd);" + NL + "      }" + NL + "    }" + NL + "   }" + NL + "  " + NL + "  bool isServiceCmd(cmd) {" + NL + "    return LeftTree.sServiceMethods.contains(cmd);" + NL + "  }" + NL + "}";
  protected final String TEXT_7 = NL + " ";

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
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
