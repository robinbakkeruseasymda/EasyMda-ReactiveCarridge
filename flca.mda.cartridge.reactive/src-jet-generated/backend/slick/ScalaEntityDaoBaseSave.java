package backend.slick;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaEntityDaoBaseSave
{
  protected static String nl;
  public static synchronized ScalaEntityDaoBaseSave create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaEntityDaoBaseSave result = new ScalaEntityDaoBaseSave();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "\tdef save";
  protected final String TEXT_3 = "(obj: ";
  protected final String TEXT_4 = ")(implicit session: Session): ";
  protected final String TEXT_5 = " = {" + NL + "\t\tval isNew = obj.id.isEmpty" + NL + "\t\tval isDirty = obj.isDirty" + NL + "\t\tvar r = if (isNew) insert";
  protected final String TEXT_6 = "(obj) else obj" + NL + "\t\tr = save";
  protected final String TEXT_7 = "Rels(r)" + NL + " \t\tr = if (isNew || isDirty) update";
  protected final String TEXT_8 = "(r) else r" + NL + "\t\tr = if (!isNew) deleteOneToManys(r) else r" + NL + "\t\tfinish";
  protected final String TEXT_9 = "(r, r.ofd)" + NL + "\t}" + NL + "\t" + NL + "\tprivate def save";
  protected final String TEXT_10 = "Rels(obj: ";
  protected final String TEXT_11 = ")(implicit session: Session): ";
  protected final String TEXT_12 = " = {" + NL + "\t\tvar r = obj";
  protected final String TEXT_13 = NL + "\t\tr = save";
  protected final String TEXT_14 = "(r)";
  protected final String TEXT_15 = NL + "\t\tr" + NL + "\t}" + NL + "\t";
  protected final String TEXT_16 = NL + NL + "\tdef save";
  protected final String TEXT_17 = "(obj: ";
  protected final String TEXT_18 = ")(implicit session: Session): ";
  protected final String TEXT_19 = " = {" + NL + "\t\tval isNew = obj.id.isEmpty" + NL + "\t\tval isDirty = obj.isDirty" + NL + "\t\tval baserow = if (isNew) insert";
  protected final String TEXT_20 = "(obj) " + NL + "\t\t              else if (isDirty) update";
  protected final String TEXT_21 = "(obj) else mapTo";
  protected final String TEXT_22 = "Row(obj)" + NL + "\t\tvar r = saveConcrete";
  protected final String TEXT_23 = "(obj, baserow)" + NL + "\t\tr = save";
  protected final String TEXT_24 = "Rels(r)" + NL + "\t\tfinish";
  protected final String TEXT_25 = "(r)" + NL + "\t}" + NL + "\t//" + NL + "\tprivate def saveConcrete";
  protected final String TEXT_26 = "(obj:";
  protected final String TEXT_27 = ", baserow:";
  protected final String TEXT_28 = ")(implicit session: Session): ";
  protected final String TEXT_29 = " = {" + NL + "\t\tobj match {";
  protected final String TEXT_30 = "\t\t" + NL + "\t\t\tcase _: ";
  protected final String TEXT_31 = " => save";
  protected final String TEXT_32 = "(obj.asInstanceOf[";
  protected final String TEXT_33 = "], baserow)";
  protected final String TEXT_34 = "\t\t\t" + NL + "\t\t\tcase _ => throw new ClassCastException(\"can not cast \" + obj.getClass())" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tprivate def save";
  protected final String TEXT_35 = "Rels(obj:";
  protected final String TEXT_36 = "):";
  protected final String TEXT_37 = " = obj" + NL + "\t\t";
  protected final String TEXT_38 = NL + NL + "\tdef save";
  protected final String TEXT_39 = "(obj: ";
  protected final String TEXT_40 = ")(implicit session: Session): ";
  protected final String TEXT_41 = " = {" + NL + "\t\tval r = save";
  protected final String TEXT_42 = "(obj)" + NL + "\t\tr.asInstanceOf[";
  protected final String TEXT_43 = "]" + NL + "\t}" + NL + "  " + NL + "\t/**" + NL + "\t * this is called from ";
  protected final String TEXT_44 = "DaoBase" + NL + "\t */" + NL + "\tdef save";
  protected final String TEXT_45 = "(obj: ";
  protected final String TEXT_46 = ", baseRow:";
  protected final String TEXT_47 = "Row)(implicit session: Session): ";
  protected final String TEXT_48 = " = {" + NL + "\t\tval isNew = obj.id.isEmpty" + NL + "    \tval isDirty = obj.isDirty" + NL + "\t\tvar r = if (isNew) insert";
  protected final String TEXT_49 = "(obj, baseRow.id) " + NL + "\t\t\t\telse if (isDirty) update";
  protected final String TEXT_50 = "(obj) else obj" + NL + "\t\tr = save";
  protected final String TEXT_51 = "Rels(r)" + NL + "\t\tfinish";
  protected final String TEXT_52 = "(r, baseRow)" + NL + "\t}" + NL + "\t" + NL + "\tprivate def save";
  protected final String TEXT_53 = "Rels(obj:";
  protected final String TEXT_54 = ")(implicit session: Session) : ";
  protected final String TEXT_55 = " = {" + NL + "    \t//  deleteOneToManys(obj) n.a." + NL + "\t\tobj" + NL + "\t}" + NL + "\t";
  protected final String TEXT_56 = NL;
  protected final String TEXT_57 = NL + "\tprivate def save";
  protected final String TEXT_58 = "(obj:";
  protected final String TEXT_59 = ")(implicit session: Session) : ";
  protected final String TEXT_60 = " = {" + NL + "\t\tif (obj.";
  protected final String TEXT_61 = ".isDefined) obj.";
  protected final String TEXT_62 = " = Some(save";
  protected final String TEXT_63 = "(obj.";
  protected final String TEXT_64 = ".get))" + NL + "\t\tobj" + NL + "\t}";
  protected final String TEXT_65 = NL + " ";
  protected final String TEXT_66 = " " + NL + "\tdef save";
  protected final String TEXT_67 = "(obj:";
  protected final String TEXT_68 = ")(implicit session: Session): ";
  protected final String TEXT_69 = " = {" + NL + "\t\tobj.";
  protected final String TEXT_70 = " = obj.";
  protected final String TEXT_71 = ".map(";
  protected final String TEXT_72 = " => {";
  protected final String TEXT_73 = NL + "\t\t\t";
  protected final String TEXT_74 = ".";
  protected final String TEXT_75 = " = ";
  protected final String TEXT_76 = NL + "\t\t\tsave";
  protected final String TEXT_77 = "(";
  protected final String TEXT_78 = ")" + NL + "\t\t})" + NL + "\t\tobj" + NL + "\t}" + NL + "  ";
  protected final String TEXT_79 = NL;

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
     String row= entity + "Row"; String rows= entity + "Rows"; String query= nu.uncapName(entity) + "Query"; 
     ImportUtils importUtils = new ImportUtils(false); 
    stringBuffer.append(TEXT_1);
     if (isNormalClass) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_12);
     for (Fw fw : tu.getAllFields(cc)) { 
       String fldname = nu.capName(fw.name()); 
       if (fw.isRelation() && fw.isEntity() && fw.isOwner()) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_14);
       } 
     } 
    stringBuffer.append(TEXT_15);
     } else if (isBaseClass) { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(row);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_29);
     for (Class subclz : subclasses) { 
       importUtils.addTemplateImport(subclz, Tid.SCALA_ENTITY.name()); 
       String subname = subclz.getSimpleName(); 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(subname);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(subname);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(subname);
    stringBuffer.append(TEXT_33);
     } 
    stringBuffer.append(TEXT_34);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_37);
     } else { 
     String superClsname = tu.getSuperClass(cc).getSimpleName(); 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(superClsname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(superClsname);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(superClsname);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_55);
     } 
    stringBuffer.append(TEXT_56);
     for (Fw fw : tu.getAllFields(cc)) { 
       if (fw.isEntity() && fw.isManyToOneField() && fw.isOwner()) { 
       String fldname = fw.name(); String fldtype = fw.typeName(); 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(entity);
    stringBuffer.append(fw.genericTypeName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(fldtype);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_64);
       } 
     } 
    stringBuffer.append(TEXT_65);
     for (Fw fw : tu.getAllFields(cc)) { 
       if (fw.isEntity() && (fw.isOneToManyField() || fw.isOneToOneField())) { 
       String fldname = fw.name(); 
    	 String fldtype = fw.genericTypeName(); 
       String fkname = (fw.hasMappedByFkField()) ? fw.getMappedByFkFieldName() : "?mappedby?"; 
	 String item = nu.uncapName(fw.genericTypeName());
	 Fw mapByFld = (fw.hasMappedByFkField()) ? fw.getMappedByFkField() : null; 
    stringBuffer.append(TEXT_66);
    stringBuffer.append(entity);
    stringBuffer.append(nu.capName(fldname));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(fldname);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_72);
          if (fw.isOneToManyField()) { 
            String setobj = (mapByFld != null) ? "Some(obj)" : "???"; 
			if (mapByFld.isSimple()) setobj = "obj." + idname; 
    stringBuffer.append(TEXT_73);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(fkname);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(setobj);
          } 
    stringBuffer.append(TEXT_76);
    stringBuffer.append(fldtype);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_78);
       } 
     } 
    stringBuffer.append(TEXT_79);
    return stringBuffer.toString();
  }
}
