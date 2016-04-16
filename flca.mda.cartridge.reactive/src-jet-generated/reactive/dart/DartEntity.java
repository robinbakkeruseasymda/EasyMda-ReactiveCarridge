package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DartEntity
{
  protected static String nl;
  public static synchronized DartEntity create(String lineSeparator)
  {
    nl = lineSeparator;
    DartEntity result = new DartEntity();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ";" + NL + " " + NL + "import '";
  protected final String TEXT_4 = "';" + NL + " " + NL + "class ";
  protected final String TEXT_5 = " extends Object with Observable {" + NL + "  //properties" + NL + "  String fd = null;";
  protected final String TEXT_6 = NL + "  ";
  protected final String TEXT_7 = NL + "  " + NL + "  // constructor & factories";
  protected final String TEXT_8 = NL + "  ";
  protected final String TEXT_9 = "() {}" + NL + "" + NL + "  factory ";
  protected final String TEXT_10 = ".clone(";
  protected final String TEXT_11 = " s) {";
  protected final String TEXT_12 = NL + "    ";
  protected final String TEXT_13 = " r = new ";
  protected final String TEXT_14 = "();" + NL + "    r.fd = s.fd;";
  protected final String TEXT_15 = NL + "    ";
  protected final String TEXT_16 = NL + "    return r;" + NL + "  }" + NL + "" + NL + "  factory ";
  protected final String TEXT_17 = ".fromJson(json) {" + NL + "    Map map = JSON.decode(json);" + NL + "    return new ";
  protected final String TEXT_18 = ".fromMap(map);" + NL + "  }" + NL + "" + NL + "  factory ";
  protected final String TEXT_19 = ".fromMap(map) {";
  protected final String TEXT_20 = NL + "    ";
  protected final String TEXT_21 = " r = new ";
  protected final String TEXT_22 = "();" + NL + "    r.fd = map['fd'];";
  protected final String TEXT_23 = NL + "    r.";
  protected final String TEXT_24 = " = map['";
  protected final String TEXT_25 = "'];";
  protected final String TEXT_26 = NL + "    r.";
  protected final String TEXT_27 = " = ";
  protected final String TEXT_28 = ".valueOf(map['";
  protected final String TEXT_29 = "']['value']);";
  protected final String TEXT_30 = NL + "    r.";
  protected final String TEXT_31 = " = DateUtils.varToDate(map['";
  protected final String TEXT_32 = "']);";
  protected final String TEXT_33 = NL + "    r.";
  protected final String TEXT_34 = " = map['";
  protected final String TEXT_35 = "'];";
  protected final String TEXT_36 = NL + "    var _";
  protected final String TEXT_37 = " = map['";
  protected final String TEXT_38 = "'];";
  protected final String TEXT_39 = NL + "    if (_";
  protected final String TEXT_40 = " != null) {" + NL + "       r.";
  protected final String TEXT_41 = " = _";
  protected final String TEXT_42 = ";" + NL + "    }";
  protected final String TEXT_43 = NL + "    if (_";
  protected final String TEXT_44 = " != null) {" + NL + "       r.";
  protected final String TEXT_45 = " = new ";
  protected final String TEXT_46 = ".fromMap(_";
  protected final String TEXT_47 = ");" + NL + "    }";
  protected final String TEXT_48 = NL + "    List _";
  protected final String TEXT_49 = "_list = map['";
  protected final String TEXT_50 = "'];" + NL + "    if (_";
  protected final String TEXT_51 = "_list != null) {" + NL + "      _";
  protected final String TEXT_52 = "_list.forEach((e) => r.";
  protected final String TEXT_53 = ".add(new ";
  protected final String TEXT_54 = ".fromMap(e)));" + NL + "    }";
  protected final String TEXT_55 = NL + "    return r;" + NL + "  }" + NL + "" + NL + "  Map toJson() {" + NL + "    Map map = new Map();";
  protected final String TEXT_56 = NL + "    map['";
  protected final String TEXT_57 = "'] = this.";
  protected final String TEXT_58 = ";";
  protected final String TEXT_59 = NL + "    map['";
  protected final String TEXT_60 = "'] = DateUtils.dateToJson(this.";
  protected final String TEXT_61 = ");";
  protected final String TEXT_62 = NL + "    map['";
  protected final String TEXT_63 = "'] = this.";
  protected final String TEXT_64 = ";";
  protected final String TEXT_65 = NL + "    map['";
  protected final String TEXT_66 = "'] = this.";
  protected final String TEXT_67 = ";";
  protected final String TEXT_68 = NL + "    //todo onetomany" + NL + "    " + NL + "    return map;" + NL + "  }" + NL + "" + NL + "  @override" + NL + "  String toString() {";
  protected final String TEXT_69 = NL + "    return \"";
  protected final String TEXT_70 = "\";" + NL + "  }" + NL + "} ";

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
     FilenameHelper fnh = new FilenameHelper(); 
     NameUtils nu = new NameUtils();
     TemplateUtils tplu = new TemplateUtils();
     AppUtils au = new AppUtils(); 
     Object element = arg.getElement(); 
     Class cc = element.getClass(); 
     String classname = nu.getCurrentClassname();
     String pck = nu.getCurrentPackage();
     String entity = tplu.getClassName(Tid.DART_ENTITY.name());
     String capname = nu.capName(entity); 
     String uncapname = nu.uncapName(entity); 
     String entityUC = entity.toUpperCase(); 
     String entityLC = entity.toLowerCase(); 
     boolean isSubClass = tu.isSubClass(cc); 
     boolean hasSubClasses = tu.hasSubClasses(cc); 
     boolean isBaseClass = tu.isBaseClass(cc); 
     boolean isAbstract = tu.hasAbstractAnnotation(); 
     String idname = tu.getIdName(cc); String idtype = tu.getIdTypeName(); 
     String pkName = nu.capName(tu.getIdName(cc)); String pkType = tu.getIdTypeName(); 
     String browseForm = tplu.getClassName(Tid.DART_ENTITY_BROWSE.name()); 
     String detailForm = tplu.getClassName(Tid.DART_ENTITY_FORM.name()); 
     String searchform = tplu.getClassName(Tid.DART_ENTITY_SEARCH.name());
     String daoservice = tplu.getClassName(Tid.DART_DAO_SERVICE.name());
     String appname = au.getApplicationName(); 
     String appConstants = tplu.getAppClassName(Tid.DART_APP_CONSTANTS.name());
     String serviceBase = tplu.getClassName(Tid.DART_APP_SERVICE_BASE.name());
     String pubpath = fnh.getRelativeToRootPath(element.getClass(), arg.getTemplate()) + "pub"; 
     String dartlib = fnh.getRelativeToRootPath(element.getClass(), arg.getTemplate()) + appname.toLowerCase() + "_library.dart"; 
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
     FwSelectType DISC_FLD = FwSelectType.DISCRIMINATOR; 
     FwSelectType SIMPLE_FLD = FwSelectType.SIMPLE; 
     FwSelectType ENUM_FLD = FwSelectType.ENUM; 
     FwSelectType SPECIAL_FLD = FwSelectType.SPECIAL; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dartlib);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(tu.format(tu.getAllFields(cc), "%t %n = %d;", "\n  "));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(tu.format(tu.getAllFields(cc), "r.%n = s.%n;", "\n    "));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_22);
     if (!tu.hasExplicitId(cc) && !isSubClass) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_25);
     } 
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
       if (fw.isEnum()) { 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_29);
       } else if (fw.isDate()) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_32);
       } else { 
    stringBuffer.append(TEXT_33);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_35);
       } 
     }  
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD, O2O_FLD)) { 
    stringBuffer.append(TEXT_36);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_38);
        if (fw.isSimple()) { 
    stringBuffer.append(TEXT_39);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_42);
        } else { 
    stringBuffer.append(TEXT_43);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_47);
        } 
     } 
     for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) { 
    stringBuffer.append(TEXT_48);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(fw.genericTypeName());
    stringBuffer.append(TEXT_54);
     } 
    stringBuffer.append(TEXT_55);
     if (!tu.hasExplicitId(cc) && !isSubClass) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_58);
     } 
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
       if (fw.isDate()) { 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_61);
       } else { 
    stringBuffer.append(TEXT_62);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_64);
       } 
     }  
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD, O2O_FLD)) { 
    stringBuffer.append(TEXT_65);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_67);
     } 
    stringBuffer.append(TEXT_68);
     String tostr = ""; 
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
        tostr += fw.name() + "=$" + fw.name() + ",";  
     } 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(tostr);
    stringBuffer.append(TEXT_70);
    return stringBuffer.toString();
  }
}
