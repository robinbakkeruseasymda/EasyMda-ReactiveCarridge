package backend.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class DaoServiceActor
{
  protected static String nl;
  public static synchronized DaoServiceActor create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoServiceActor result = new DaoServiceActor();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/*" + NL + " * Generated via the com.flca generator" + NL + " */" + NL + "\t" + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "\t";
  protected final String TEXT_4 = NL + " " + NL + "object ";
  protected final String TEXT_5 = " {" + NL + "  def props = Props[";
  protected final String TEXT_6 = "]" + NL + "  def name = \"";
  protected final String TEXT_7 = "\"" + NL + "  " + NL + "  case object Find";
  protected final String TEXT_8 = "Req" + NL + "  case class Find";
  protected final String TEXT_9 = "Res(value: List[";
  protected final String TEXT_10 = "]) " + NL + "" + NL + "  case class Retrieve";
  protected final String TEXT_11 = "Req(";
  protected final String TEXT_12 = ":";
  protected final String TEXT_13 = ")" + NL + "  case class Retrieve";
  protected final String TEXT_14 = "Res(value:Option[";
  protected final String TEXT_15 = "]) " + NL + "" + NL + "  case class Save";
  protected final String TEXT_16 = "Req(value:";
  protected final String TEXT_17 = ")" + NL + "  case class Save";
  protected final String TEXT_18 = "Res(value:";
  protected final String TEXT_19 = ") " + NL + "  " + NL + "  case class Delete";
  protected final String TEXT_20 = "Req(";
  protected final String TEXT_21 = ":";
  protected final String TEXT_22 = ")" + NL + "}" + NL + " " + NL + "class ";
  protected final String TEXT_23 = " extends Actor {" + NL + "  import ";
  protected final String TEXT_24 = "._" + NL + "" + NL + "  import context._" + NL + "" + NL + "  def receive: Receive = {" + NL + "    case Find";
  protected final String TEXT_25 = "Req => {" + NL + "    \ttry {" + NL + "\t\t\tsender ! Find";
  protected final String TEXT_26 = "Res(service.find";
  protected final String TEXT_27 = ")" + NL + "    \t} catch {" + NL + "    \t\tcase e:Exception => sender ! Failure(e)" + NL + "    \t}" + NL + "    }" + NL + "    case Retrieve";
  protected final String TEXT_28 = "Req(";
  protected final String TEXT_29 = ") => {" + NL + "    \ttry {" + NL + "\t\t\tsender ! Retrieve";
  protected final String TEXT_30 = "Res(service.retrieve";
  protected final String TEXT_31 = "(";
  protected final String TEXT_32 = "))" + NL + "    \t} catch {" + NL + "    \t\tcase e:Exception => sender ! Failure(e)" + NL + "    \t}" + NL + "    }" + NL + "    case Save";
  protected final String TEXT_33 = "Req(value) => {" + NL + "    \ttry {" + NL + "\t\t\tsender ! Save";
  protected final String TEXT_34 = "Res(service.save";
  protected final String TEXT_35 = "(value))" + NL + "    \t} catch {" + NL + "    \t\tcase e:Exception => sender ! Failure(e)" + NL + "    \t}" + NL + "    }" + NL + "  }" + NL + "" + NL + "  val service = ";
  protected final String TEXT_36 = ".get" + NL + "}";
  protected final String TEXT_37 = NL;

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
 
     String srvfact = tplu.getClassName(Tid.SCALA_DAO_SERVICE_FACT.name()); 
     impu.addImport("akka.actor.Actor");
     impu.addImport("akka.actor.Props");
     impu.addImport("akka.actor.Status.Failure");
     impu.addImport(cc);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(idtype);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(idname);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(srvfact);
    stringBuffer.append(TEXT_36);
     importStringBuffer.insert(importInsertionPoint,  impu.computeSortedImports());
    stringBuffer.append(TEXT_37);
    return stringBuffer.toString();
  }
}
