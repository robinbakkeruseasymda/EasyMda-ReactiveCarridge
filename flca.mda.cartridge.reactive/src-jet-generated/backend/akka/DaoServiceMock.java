package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DaoServiceMock
{
  protected static String nl;
  public static synchronized DaoServiceMock create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoServiceMock result = new DaoServiceMock();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + "  " + NL + "object ";
  protected final String TEXT_5 = " extends ";
  protected final String TEXT_6 = " {" + NL + "  " + NL + "  override def find";
  protected final String TEXT_7 = "() : List[";
  protected final String TEXT_8 = "] = {" + NL + "    List(";
  protected final String TEXT_9 = ".makeRandom(), ";
  protected final String TEXT_10 = ".makeRandom(), ";
  protected final String TEXT_11 = ".makeRandom())" + NL + "  }" + NL + " " + NL + "  override def retrieve";
  protected final String TEXT_12 = "(";
  protected final String TEXT_13 = ":";
  protected final String TEXT_14 = "): Option[";
  protected final String TEXT_15 = "] = {" + NL + "    Some(";
  protected final String TEXT_16 = ".makeRandom())" + NL + "  }" + NL + " " + NL + "  override def save";
  protected final String TEXT_17 = "(";
  protected final String TEXT_18 = ":";
  protected final String TEXT_19 = "): ";
  protected final String TEXT_20 = " = {";
  protected final String TEXT_21 = NL + "     ";
  protected final String TEXT_22 = NL + "  }" + NL + "  " + NL + "  override def delete";
  protected final String TEXT_23 = "(";
  protected final String TEXT_24 = ":";
  protected final String TEXT_25 = "): Unit = {" + NL + "    println(\"deleted ";
  protected final String TEXT_26 = " : ";
  protected final String TEXT_27 = " ...\")" + NL + "  }" + NL + " ";
  protected final String TEXT_28 = " " + NL + "}";
  protected final String TEXT_29 = NL;

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
 
     String srvintf = tplu.getClassName(Tid.SCALA_DAO_SERVICE_INTF.name()); 
     String srvimpl = tplu.getClassName(Tid.SCALA_DAO_SERVICE_IMPL.name()); 
     String mock = tplu.getClassName(Tid.SCALA_ENTITY_MOCK.name()); 
     impu.addImport(cc);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(classname );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(srvintf );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_27);
     for (CrudMethod cm : iu.getAllCrudMethods()) { //TODO 
     } 
    stringBuffer.append(TEXT_28);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_29);
    return stringBuffer.toString();
  }
}
