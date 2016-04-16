package backend.slick;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;
import java.lang.annotation.*;

public class ScalaEntity
{
  protected static String nl;
  public static synchronized ScalaEntity create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaEntity result = new ScalaEntity();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = "\t";
  protected final String TEXT_5 = "\t";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = " " + NL + "/**" + NL + " Generated \"Fd\" class that can be used to define whether or not to fetch (deeply) nested classes." + NL + "*/" + NL + " case class ";
  protected final String TEXT_9 = "Fd(";
  protected final String TEXT_10 = ") extends FetchDepth" + NL + " " + NL + "/**" + NL + " generated class" + NL + "*/" + NL + "@JsonIdentityInfo(generator = classOf[ObjectIdGenerators.UUIDGenerator])" + NL + "class ";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = ") " + NL + "\textends ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = "{" + NL + " " + NL + "\t";
  protected final String TEXT_15 = NL + "\tprivate var _";
  protected final String TEXT_16 = "Id :Option[Long] = None";
  protected final String TEXT_17 = NL + "\tprivate var _";
  protected final String TEXT_18 = " : Option[";
  protected final String TEXT_19 = "] = None";
  protected final String TEXT_20 = NL + "  " + NL + "\tprivate var _dirty:Boolean = false" + NL + "\t" + NL + "\t// getters " + NL + "\t";
  protected final String TEXT_21 = NL + "\tdef ";
  protected final String TEXT_22 = " = _";
  protected final String TEXT_23 = " " + NL + "\tdef ";
  protected final String TEXT_24 = "  = _";
  protected final String TEXT_25 = " ";
  protected final String TEXT_26 = NL + "\tdef ";
  protected final String TEXT_27 = " = _";
  protected final String TEXT_28 = " ";
  protected final String TEXT_29 = NL + "\t" + NL + "\t// setters" + NL + "\t";
  protected final String TEXT_30 = NL + "\tdef ";
  protected final String TEXT_31 = "_= (value:Option[";
  protected final String TEXT_32 = "]) = {" + NL + "\t\t_";
  protected final String TEXT_33 = " = value" + NL + "\t\tif (_";
  protected final String TEXT_34 = ".isDefined) _";
  protected final String TEXT_35 = "Id = ";
  protected final String TEXT_36 = ".get.id else _";
  protected final String TEXT_37 = "Id = None" + NL + "\t\t_dirty = true" + NL + "\t}" + NL + "\tdef ";
  protected final String TEXT_38 = " = _";
  protected final String TEXT_39 = " " + NL + "\tdef ";
  protected final String TEXT_40 = "Id_= (value:Option[Long]) = { _";
  protected final String TEXT_41 = "Id = value; _dirty = true; }";
  protected final String TEXT_42 = NL + "  ";
  protected final String TEXT_43 = NL + "\tdef ";
  protected final String TEXT_44 = "_= (value:Option[";
  protected final String TEXT_45 = "]) = { " + NL + "\t\t_";
  protected final String TEXT_46 = " = value; " + NL + "\t\tif (_";
  protected final String TEXT_47 = ".isDefined) _";
  protected final String TEXT_48 = "Id = ";
  protected final String TEXT_49 = ".get.id else _";
  protected final String TEXT_50 = "Id = None" + NL + "\t\t_dirty = true; " + NL + "\t}" + NL + "\t " + NL + "\tdef ";
  protected final String TEXT_51 = "_= (value:Option[";
  protected final String TEXT_52 = "]) = { _";
  protected final String TEXT_53 = " = value; _dirty = true; }";
  protected final String TEXT_54 = NL + "\tdef ";
  protected final String TEXT_55 = "_= (value:Option[Long]) = { _";
  protected final String TEXT_56 = " = value; _dirty = true; }";
  protected final String TEXT_57 = NL + "\t" + NL + "\t";
  protected final String TEXT_58 = "def isDirty = _dirty" + NL + " ";
  protected final String TEXT_59 = NL + "  /**" + NL + "  * use this method to copy ";
  protected final String TEXT_60 = NL + "  */ " + NL + "  def copy";
  protected final String TEXT_61 = "(";
  protected final String TEXT_62 = ") = {" + NL + "  \tval result = new ";
  protected final String TEXT_63 = "(";
  protected final String TEXT_64 = ")" + NL + " " + NL + "\t";
  protected final String TEXT_65 = NL + "\t//result.";
  protected final String TEXT_66 = " = this.";
  protected final String TEXT_67 = NL + "\tresult.";
  protected final String TEXT_68 = " = this.";
  protected final String TEXT_69 = NL + "\tresult.";
  protected final String TEXT_70 = " = this.";
  protected final String TEXT_71 = " ";
  protected final String TEXT_72 = NL + "\tresult._dirty = false" + NL + "  \tresult" + NL + "  }";
  protected final String TEXT_73 = "  " + NL + "}";
  protected final String TEXT_74 = NL;

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
 
     String basepck = tplu.getPackage(Tid.SCALA_BASECLASS.name());
    stringBuffer.append(TEXT_4);
     String baseclass = tplu.getClassName(Tid.SCALA_BASECLASS.name());
    stringBuffer.append(TEXT_5);
     impu.addImport("com.fasterxml.jackson.annotation.*"); 
     impu.addImport(basepck + ".FetchDepth");
     impu.addImport(basepck + "." + baseclass);
    stringBuffer.append(TEXT_6);
     String extendsClause = "BaseClass"; 
     String overstr = ""; 
     String withClause = ""; 
     String discrValue = ""; 
     String classVals = tu.format(tu.getFieldsExc(cc, VAR_FLD), "%v %n:%o[%t]=%d", ",\n\t"); 
     Annotation discrAnno = tu.getAnnotation(cc, mda.annotation.jpa.DiscriminatorValue.class);
    stringBuffer.append(TEXT_7);
     if (isSubClass) { 
        discrValue = (discrAnno!=null) ? ((mda.annotation.jpa.DiscriminatorValue) discrAnno).value() : "?DiscriminatorValue?"; 
        String superclassVals = tu.format(tu.getFieldsExc(superClass, VAR_FLD, DISC_FLD), "%n", ","); 
        superclassVals += ", " + "\"" + discrValue + "\""; 
    	  overstr = "override "; 
    	  extendsClause = superClassname + "(" + superclassVals + ")"; 
    	  classVals = tu.format(tu.getFieldsExc(cc, VAR_FLD), "override %v %n:%o[%t]=%d", ",\n\t\t"); 
     } else if (isBaseClass) { 
        discrValue = (discrAnno!=null) ? ((mda.annotation.jpa.DiscriminatorValue) discrAnno).value() : "discriminator"; 
    	  classVals = tu.format(tu.getFieldsExc(cc, VAR_FLD), "%v %n:%o[%t]=%d", ",\n\t\t"); 
        classVals += ", val " + discrValue + ":String=null"; 
     } 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(tu.getFetchModelParams(cc));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(classVals);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(extendsClause);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(withClause);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD), "private %v _%n : %o[%t] = %d", "\n\t"));
     for (Fw fw : tu.getFieldsInc(cc, O2O_FLD)) { 
        String n = fw.name(); 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_16);
     } 
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD)) { 
       String n = fw.name(); String fldIdType = tu.getIdType(fw.genericType()).getSimpleName(); 
	String fldIdName = tu.getIdName(fw.type()); 
       if (fw.isEntity()) { 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(fldIdType);
    stringBuffer.append(TEXT_19);
       } 
     } //for loop 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD,M2O_FLD), "def %n = _%n", "\n\t"));
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD)) { 
       String n = fw.name(); String fldIdType = tu.getIdType(fw.genericType()).getSimpleName(); 
	String fldIdName = tu.getIdName(cc); 
       if (fw.isEntity()) { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_25);
       } else { 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_28);
       }  
     } //for loop 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD,M2O_FLD,O2O_FLD), "def %n_= (value:%o[%t]) = {_%n = value; _dirty = true;}", "\n\t"));
     for (Fw fw : tu.getFieldsInc(cc, O2O_FLD)) { 
        String n = fw.name(); 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(fw.genericTypeName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_41);
     } 
    stringBuffer.append(TEXT_42);
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD)) { 
       String n = fw.name(); String fldIdType = tu.getIdType(fw.type()).getSimpleName(); 
	String fldIdName = tu.getIdName(fw.genericType()); String fldType = fw.genericTypeName(); 
       if (fw.isEntity()) { 
    stringBuffer.append(TEXT_43);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(fldType);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(fldIdType);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_53);
       } else { 
    stringBuffer.append(TEXT_54);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_56);
       } 
     } //for loop 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(overstr);
    stringBuffer.append(TEXT_58);
     if (!isBaseClass) { 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAR_FLD), "%n:%o[%t]=%d", ",\n\t\t"));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAR_FLD), "%n", ",") );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(tu.format(tu.getFieldsExc(cc, VAL_FLD,M2O_FLD), "result.%n = this.%n", "\n\t"));
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD)) { 
       String n = fw.name(); 
       if (fw.isEntity()) { 
    stringBuffer.append(TEXT_65);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(n);
    stringBuffer.append(tu.ID_SUFFIX);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(n);
       } else { 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(n);
    stringBuffer.append(TEXT_71);
       } 
     } //for loop 
    stringBuffer.append(TEXT_72);
     } 
    stringBuffer.append(TEXT_73);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_74);
    return stringBuffer.toString();
  }
}
