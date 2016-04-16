package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartEnum
{
  protected static String nl;
  public static synchronized DartEnum create(String lineSeparator)
  {
    nl = lineSeparator;
    DartEnum result = new DartEnum();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ";" + NL + " " + NL + "import '";
  protected final String TEXT_4 = "';" + NL + "  " + NL + "class ";
  protected final String TEXT_5 = " extends Enum<String> {" + NL + " " + NL + "  const ";
  protected final String TEXT_6 = "(String val): super(val);" + NL + " ";
  protected final String TEXT_7 = NL + "  static const ";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = " = const ";
  protected final String TEXT_10 = "('";
  protected final String TEXT_11 = "'); ";
  protected final String TEXT_12 = "\t" + NL;
  protected final String TEXT_13 = NL + "  static const List values = const [";
  protected final String TEXT_14 = "];" + NL + "  static const Map selectMap = const {";
  protected final String TEXT_15 = NL + "    '";
  protected final String TEXT_16 = "': '";
  protected final String TEXT_17 = "',";
  protected final String TEXT_18 = NL + "  };" + NL + "" + NL + "  static ";
  protected final String TEXT_19 = " valueOf(String val) {" + NL + "    return values.firstWhere((e) => e.value == val, orElse: () => null);" + NL + "  }" + NL + "" + NL + "  Map toJson() {" + NL + "    Map map = new Map();" + NL + "    map[\"enumClass\"] = \"";
  protected final String TEXT_20 = "\";" + NL + "    map[\"value\"] = value;" + NL + "    return map;" + NL + "  }" + NL + "} ";
  protected final String TEXT_21 = NL;

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
     NameUtils nu = new NameUtils();
     AppUtils au = new AppUtils(); 
     FilenameHelper fnh = new FilenameHelper(); 
     String appname = au.getApplicationName(); 
     String pck = nu.getCurrentPackage();
     String classname = nu.getCurrentClassname();
     Class enumClass = arg.getElementClass(); String clsname = enumClass.getSimpleName(); 
     String valuesString = ""; 
     String dartlib = fnh.getRelativeToRootPath(enumClass, arg.getTemplate()) + appname.toLowerCase() + "_library.dart"; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dartlib);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(clsname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(clsname);
    stringBuffer.append(TEXT_6);
     for (String item : tu.getEnumItems(enumClass)) { 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(clsname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(clsname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_12);
     for (String item : tu.getEnumItems(enumClass)) { 
       valuesString += clsname + "."  + item + ","; 
     } 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(nu.trimRight(valuesString,","));
    stringBuffer.append(TEXT_14);
     for (String item : tu.getEnumItems(enumClass)) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_17);
     } 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(clsname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(enumClass.getName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
