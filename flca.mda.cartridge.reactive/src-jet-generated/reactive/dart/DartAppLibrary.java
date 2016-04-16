package reactive.dart;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import mda.annotation.crud.*;
import reactive.*;

public class DartAppLibrary
{
  protected static String nl;
  public static synchronized DartAppLibrary create(String lineSeparator)
  {
    nl = lineSeparator;
    DartAppLibrary result = new DartAppLibrary();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "library ";
  protected final String TEXT_2 = ";" + NL + " " + NL + "export 'dart:html';" + NL + "export 'dart:convert';" + NL + " " + NL + "export 'package:polymer/polymer.dart';" + NL + "" + NL + "export 'package:pubsub/pubsub.dart';" + NL + "export 'package:pubsub/message.dart';" + NL + " " + NL + "export 'pub/form_fields/form_fields.dart';" + NL + "export 'pub/date_utils.dart';" + NL + "export 'pub/enum.dart';" + NL + " " + NL + "export 'view/left_tree.dart';" + NL + "export 'view/main_view.dart';" + NL + " ";
  protected final String TEXT_3 = NL + "export '";
  protected final String TEXT_4 = "';";
  protected final String TEXT_5 = NL + "  ";
  protected final String TEXT_6 = NL + " ";
  protected final String TEXT_7 = NL + "export '";
  protected final String TEXT_8 = "';";
  protected final String TEXT_9 = NL + " ";
  protected final String TEXT_10 = NL + "export '";
  protected final String TEXT_11 = "';";
  protected final String TEXT_12 = NL + " ";
  protected final String TEXT_13 = NL + "export '";
  protected final String TEXT_14 = "';";
  protected final String TEXT_15 = NL + " ";
  protected final String TEXT_16 = NL + "export '";
  protected final String TEXT_17 = "';";
  protected final String TEXT_18 = NL + " ";
  protected final String TEXT_19 = NL + "export '";
  protected final String TEXT_20 = "';";
  protected final String TEXT_21 = NL + " ";

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
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_2);
     String entities[] = new String[] {Tid.DART_ENTITY.name()}; 
     for (Class clz : tu.getAllModelEntities()) { 
       for (String name : entities) { 
       String impname = tu.getLibraryImport(clz, tplu.getTemplate(name)); 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_4);
       } 
    stringBuffer.append(TEXT_5);
     } 
    stringBuffer.append(TEXT_6);
     String guiEntities[] = new String[] {Tid.DART_ENTITY_FORM.name(), Tid.DART_ENTITY_BROWSE.name() }; 
     for (Class clz : tu.getAllModelEntities()) { 
       if (tu.hasAnnotation(clz, mda.annotation.Gui.class)) { 
         for (String name : guiEntities) { 
           String impname = tu.getLibraryImport(clz, tplu.getTemplate(name)); 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_8);
         } 
       } 
     } 
    stringBuffer.append(TEXT_9);
     String srvEntities[] = new String[] {Tid.DART_ENTITY_CTRL.name(), Tid.DART_DAO_SERVICE.name() }; 
     for (Class clz : tu.getAllModelEntities()) { 
       if (tu.hasAnnotation(clz, mda.annotation.RestService.class)) { 
         for (String name : srvEntities) { 
           String impname = tu.getLibraryImport(clz, tplu.getTemplate(name)); 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_11);
         } 
       } 
     } 
    stringBuffer.append(TEXT_12);
     String enumNames[] = new String[] {Tid.DART_ENUM.name() }; 
     for (Class clz : tu.getAllModelEnums()) { 
       for (String name : enumNames) { 
         String impname = tu.getLibraryImport(clz, tplu.getTemplate(name)); 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_14);
       } 
     } 
    stringBuffer.append(TEXT_15);
     String serviceNames[] = new String[] {Tid.DART_SERVICE.name(), Tid.DART_SERVICE_IMPL.name() }; 
     for (Class clz : tu.getAllModelServices()) { 
       for (String name : serviceNames) { 
         String impname = tu.getLibraryImport(clz, tplu.getTemplate(name)); 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_17);
       } 
     } 
    stringBuffer.append(TEXT_18);
     String appTemplateNames[] = new String[] {Tid.DART_APP_CONSTANTS.name(), Tid.DART_APP_SERVICE_BASE.name(), Tid.DART_APP_MAINCTRL.name()}; 
     for (String name : appTemplateNames) { 
       String impname = tu.getLibraryImport(Object.class, tplu.getTemplate(name)); //TODO 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(impname);
    stringBuffer.append(TEXT_20);
     } 
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
