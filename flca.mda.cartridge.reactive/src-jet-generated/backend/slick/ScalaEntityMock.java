package backend.slick;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaEntityMock
{
  protected static String nl;
  public static synchronized ScalaEntityMock create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaEntityMock result = new ScalaEntityMock();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " " + NL + "object ";
  protected final String TEXT_5 = " {";
  protected final String TEXT_6 = NL + "\tdef make(";
  protected final String TEXT_7 = ") : ";
  protected final String TEXT_8 = " = {" + NL + "  " + NL + "\tval result = new ";
  protected final String TEXT_9 = "(";
  protected final String TEXT_10 = ")" + NL + "\t";
  protected final String TEXT_11 = NL + "\tresult" + NL + "\t}";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = "\t  " + NL + "" + NL + "\tdef makeRandom(";
  protected final String TEXT_14 = ":Option[";
  protected final String TEXT_15 = "]=None) : ";
  protected final String TEXT_16 = " = {" + NL + "\t\timport ";
  protected final String TEXT_17 = "._" + NL + "\t\tval result = new ";
  protected final String TEXT_18 = "(";
  protected final String TEXT_19 = ")" + NL + "\t\t";
  protected final String TEXT_20 = NL + "\t\tresult.";
  protected final String TEXT_21 = " = Set(";
  protected final String TEXT_22 = ".makeRandom(None), ";
  protected final String TEXT_23 = ".makeRandom(None))";
  protected final String TEXT_24 = "     ";
  protected final String TEXT_25 = NL + "\t\tresult.";
  protected final String TEXT_26 = " = Set(";
  protected final String TEXT_27 = ".makeRandom(None), ";
  protected final String TEXT_28 = ".makeRandom(None))     ";
  protected final String TEXT_29 = NL + "\t\tresult" + NL + "\t}" + NL;
  protected final String TEXT_30 = NL + NL + "\tdef makeRandom(";
  protected final String TEXT_31 = ":Option[";
  protected final String TEXT_32 = "]=None) : ";
  protected final String TEXT_33 = " = {" + NL + "\t\timport ";
  protected final String TEXT_34 = "._" + NL + "\t\tval result = LoanMock.makeRandom() //TODO" + NL + "\t\tresult" + NL + "\t}" + NL;
  protected final String TEXT_35 = NL + "  " + NL + "\tdef hasAllFetched(obj:";
  protected final String TEXT_36 = ") : Boolean = {";
  protected final String TEXT_37 = NL + "\t\tval b";
  protected final String TEXT_38 = " = obj.";
  protected final String TEXT_39 = ".isDefined    ";
  protected final String TEXT_40 = NL + "    \tval b";
  protected final String TEXT_41 = " = !obj.";
  protected final String TEXT_42 = ".isEmpty && obj.";
  protected final String TEXT_43 = ".forall(";
  protected final String TEXT_44 = ".hasAllFetched(_))";
  protected final String TEXT_45 = NL + "\t\ttrue";
  protected final String TEXT_46 = NL + "\t\t";
  protected final String TEXT_47 = NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_48 = NL;

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
     if (!isBaseClass) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, OFD_FLD), "%n:%o[%t]=%d", ",\n\t"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAR_FLD, OFD_FLD), "%n", ",\n\t\t"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD, O2M_FLD), "result.%n = %n", "\n\t"));
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_12);
     if (!isBaseClass) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(randomutils);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAR_FLD, OFD_FLD), "%n", ",\n\t"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD, O2M_FLD,M2O_FLD), "result.%n = %f<randomValue()>", "\n\t\t"));
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD)) { 
        if (!tu.isNestedIn(cc, fw.genericType()) && !fw.isSimple() ) { 
          String nestedmock = tplu.getClassName(fw.genericType(), Tid.SCALA_ENTITY_MOCK.name());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(nestedmock);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(nestedmock);
    stringBuffer.append(TEXT_23);
        } 
    stringBuffer.append(TEXT_24);
     } 
     for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
          String nestedmock = tplu.getClassName(fw.genericType(), Tid.SCALA_ENTITY_MOCK.name());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(nestedmock);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(nestedmock);
    stringBuffer.append(TEXT_28);
     } 
    stringBuffer.append(TEXT_29);
     } else { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(randomutils);
    stringBuffer.append(TEXT_34);
     } 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_36);
     int cnt = 0; 
     for (Fw fw : tu.getFieldsInc(cc, O2O_FLD)) { 
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cnt++);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_39);
     } 
      for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
        String nestedmock = tplu.getClassName(fw.genericType(), Tid.SCALA_ENTITY_MOCK.name());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cnt++);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(nestedmock);
    stringBuffer.append(TEXT_44);
     } 
     String s = ""; for (int i=0;i<cnt;i++) { s += "b" + i + " && ";} 
      if (s.trim().isEmpty()) {
    stringBuffer.append(TEXT_45);
      } else { 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(nu.trimRight(s, "&&"));
      } 
    stringBuffer.append(TEXT_47);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_48);
    return stringBuffer.toString();
  }
}
