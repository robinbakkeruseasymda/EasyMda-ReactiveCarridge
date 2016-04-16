package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DartDetailForm
{
  protected static String nl;
  public static synchronized DartDetailForm create(String lineSeparator)
  {
    nl = lineSeparator;
    DartDetailForm result = new DartDetailForm();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ";" + NL + " " + NL + "import '";
  protected final String TEXT_4 = "';" + NL + " " + NL + "@CustomTag('";
  protected final String TEXT_5 = "-form')" + NL + "class ";
  protected final String TEXT_6 = " extends PolymerElement with AbstractForm {" + NL + "  " + NL + "  @override ShadowRoot get formRoot => shadowRoot;" + NL;
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = "  " + NL + "  @override List<String> get fieldIds =>  [";
  protected final String TEXT_9 = "];" + NL + "  ";
  protected final String TEXT_10 = NL + "  ";
  protected final String TEXT_11 = " _";
  protected final String TEXT_12 = ";" + NL + "  " + NL + "  @observable ";
  protected final String TEXT_13 = NL + "  ";
  protected final String TEXT_14 = " get ";
  protected final String TEXT_15 = " => _";
  protected final String TEXT_16 = ";" + NL + "  " + NL + "  void set ";
  protected final String TEXT_17 = "(";
  protected final String TEXT_18 = " val) {" + NL + "    _";
  protected final String TEXT_19 = " = val;" + NL + "    updateView();" + NL + "  }" + NL + "  ";
  protected final String TEXT_20 = NL + "  ";
  protected final String TEXT_21 = ".created(): super.created() {} " + NL + "  " + NL + "  @override ";
  protected final String TEXT_22 = " updateModel() {";
  protected final String TEXT_23 = NL + "    ";
  protected final String TEXT_24 = " r = new ";
  protected final String TEXT_25 = ".clone(";
  protected final String TEXT_26 = ");" + NL;
  protected final String TEXT_27 = NL + "    r.";
  protected final String TEXT_28 = " = ";
  protected final String TEXT_29 = ".values[getSelectedIndex('#id_";
  protected final String TEXT_30 = "')];";
  protected final String TEXT_31 = NL + "    r.";
  protected final String TEXT_32 = " = getValue('#id_";
  protected final String TEXT_33 = "');";
  protected final String TEXT_34 = "    " + NL + "    return r;" + NL + "  }" + NL + "  " + NL + "  @override void updateView() {";
  protected final String TEXT_35 = NL + "  setValue('#id_";
  protected final String TEXT_36 = "', ";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = ");  ";
  protected final String TEXT_39 = "  ";
  protected final String TEXT_40 = NL + "    setCombobox(\"#id_";
  protected final String TEXT_41 = "\", ";
  protected final String TEXT_42 = ".";
  protected final String TEXT_43 = ", ";
  protected final String TEXT_44 = ".values);";
  protected final String TEXT_45 = NL + "    setValue('#id_";
  protected final String TEXT_46 = "', ";
  protected final String TEXT_47 = ".";
  protected final String TEXT_48 = ");  ";
  protected final String TEXT_49 = NL + "  }" + NL + "" + NL + "  // observable Maps to populate combobox(s)";
  protected final String TEXT_50 = NL + "    @observable Map get ";
  protected final String TEXT_51 = "_values => ";
  protected final String TEXT_52 = ".selectMap;";
  protected final String TEXT_53 = "      " + NL + "  " + NL + "} " + NL;
  protected final String TEXT_54 = NL;

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
     if (!tu.hasAnnotation(cc, mda.annotation.Gui.class)) return ""; 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dartlib);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(entityLC);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
     String fieldIds = ""; 
     if (!tu.hasExplicitId(cc) && !isSubClass) {
     fieldIds += "\"#id_" + idname + "\","; 
     } 
     for (Fw fw : tu.getAllFields(cc)) { 
     String fldname = fw.name(); fieldIds += "\"#id_" + fldname +"\","; 
    stringBuffer.append(TEXT_7);
     }  
    stringBuffer.append(TEXT_8);
    stringBuffer.append(nu.trimRight(fieldIds,","));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_26);
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
     String fldname = fw.name(); 
       if (fw.isEnum()) { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_30);
       } else { 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_33);
       } 
     }  
    stringBuffer.append(TEXT_34);
     if (!tu.hasExplicitId(cc) && !isSubClass) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_38);
     } 
    stringBuffer.append(TEXT_39);
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
     String fldname = fw.name(); 
       if (fw.isEnum()) { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_44);
       } else { 
    stringBuffer.append(TEXT_45);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_48);
       } 
     }  
    stringBuffer.append(TEXT_49);
     for (Fw fw : tu.getFieldsInc(cc, ENUM_FLD)) { 
     String fldname = fw.name(); 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(fw.typeName());
    stringBuffer.append(TEXT_52);
     }  
    stringBuffer.append(TEXT_53);
    stringBuffer.append(TEXT_54);
    return stringBuffer.toString();
  }
}
