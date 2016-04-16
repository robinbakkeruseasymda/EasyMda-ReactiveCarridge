package backend.misc;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class Dto
{
  protected static String nl;
  public static synchronized Dto create(String lineSeparator)
  {
    nl = lineSeparator;
    Dto result = new Dto();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = "\t";
  protected final String TEXT_5 = "\t";
  protected final String TEXT_6 = NL + NL + "/**" + NL + " generated class" + NL + "*/";
  protected final String TEXT_7 = NL + "class ";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = ") {" + NL + "\t";
  protected final String TEXT_10 = NL + "\t" + NL + "\tprivate var _dirty:Boolean = false" + NL + "\t" + NL + "\t// getters & setters" + NL + "\t";
  protected final String TEXT_11 = NL + "\t";
  protected final String TEXT_12 = NL + "\tdef dirty = _dirty" + NL + "\t" + NL + "}";
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
 
     FwSelectType[] allOutputOpts = new FwSelectType[] {ID_FLD, VAR_FLD}; 
     String basepck = tplu.getPackage(Tid.SCALA_BASECLASS.name());
    stringBuffer.append(TEXT_4);
     String baseclass = tplu.getClassName(Tid.SCALA_BASECLASS.name());
    stringBuffer.append(TEXT_5);
     impu.addImport(basepck + ".FetchDepth");
     impu.addImport(basepck + "." + baseclass);
    stringBuffer.append(TEXT_6);
     List<Fw> fields = tu.getFieldsInc(cc, VAL_FLD); 
     fields = tu.filterOut(cc, fields, SPECIAL_FLD); 
     List<String> valList = new ArrayList<String>(); 
     for (Fw fw : fields) { valList.add(fw.format("%v %n:%o[%t]=%d, ")); } 
     String classVals = nu.join(valList, "\n\t"); 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(classVals);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD), "private %v _%n:%o[%t]=%d", "\n\t"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD), "def %n = _%n", "\n\t"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD), "def %n_= (value:%o[%t]) = {_%n=value; _dirty=true;}", "\n\t"));
    stringBuffer.append(TEXT_12);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
