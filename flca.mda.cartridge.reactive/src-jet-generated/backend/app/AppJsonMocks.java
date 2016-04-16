package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class AppJsonMocks
{
  protected static String nl;
  public static synchronized AppJsonMocks create(String lineSeparator)
  {
    nl = lineSeparator;
    AppJsonMocks result = new AppJsonMocks();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + NL + "@RunWith(classOf[JUnitRunner])" + NL + "class ";
  protected final String TEXT_6 = " extends FunSuite {" + NL + "  " + NL + "  val tmpdir = System.getProperty(\"java.io.tmpdir\")" + NL + " ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = NL + "  test(\"save";
  protected final String TEXT_9 = "\") {" + NL + "\tval json = JsonUtils.toJson(";
  protected final String TEXT_10 = ".makeRandom(None))" + NL + "    writeFile(\"save";
  protected final String TEXT_11 = ".json\", json)  " + NL + "  }  " + NL + "    " + NL + "  test(\"find";
  protected final String TEXT_12 = "\") {" + NL + "    val list = List(";
  protected final String TEXT_13 = ".makeRandom(None), ";
  protected final String TEXT_14 = ".makeRandom(None), ";
  protected final String TEXT_15 = ".makeRandom(None))" + NL + "\tval json = JsonUtils.toJson(list)" + NL + "    writeFile(\"find";
  protected final String TEXT_16 = ".json\", json)  " + NL + "  }" + NL + "    " + NL + "  test(\"retrieve";
  protected final String TEXT_17 = "\") {" + NL + "\tval json = JsonUtils.toJson(";
  protected final String TEXT_18 = ".makeRandom(Some(100)))" + NL + "    writeFile(\"retrieve";
  protected final String TEXT_19 = ".json\", json)  " + NL + "  }  " + NL + "  ";
  protected final String TEXT_20 = NL;
  protected final String TEXT_21 = NL + "  test(\"";
  protected final String TEXT_22 = "\") {";
  protected final String TEXT_23 = " " + NL + "\tval json = JsonUtils.toJson(";
  protected final String TEXT_24 = ".makeRandom(None))";
  protected final String TEXT_25 = NL + "\tval json = JsonUtils.toJson(\"TODO\")";
  protected final String TEXT_26 = NL + "    writeFile(\"";
  protected final String TEXT_27 = ".json\", json)  " + NL + "  }" + NL + "  ";
  protected final String TEXT_28 = NL + "  ";
  protected final String TEXT_29 = NL + NL + "  private def writeFile(name: String, text: String): Unit = {" + NL + "    val fname = tmpdir + \"/\" + name" + NL + "\tval writer = new PrintWriter(new File(fname))" + NL + "    writer.write(text)" + NL + "    writer.close()" + NL + "    println(\"created \" + fname)" + NL + "  }" + NL + "}" + NL;
  protected final String TEXT_30 = NL;

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
     TemplateUtils tplu = new TemplateUtils();
     ScalaInterfaceUtils iu = new ScalaInterfaceUtils(); 
     AppUtils au = new AppUtils(); 
     Object element = arg.getElement(); 
     Class cc = element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     String entity = tplu.getClassName(Tid.SCALA_ENTITY.name());
     String datastores = tplu.getClassName(Tid.SCALA_APP_DATASTORES.name());
     String datasource = tplu.getClassName(Tid.SCALA_SLICK_DATASOURCE.name());
     String uncapname = nu.uncapName(entity); 
     String idname = tu.getIdName(cc); String idtype = tu.getIdTypeName(); 
     String appName = au.getApplicationName(); 
     String appPck = au.getApplicationPackage();
     boolean isSubClass = tu.isSubClass(cc); 
     boolean isBaseClass = tu.isBaseClass(cc); 
     boolean isNormalClass = tu.isNormalClass(cc); 
     Class superClass = tu.getSuperClass(cc); //only valid when isSubClass 
     String superClassname = isSubClass ? superClass.getSimpleName() : ""; 
     List<Class<?>> subclasses = tu.getSubClasses(cc); 
     boolean isAbstract = tu.hasAbstractAnnotation(); 
     String fetchModel = tu.getFetchDepthTypeName(cc); 
     GetFieldsModus EXC = GetFieldsModus.EXCLUDE; 
     GetFieldsModus INC = GetFieldsModus.INCLUDE; 
     FwSelectType ID_FLD = FwSelectType.ID; 
     FwSelectType VAL_FLD = FwSelectType.VAL; 
     FwSelectType VAR_FLD = FwSelectType.VAR; 
     FwSelectType O2M_FLD = FwSelectType.ONETOMANY; 
     FwSelectType M2M_FLD = FwSelectType.MANYTOMANY; 
     FwSelectType M2O_FLD = FwSelectType.MANYTOONE; 
     FwSelectType O2O_FLD = FwSelectType.ONETOONE; 
     FwSelectType REL_FLD = FwSelectType.RELATIONS; 
     FwSelectType OFD_FLD = FwSelectType.OFD; 
     FwSelectType SPECIAL_FLD = FwSelectType.SPECIAL; 
     FwSelectType DISC_FLD = FwSelectType.DISCRIMINATOR; 
     FwSelectType SIMPLE_FLD = FwSelectType.SIMPLE; 
     FwSelectType ENUM_FLD = FwSelectType.ENUM; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_3);
     	ImportUtils impu = new ImportUtils(); 
   	StringBuffer importStringBuffer = stringBuffer;
   	int importInsertionPoint = stringBuffer.length();
   	impu.addCompilationUnitImports(stringBuffer.toString()); 
 
    stringBuffer.append(TEXT_4);
     impu.addImport("com.easymda.util.JsonUtils");
     impu.addImport("org.junit.runner.RunWith");
     impu.addImport("org.scalatest.BeforeAndAfterEach");
     impu.addImport("org.scalatest.FunSuite");
     impu.addImport("org.scalatest.junit.JUnitRunner");
     impu.addImport("java.io.PrintWriter");
     impu.addImport("java.io.File");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
     for (Class clz : tu.getAllModelEntities()) { 
    stringBuffer.append(TEXT_7);
       if (tu.generateRestService(clz)) { 
       String name=clz.getSimpleName(); String mock=tplu.getClassName(clz, Tid.SCALA_ENTITY_MOCK.name()); 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_19);
       } 
     } 
    stringBuffer.append(TEXT_20);
     for (Class clz : tu.getAllModelServices()) { 
       for (Method m : iu.getMethods(clz)) { 
       Class rettyp = m.getReturnType(); 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_22);
         if (tu.isEntity(rettyp) || tu.isDto(rettyp)) { 
         String mock = (tu.isEntity(rettyp)) ? tplu.getClassName(rettyp, Tid.SCALA_ENTITY_MOCK.name()) : tplu.getClassName(rettyp, Tid.SCALA_DTO_MOCK.name()); 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_24);
         } else { 
    stringBuffer.append(TEXT_25);
         }  
    stringBuffer.append(TEXT_26);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_27);
       } 
    stringBuffer.append(TEXT_28);
     } 
    stringBuffer.append(TEXT_29);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}
