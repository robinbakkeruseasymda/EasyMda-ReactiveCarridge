package backend.misc;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaEnum
{
  protected static String nl;
  public static synchronized ScalaEnum create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaEnum result = new ScalaEnum();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = NL + " " + NL + "object ";
  protected final String TEXT_3 = " extends Enumeration() {" + NL + "  type ";
  protected final String TEXT_4 = " = Value";
  protected final String TEXT_5 = NL + "  val ";
  protected final String TEXT_6 = " = Value(\"";
  protected final String TEXT_7 = "\")";
  protected final String TEXT_8 = "\t" + NL + " " + NL + "  def parse(name: String) = {";
  protected final String TEXT_9 = NL + "    ";
  protected final String TEXT_10 = " .values.filter(_.toString == name).headOption" + NL + "  }" + NL + "  def parse(name: Option[String]) = {";
  protected final String TEXT_11 = NL + "    ";
  protected final String TEXT_12 = " .values.filter(_.toString == name.get).headOption" + NL + "  }" + NL + "}";
  protected final String TEXT_13 = NL;

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
     Class enumClass = arg.getElementClass(); 
     String pck = nu.getCurrentPackage();
     String classname = nu.getCurrentClassname();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_4);
     for (String item : tu.getEnumItems(enumClass)) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
