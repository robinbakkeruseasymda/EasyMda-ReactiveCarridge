package backend.misc;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaDtoMock
{
  protected static String nl;
  public static synchronized ScalaDtoMock create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaDtoMock result = new ScalaDtoMock();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " " + NL + "object ";
  protected final String TEXT_5 = " {";
  protected final String TEXT_6 = NL + NL + "\tdef make(";
  protected final String TEXT_7 = ") : ";
  protected final String TEXT_8 = " = {" + NL + "  " + NL + "\t\tval result = new ";
  protected final String TEXT_9 = "(";
  protected final String TEXT_10 = ")" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_11 = NL + "\t\tresult" + NL + "\t}" + NL + "\t" + NL + "\tdef makeRandom(";
  protected final String TEXT_12 = ":Option[";
  protected final String TEXT_13 = "]=None) : ";
  protected final String TEXT_14 = " = {" + NL + "\t\timport ";
  protected final String TEXT_15 = "._" + NL + "\t\tval result = new ";
  protected final String TEXT_16 = "(";
  protected final String TEXT_17 = ")" + NL + "\t\t";
  protected final String TEXT_18 = NL + "\t\tresult" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_19 = NL;

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
 
     String mock = tplu.getClassName(Tid.SCALA_ENTITY_MOCK.name());
     String randomutils = tplu.getClassName(Tid.SCALA_RANDOMUTILS.name());
     FwSelectType[] opts1 = new FwSelectType[] {}; 
     FwSelectType[] opts2 = new FwSelectType[] {VAR_FLD}; 
     impu.addImport(cc);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_5);
     String makeArgs = tu.format(tu.getFieldsExc(cc, SPECIAL_FLD), "%n:%o[%t]=%d", ",\n\t\t"); 
     String valNames = tu.format(tu.getFieldsExc(cc, SPECIAL_FLD,VAR_FLD), "%n", ",\n\t\t"); 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(makeArgs );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(valNames);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD, O2M_FLD), "result.%n = %n", "\n\t\t"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(randomutils);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(valNames);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD, O2M_FLD, SPECIAL_FLD), "result.%n = %f<randomValue()>", "\n\t\t"));
    stringBuffer.append(TEXT_18);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
