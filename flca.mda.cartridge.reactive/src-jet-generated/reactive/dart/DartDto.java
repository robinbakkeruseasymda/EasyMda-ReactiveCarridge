package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DartDto
{
  protected static String nl;
  public static synchronized DartDto create(String lineSeparator)
  {
    nl = lineSeparator;
    DartDto result = new DartDto();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ";" + NL + "  " + NL + "import '";
  protected final String TEXT_4 = "';" + NL + "  " + NL + "class ";
  protected final String TEXT_5 = " extends Object with Observable {" + NL + "  //properties" + NL + "  List<int> ohc = [0];" + NL + "  String fd = null;";
  protected final String TEXT_6 = NL + "  ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = " = ";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL + "  " + NL + "  // constructor & factories";
  protected final String TEXT_11 = NL + "  ";
  protected final String TEXT_12 = "() {}" + NL + "" + NL + "  factory ";
  protected final String TEXT_13 = ".clone(";
  protected final String TEXT_14 = " s) {";
  protected final String TEXT_15 = NL + "    ";
  protected final String TEXT_16 = " r = new ";
  protected final String TEXT_17 = "();" + NL + "    r.ohc = s.ohc;" + NL + "    r.fd = s.fd;";
  protected final String TEXT_18 = NL + "    r.";
  protected final String TEXT_19 = " = s.";
  protected final String TEXT_20 = ";";
  protected final String TEXT_21 = NL + "    return r;" + NL + "  }" + NL + "" + NL + "  factory ";
  protected final String TEXT_22 = ".fromJson(json) {" + NL + "    Map map = JSON.decode(json);" + NL + "    return new ";
  protected final String TEXT_23 = ".fromMap(map);" + NL + "  }" + NL + "" + NL + "  factory ";
  protected final String TEXT_24 = ".fromMap(map) {";
  protected final String TEXT_25 = NL + "    ";
  protected final String TEXT_26 = " r = new ";
  protected final String TEXT_27 = "();" + NL + "    r.ohc = map['ohc'];" + NL + "    r.fd = map['fd'];";
  protected final String TEXT_28 = NL + "    r.";
  protected final String TEXT_29 = " = ";
  protected final String TEXT_30 = ".valueOf(map['";
  protected final String TEXT_31 = "']['value']);";
  protected final String TEXT_32 = NL + "    r.";
  protected final String TEXT_33 = " = map['";
  protected final String TEXT_34 = "'];";
  protected final String TEXT_35 = NL + "    r.";
  protected final String TEXT_36 = " = new ";
  protected final String TEXT_37 = ".fromMap(map['";
  protected final String TEXT_38 = "']);";
  protected final String TEXT_39 = NL + "    List _";
  protected final String TEXT_40 = "_list = map['";
  protected final String TEXT_41 = "'];" + NL + "    _";
  protected final String TEXT_42 = "_list.forEach((e) => r.";
  protected final String TEXT_43 = ".add(new ";
  protected final String TEXT_44 = ".fromMap(e)));";
  protected final String TEXT_45 = NL + "    return r;" + NL + "  }" + NL + "" + NL + "  Map toJson() {" + NL + "    Map map = new Map();";
  protected final String TEXT_46 = NL + "    map['";
  protected final String TEXT_47 = "'] = DateUtils.dateToJson(this.";
  protected final String TEXT_48 = ");";
  protected final String TEXT_49 = NL + "    map['";
  protected final String TEXT_50 = "'] = this.";
  protected final String TEXT_51 = ";";
  protected final String TEXT_52 = NL + "    map['";
  protected final String TEXT_53 = "'] = this.";
  protected final String TEXT_54 = ";";
  protected final String TEXT_55 = NL + "    //todo onetomany" + NL + "    " + NL + "    return map;" + NL + "  }" + NL + "" + NL + "  @override" + NL + "  String toString() {";
  protected final String TEXT_56 = NL + "    return \"";
  protected final String TEXT_57 = "\";" + NL + "  }" + NL + "} ";

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
     for (Fw fw : tu.getFieldsExc(cc, SPECIAL_FLD)) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(fw.getDefaultValue());
    stringBuffer.append(TEXT_9);
     }  
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
     for (Fw fw : tu.getFieldsExc(cc, SPECIAL_FLD)) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_20);
     }  
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_27);
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
       if (fw.isEnum()) { 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_31);
       } else { 
    stringBuffer.append(TEXT_32);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_34);
       } 
     }  
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD, O2O_FLD)) { 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_38);
     } 
     for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_44);
     } 
    stringBuffer.append(TEXT_45);
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
       if (fw.isDate()) { 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_48);
       } else { 
    stringBuffer.append(TEXT_49);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_51);
       } 
     }  
     for (Fw fw : tu.getFieldsInc(cc, M2O_FLD, O2O_FLD)) { 
    stringBuffer.append(TEXT_52);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_54);
     } 
    stringBuffer.append(TEXT_55);
     String tostr = ""; 
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
       tostr += fw.name() + "=$" + fw.name() + ",";  
     } 
    stringBuffer.append(TEXT_56);
    stringBuffer.append(tostr);
    stringBuffer.append(TEXT_57);
    return stringBuffer.toString();
  }
}
