package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DartDetailFormHtml
{
  protected static String nl;
  public static synchronized DartDetailFormHtml create(String lineSeparator)
  {
    nl = lineSeparator;
    DartDetailFormHtml result = new DartDetailFormHtml();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_2 = "/form_fields/int_form_field.html\"> " + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_3 = "/form_fields/dec_form_field.html\">" + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_4 = "/form_fields/string_form_field.html\">" + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_5 = "/form_fields/date_form_field.html\">" + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_6 = "/form_fields/bool_form_field.html\">" + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_7 = "/form_fields/radio_form_field.html\">" + NL + "<link rel=\"import\" href=\"";
  protected final String TEXT_8 = "/form_fields/combo_form_field.html\">" + NL + "" + NL + "<polymer-element name=\"";
  protected final String TEXT_9 = "-form\" >" + NL + "  <template>" + NL + "   <form method=\"post\">";
  protected final String TEXT_10 = NL + "     <int-form-field id=\"id_";
  protected final String TEXT_11 = "\" label=\"";
  protected final String TEXT_12 = "\" req=\"true\" editable=\"false\" ></int-form-field>";
  protected final String TEXT_13 = "  ";
  protected final String TEXT_14 = NL + "     <combo-form-field id=\"id_";
  protected final String TEXT_15 = "\" items=\"{{";
  protected final String TEXT_16 = "_values}}\" prompt=\"choose..\" label=\"";
  protected final String TEXT_17 = "\" ></combo-form-field>";
  protected final String TEXT_18 = " " + NL + "     <";
  protected final String TEXT_19 = " id=\"id_";
  protected final String TEXT_20 = "\" label=\"";
  protected final String TEXT_21 = "\" ></";
  protected final String TEXT_22 = ">";
  protected final String TEXT_23 = NL + "      <div><input type=\"submit\" on-click=\"{{submitForm}}\"></div>" + NL + "    </form>" + NL + "  </template>" + NL + "  <script type=\"application/dart\" src=\"";
  protected final String TEXT_24 = "_form.dart\"></script>" + NL + "</polymer-element>";
  protected final String TEXT_25 = NL;

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
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(pubpath);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_9);
     if (!tu.hasExplicitId(cc) && !isSubClass) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
     for (Fw fw : tu.getFieldsInc(cc, SIMPLE_FLD)) { 
     String fldname = fw.name(); 
       if (fw.isEnum()) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_17);
       } else { 
       String formfld = tu.getFormField(fw.type()); 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(formfld);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(formfld);
    stringBuffer.append(TEXT_22);
       } 
     }  
    stringBuffer.append(TEXT_23);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
