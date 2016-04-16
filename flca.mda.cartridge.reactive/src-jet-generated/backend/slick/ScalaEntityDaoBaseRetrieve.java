package backend.slick;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaEntityDaoBaseRetrieve
{
  protected static String nl;
  public static synchronized ScalaEntityDaoBaseRetrieve create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaEntityDaoBaseRetrieve result = new ScalaEntityDaoBaseRetrieve();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = " " + NL;
  protected final String TEXT_3 = NL + NL + "\tdef retrieve";
  protected final String TEXT_4 = "(";
  protected final String TEXT_5 = ":";
  protected final String TEXT_6 = ", fd:Option[";
  protected final String TEXT_7 = "]=Some(";
  protected final String TEXT_8 = "()))(implicit session: Session): Option[";
  protected final String TEXT_9 = "] = {" + NL + "\t\tlogger.info(s\"retrieve ";
  protected final String TEXT_10 = " $";
  protected final String TEXT_11 = "\")" + NL + "\t\tval list = ";
  protected final String TEXT_12 = ".list.filter(Some(";
  protected final String TEXT_13 = ") == _.";
  protected final String TEXT_14 = ")" + NL + "\t\tif (list.size == 1) {" + NL + "\t\t\tvar r = ";
  protected final String TEXT_15 = "(list.head) " + NL + "\t\t\tr = retrieve";
  protected final String TEXT_16 = "Rels(r, fd)" + NL + "\t\t\tSome(finish";
  protected final String TEXT_17 = "(r, fd))" + NL + "\t\t} else {" + NL + "\t\t\tlogger.error(s\"could not retrieve ";
  protected final String TEXT_18 = " $";
  protected final String TEXT_19 = "\")" + NL + "\t\t\tNone" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + " \tprivate def retrieve";
  protected final String TEXT_20 = "Rels(obj: ";
  protected final String TEXT_21 = ", fd: Option[";
  protected final String TEXT_22 = "])(implicit session: Session): ";
  protected final String TEXT_23 = " = {";
  protected final String TEXT_24 = NL;
  protected final String TEXT_25 = NL + "\t\tobj" + NL + "\t}" + NL + "\t";
  protected final String TEXT_26 = NL + NL + "\tdef retrieve";
  protected final String TEXT_27 = "(";
  protected final String TEXT_28 = ":";
  protected final String TEXT_29 = ", fd:Option[";
  protected final String TEXT_30 = "]=Some(new FetchEager()))(implicit session: Session): Option[";
  protected final String TEXT_31 = "] = {" + NL + "\t\tlogger.info(s\"retrieve ";
  protected final String TEXT_32 = " $";
  protected final String TEXT_33 = "\")" + NL + "\t\tval list = ";
  protected final String TEXT_34 = ".list.filter(Some(";
  protected final String TEXT_35 = ") == _.";
  protected final String TEXT_36 = ")" + NL + "\t\tif (list.size == 1) {" + NL + "\t\t\tvar r:Option[";
  protected final String TEXT_37 = "] = retrieveConcrete";
  protected final String TEXT_38 = "(list.head, fd)" + NL + "\t\t\tSome(finish";
  protected final String TEXT_39 = "(r.get))" + NL + "\t\t} else {" + NL + "\t\t\tlogger.error(s\"could not retrieve ";
  protected final String TEXT_40 = " $";
  protected final String TEXT_41 = "\")" + NL + "\t\t\tNone" + NL + "\t\t}" + NL + "\t}" + NL + "\t\t" + NL + "\tdef retrieveConcrete";
  protected final String TEXT_42 = "(row:";
  protected final String TEXT_43 = ", fd:Option[FetchDepth])(implicit session: Session): Option[";
  protected final String TEXT_44 = "] = {" + NL + "\t\trow.discriminator match {" + NL + "\t\t\tcase \"L\" => retrieve";
  protected final String TEXT_45 = "(row.id.get, fd)" + NL + "\t\t\tcase _ => null" + NL + "\t\t}" + NL + "\t}" + NL + "\t";
  protected final String TEXT_46 = NL + NL + "\tdef retrieve";
  protected final String TEXT_47 = "(id:Long, fd:Option[FetchDepth]=Some(new FetchEager()))(implicit session: Session): Option[";
  protected final String TEXT_48 = "] = {" + NL + "\t\tlogger.info(s\"retrieve ";
  protected final String TEXT_49 = " $id\")" + NL + "\t\tval list = ";
  protected final String TEXT_50 = ".list.filter(Some(id) == _.id)" + NL + "\t\tif (list.size == 1) {" + NL + "\t\t\tvar r = mapFrom";
  protected final String TEXT_51 = "Row(list.head)" + NL + "\t\t\tr = retrieve";
  protected final String TEXT_52 = "NestedObjects(r, fd)" + NL + "\t\t\tSome(finish";
  protected final String TEXT_53 = "(r))" + NL + "\t\t} else {" + NL + "\t\t\tlogger.error(s\"could not retrieve ";
  protected final String TEXT_54 = " $id\")" + NL + "\t\t\tNone" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\t/*" + NL + "\t * This method is called from the baseclass" + NL + "\t */" + NL + "\tdef retrieve";
  protected final String TEXT_55 = "(baseRow: ";
  protected final String TEXT_56 = "Row)(implicit session: Session): ";
  protected final String TEXT_57 = " = {" + NL + "\t\tlogger.info(s\"retrieve concrete ";
  protected final String TEXT_58 = " with id=${baseRow.id}\")" + NL + "\t\tval list = ";
  protected final String TEXT_59 = ".list.filter(baseRow.id == _.id)" + NL + "\t\tif (list.size == 1) {" + NL + "\t\t\tval r1 = ";
  protected final String TEXT_60 = "s(baseRow, list.head)" + NL + "\t\t\t//nested object n.a." + NL + "\t\t\tr1" + NL + "\t\t} else {" + NL + "\t\t\tval msg = s\"could not retrieve concrete ";
  protected final String TEXT_61 = " with id=${baseRow.id}\"" + NL + "\t\t\tlogger.error(msg)" + NL + "\t\t\tthrow new RuntimeException(msg)" + NL + "\t\t}" + NL + "\t}\t" + NL + "\t" + NL + "\tprivate def retrieve";
  protected final String TEXT_62 = "NestedObjects(obj: ";
  protected final String TEXT_63 = ", fd: Option[FetchDepth])(implicit session: Session): ";
  protected final String TEXT_64 = " = {" + NL + "\t\t//TODO nog te genereren." + NL + "\t\t\tobj" + NL + "\t}" + NL + "\t";
  protected final String TEXT_65 = "\t";
  protected final String TEXT_66 = "\t";
  protected final String TEXT_67 = "\t" + NL + "" + NL + "\t/*" + NL + "\t * This method is implictelly called from the baseclass, and uses both the baserow and its own row" + NL + "\t */" + NL + "\tdef ";
  protected final String TEXT_68 = "s(baseRow: ";
  protected final String TEXT_69 = "Row, row: ";
  protected final String TEXT_70 = ", newId: Option[Long] = None): ";
  protected final String TEXT_71 = " = {" + NL + "//    val useId = if (newId.isDefined) newId else row.id" + NL + "\t\tval obj = new ";
  protected final String TEXT_72 = "(";
  protected final String TEXT_73 = ")" + NL + "    " + NL + "\t\t";
  protected final String TEXT_74 = NL + "\t\tobj" + NL + "\t}\t" + NL + "\t";

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
     String rowmapper = tplu.getClassName(Tid.SCALA_ROWMAPPERS.name()); 
     String row= entity + "Row"; String rows= entity + "Rows"; String query= nu.uncapName(entity) + "Query"; 
     String mapfromRow= "mapFrom" + row; String maptoRow= "mapTo" + row;
     ImportUtils importUtils = new ImportUtils(false); 
    stringBuffer.append(TEXT_1);
     if (isBaseClass || isSubClass) { 
    	importUtils.addImport("com.easymda.util.FetchEager"); 
     } 
    stringBuffer.append(TEXT_2);
     if (isNormalClass) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(fetchModel);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(fetchModel);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(mapfromRow);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(fetchModel);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append( tu.genRetrieveNestedObjectsStr(cc));
    stringBuffer.append(TEXT_25);
     } else if (isBaseClass) { 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(fetchModel);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(row);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_45);
     } else { // subClass 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(query);
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
    stringBuffer.append(superClassname);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(mapfromRow);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_64);
     String baserowArgs = tu.format(tu.getFieldsExc(superClass, O2M_FLD,VAR_FLD,DISC_FLD,OFD_FLD), "baseRow.%n", ",\n\t\t\t"); 
    stringBuffer.append(TEXT_65);
     String rowArgs = tu.format(tu.getFieldsExc(cc, O2M_FLD, VAR_FLD), "row.%n", ",\n\t\t\t"); 
    stringBuffer.append(TEXT_66);
     String varSetters = tu.format(tu.getFieldsExc(cc, O2M_FLD, VAL_FLD), "obj.%n = row.%n", "\n\t\t"); 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(mapfromRow);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(superClassname);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(row);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(baserowArgs);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(varSetters);
    stringBuffer.append(TEXT_74);
     } 
    return stringBuffer.toString();
  }
}
