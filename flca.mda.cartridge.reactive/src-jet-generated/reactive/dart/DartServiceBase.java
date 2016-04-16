package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartServiceBase
{
  protected static String nl;
  public static synchronized DartServiceBase create(String lineSeparator)
  {
    nl = lineSeparator;
    DartServiceBase result = new DartServiceBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ";" + NL + "  " + NL + "import '";
  protected final String TEXT_3 = "';" + NL + "" + NL + "abstract class ";
  protected final String TEXT_4 = " {" + NL;
  protected final String TEXT_5 = NL + "  ";
  protected final String TEXT_6 = "() {" + NL + "  }" + NL + "" + NL + "  var _host;" + NL + "  " + NL + "  String getHost() {" + NL + "    if (_host == null) {" + NL + "      int mode = ";
  protected final String TEXT_7 = ".RUN_MODE;" + NL + "      if (mode == ";
  protected final String TEXT_8 = ".RUN_MODE_LOCAL) {" + NL + "        _host = \"../resources/json\";" + NL + "      } else if (mode == ";
  protected final String TEXT_9 = ".RUN_MODE_TEST) { " + NL + "        //this url is based on the Corsa proxyserver. (> corsa --allow-proxy ALL --allow-origin ALL)" + NL + "        _host = \"http://localhost:${";
  protected final String TEXT_10 = ".PROXY_PORT}/proxy/http://localhost:${";
  protected final String TEXT_11 = ".HOST_PORT}\";   " + NL + "      } else {" + NL + "        _host = \"localhost:${";
  protected final String TEXT_12 = ".HOST_PORT}\" ;" + NL + "      }" + NL + "    }" + NL + "    return _host;" + NL + "  }" + NL + "" + NL + "  void getData(action, args, callback) {" + NL + "    var request = HttpRequest.getString(getUrl(action, args)).then(callback);" + NL + "  }" + NL + "  " + NL + " " + NL + "  void postData(action, obj, callback) {" + NL + "    if (";
  protected final String TEXT_13 = ".RUN_MODE == ";
  protected final String TEXT_14 = ".RUN_MODE_LOCAL) {" + NL + "      print(\"json = \" + JSON.encode(obj));" + NL + "      getData(action, null, callback);" + NL + "    } else {" + NL + "      var request = new HttpRequest();" + NL + "      request.onReadyStateChange.listen(callback);" + NL + "      request.open(\"GET\", getUrl(action, null));" + NL + "      request.send(JSON.encode(obj));" + NL + "    }" + NL + "  }" + NL + "" + NL + "  String getUrl(String action, String args) {" + NL + "    var url;" + NL + "    if (";
  protected final String TEXT_15 = ".RUN_MODE == ";
  protected final String TEXT_16 = ".RUN_MODE_LOCAL) {" + NL + "      url = getHost() + \"/\" + action + \".json\";" + NL + "    } else {" + NL + "      url = getHost() + \"/\" + action;" + NL + "      if (args != null) {" + NL + "        url += args;" + NL + "      }" + NL + "    }" + NL + "    return url;" + NL + "  }" + NL + "}";

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
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(appConstants);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
