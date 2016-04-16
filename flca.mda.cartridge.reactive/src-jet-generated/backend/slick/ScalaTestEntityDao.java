package backend.slick;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class ScalaTestEntityDao
{
  protected static String nl;
  public static synchronized ScalaTestEntityDao create(String lineSeparator)
  {
    nl = lineSeparator;
    ScalaTestEntityDao result = new ScalaTestEntityDao();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = NL + "\t " + NL + "@RunWith(classOf[JUnitRunner])" + NL + "class ";
  protected final String TEXT_7 = " extends FunSuite {" + NL + "\t" + NL + "\tval ds = ";
  protected final String TEXT_8 = ".datasource" + NL + "\tvar dal = new ";
  protected final String TEXT_9 = "(";
  protected final String TEXT_10 = ".driver)" + NL + "\tval db = Database.forDataSource(ds)" + NL + "\t" + NL + "\ttest(\"create schema\") {" + NL + "\t\tsetup(";
  protected final String TEXT_11 = ".dbType, dal, db)" + NL + "\t}" + NL + "\t" + NL + "\tdef setup(name:String, dal:";
  protected final String TEXT_12 = ", db:Database) {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "\t" + NL + "\t\tprintln(\"Running test against \" + name)" + NL + "\t\tdb withSession { implicit session:Session =>" + NL + "\t\t\tif (";
  protected final String TEXT_13 = ".dropTables) {dal.drop}" + NL + "\t\t\tif (";
  protected final String TEXT_14 = ".createTables) {dal.create}" + NL + "\t\t}" + NL + "\t}" + NL + "\t\t" + NL + "\ttest(\"create some new ";
  protected final String TEXT_15 = "s\") {" + NL + "\t\tfor (i <- 1 to 10) {" + NL + "\t\t\tval ";
  protected final String TEXT_16 = ":";
  protected final String TEXT_17 = " = ";
  protected final String TEXT_18 = ".makeRandom(None)" + NL + "\t\t\ttestSave";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = ", dal)" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"find all ";
  protected final String TEXT_21 = "s\") {" + NL + "\t\tval ";
  protected final String TEXT_22 = "s = testFind";
  protected final String TEXT_23 = "(dal)" + NL + "\t\tassert(";
  protected final String TEXT_24 = "s.size > 9)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"retrieve ";
  protected final String TEXT_25 = " fetch complete\") {" + NL + "\t\tval obj = getOneObject(Some(";
  protected final String TEXT_26 = "Fd()))" + NL + "\t\tassert(";
  protected final String TEXT_27 = ".hasAllFetched(obj))" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"retrieve ";
  protected final String TEXT_28 = " without nested objects\") {" + NL + "\t\tval obj = getOneObject(None)";
  protected final String TEXT_29 = NL + "\t\tval b";
  protected final String TEXT_30 = " = !obj.";
  protected final String TEXT_31 = ".isDefined\t\t";
  protected final String TEXT_32 = NL + "\t\tval b";
  protected final String TEXT_33 = " = obj.";
  protected final String TEXT_34 = ".isEmpty ";
  protected final String TEXT_35 = NL + "\t\t// no nested objects to test here";
  protected final String TEXT_36 = NL + "\t\tassert(";
  protected final String TEXT_37 = ")";
  protected final String TEXT_38 = NL + "\t}" + NL + "\t\t" + NL + "\ttest(\"update ";
  protected final String TEXT_39 = " with changes\") {" + NL + "\t\tval obj1 = getOneObject(Some(";
  protected final String TEXT_40 = "Fd()))" + NL + "\t\t//TODO obj1.name = \"AAA\"" + NL + "\t\tval obj2 = testSave";
  protected final String TEXT_41 = "(obj1, dal)" + NL + "\t\t//TODO assert(obj2.name === \"AAA\")" + NL + "\t\tprintln(obj2)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"update ";
  protected final String TEXT_42 = " without changes\") {" + NL + "\t\tval obj1 = getOneObject(Some(";
  protected final String TEXT_43 = "Fd()))" + NL + "\t\tval obj2 = testSave";
  protected final String TEXT_44 = "(obj1, dal)" + NL + "\t\tprintln(obj2)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"retrieve unknown ";
  protected final String TEXT_45 = "\") {" + NL + "\t\tval obj1 = testRetrieve";
  protected final String TEXT_46 = "(-1, dal, None)" + NL + "\t\tassert(obj1.isEmpty)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"delete a ";
  protected final String TEXT_47 = "\") {" + NL + "\t\tval list = testFind";
  protected final String TEXT_48 = "(dal)" + NL + "\t\tval n1 = list.size" + NL + "\t\tval obj = list(list.size-1)" + NL + "\t\ttestDelete";
  protected final String TEXT_49 = "(obj.";
  protected final String TEXT_50 = ".get, dal)" + NL + "\t\tval n2 = testFind";
  protected final String TEXT_51 = "(dal).size" + NL + "\t\tassert(n2 == n1-1)" + NL + "\t}\t" + NL + "\t" + NL + "\ttest(\"delete an onetomany element\") {" + NL + "\t\tval obj1 = getOneObject(Some(";
  protected final String TEXT_52 = "Fd()))";
  protected final String TEXT_53 = NL + "\t\tval n1_";
  protected final String TEXT_54 = " = obj1.";
  protected final String TEXT_55 = ".size" + NL + "\t\tobj1.";
  protected final String TEXT_56 = " -= obj1.";
  protected final String TEXT_57 = ".head";
  protected final String TEXT_58 = NL + "\t\tval obj2 = testSave";
  protected final String TEXT_59 = "(obj1, dal)";
  protected final String TEXT_60 = NL + "\t\tval n2_";
  protected final String TEXT_61 = " = obj2.";
  protected final String TEXT_62 = ".size";
  protected final String TEXT_63 = NL + "\t\tassert(n2_";
  protected final String TEXT_64 = " == n1_";
  protected final String TEXT_65 = " - 1)";
  protected final String TEXT_66 = NL + "\t}\t" + NL + "\t" + NL + "\ttest(\"make find curl cmd\") {" + NL + "\t\tval url = s\"http://localhost:$HTTP_PORT/Find";
  protected final String TEXT_67 = "\"" + NL + "\t\tval s = CURL_CMD + url" + NL + "\t\tprintln(s)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"make retrieve curl cmd\") {" + NL + "\t\tval url = s\"http://localhost:$HTTP_PORT/retrieve";
  protected final String TEXT_68 = "?id=1\"" + NL + "\t\tval s = CURL_CMD + url" + NL + "\t\tprintln(s)" + NL + "\t}" + NL + "\t" + NL + "\ttest(\"make save curl cmd\") {" + NL + "\t\tval obj = ";
  protected final String TEXT_69 = ".makeRandom(None)" + NL + "\t\tval req = Save";
  protected final String TEXT_70 = "Req(obj)" + NL + "\t\tval json = JsonUtils.toJson(req);" + NL + "\t\tval url = s\"http://localhost:$HTTP_PORT/save";
  protected final String TEXT_71 = "\"" + NL + "\t\tval s = CURL_POST_CMD + \"'\" + json + \"' \" + url" + NL + "\t\tprintln(s)" + NL + "\t}" + NL + "\t" + NL + "\t//--- dao helpers --" + NL + "\t" + NL + "\tprivate def getOneObject(fd:Option[";
  protected final String TEXT_72 = "Fd]) : ";
  protected final String TEXT_73 = " = {" + NL + "\t\tval list = testFind";
  protected final String TEXT_74 = "(dal)" + NL + "\t\tval n1 = list.size" + NL + "\t\tval obj = list(list.size-1)" + NL + "\t\ttestRetrieve";
  protected final String TEXT_75 = "(obj.id.get, dal, fd).get" + NL + "\t}" + NL + "\t\t" + NL + "\tprivate def testFind";
  protected final String TEXT_76 = "(dal:";
  protected final String TEXT_77 = "): List[";
  protected final String TEXT_78 = "] = {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "" + NL + "\t\tdb withSession { implicit session =>" + NL + "\t\t\tfind";
  protected final String TEXT_79 = NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tprivate def testRetrieve";
  protected final String TEXT_80 = "(";
  protected final String TEXT_81 = ":";
  protected final String TEXT_82 = ", dal:";
  protected final String TEXT_83 = ", fd:Option[";
  protected final String TEXT_84 = "Fd]=Some(";
  protected final String TEXT_85 = "Fd())): Option[";
  protected final String TEXT_86 = "] = {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "" + NL + "\t\tdb withSession { implicit session =>" + NL + "\t\t\tretrieve";
  protected final String TEXT_87 = "(";
  protected final String TEXT_88 = ", fd)" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tprivate def testSave";
  protected final String TEXT_89 = "(";
  protected final String TEXT_90 = ":";
  protected final String TEXT_91 = ", dal:";
  protected final String TEXT_92 = "): ";
  protected final String TEXT_93 = " = {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "" + NL + "\t\tdb withSession { implicit session =>" + NL + "\t\t\tsave";
  protected final String TEXT_94 = "(";
  protected final String TEXT_95 = ")" + NL + "\t\t}" + NL + "\t}" + NL + "\t\t" + NL + "\tprivate def testDelete";
  protected final String TEXT_96 = "(";
  protected final String TEXT_97 = ":";
  protected final String TEXT_98 = ", dal:";
  protected final String TEXT_99 = "): Unit = {" + NL + "\t\timport dal._" + NL + "\t\timport dal.profile.simple._" + NL + "" + NL + "\t\tdb withSession { implicit session =>" + NL + "\t\t\tdelete";
  protected final String TEXT_100 = "(";
  protected final String TEXT_101 = ")" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "}";
  protected final String TEXT_102 = NL;

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
 
     String appConst = tplu.getClassName(Tid.SCALA_APP_CONSTANTS.name());
     String actorPck = tplu.getPackage(Tid.SCALA_DAO_SERVICE_ACTOR.name());
     String actorClz = tplu.getClassName(Tid.SCALA_DAO_SERVICE_ACTOR.name());
     impu.addImport(appPck + "." + appConst + ".*");
     impu.addImport(actorPck + "." + actorClz + ".*");
     impu.addImport("scala.slick.util.SlickLogger");
     impu.addImport("org.junit.runner.RunWith");
     impu.addImport("org.scalatest.junit.JUnitRunner");
     impu.addImport("scala.slick.jdbc.JdbcBackend.Database");
     impu.addImport("org.scalatest.FunSuite");
     impu.addImport("org.scalatest.BeforeAndAfterEach");
     impu.addTemplateImport(Tid.SCALA_SLICK_PROFILE.name());
     impu.addTemplateImport(Tid.SCALA_SLICK_DATASOURCE.name());
    stringBuffer.append(TEXT_4);
     impu.addTemplateImport(Tid.SCALA_APP_DATASTORES.name());
    stringBuffer.append(TEXT_5);
     impu.addTemplateImport(Tid.SCALA_APP_CONFIG.name());
     impu.addTemplateImport(Tid.SCALA_JSONUTILS.name());
     impu.addImport(cc);
     impu.addScalaImport(entity + "Fd", cc, Tid.SCALA_ENTITY.name());
     String mock = tplu.getClassName(Tid.SCALA_ENTITY_MOCK.name());
     String appcfg = tplu.getClassName(Tid.SCALA_APP_CONFIG.name());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(datasource);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_28);
     int cnt = 0; 
     for (Fw fw : tu.getFieldsInc(cc, O2O_FLD)) { 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cnt++);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_31);
     } 
    	for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
    		String nestedmock = tplu.getClassName(fw.genericType(), Tid.SCALA_ENTITY_MOCK.name());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cnt++);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_34);
     } 
     String s = ""; for (int i=0;i<cnt;i++) { s += "b" + i + " && ";} 
     if (s.trim().isEmpty()) { 
    stringBuffer.append(TEXT_35);
     } else { 
    stringBuffer.append(TEXT_36);
    stringBuffer.append(nu.trimRight(s, "&&"));
    stringBuffer.append(TEXT_37);
     } 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_52);
    	for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_57);
     } 
    stringBuffer.append(TEXT_58);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_59);
    	for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_62);
     } 
    	for (Fw fw : tu.getFieldsInc(cc, O2M_FLD)) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(fw.name());
    stringBuffer.append(TEXT_65);
     } 
    stringBuffer.append(TEXT_66);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(mock);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(uncapname);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(datastores);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_101);
     importStringBuffer.insert(importInsertionPoint,	impu.computeSortedImports());
    stringBuffer.append(TEXT_102);
    return stringBuffer.toString();
  }
}
