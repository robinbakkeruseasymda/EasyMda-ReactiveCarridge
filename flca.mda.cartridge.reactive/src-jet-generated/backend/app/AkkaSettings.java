package backend.app;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class AkkaSettings
{
  protected static String nl;
  public static synchronized AkkaSettings create(String lineSeparator)
  {
    nl = lineSeparator;
    AkkaSettings result = new AkkaSettings();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "package ";
  protected final String TEXT_2 = NL + NL + "import java.util.concurrent.TimeUnit" + NL + "" + NL + "import com.typesafe.config.Config" + NL + "" + NL + "import akka.actor._" + NL + "" + NL + "import scala.concurrent.duration.FiniteDuration" + NL + " " + NL + "class ";
  protected final String TEXT_3 = " (config: Config, extendedSystem: ExtendedActorSystem) extends Extension {" + NL + " " + NL + "  object Http {" + NL + "    val Port = config.getInt(";
  protected final String TEXT_4 = ".APP_NAME  + \".http.port\")" + NL + "    val Host = config.getString(";
  protected final String TEXT_5 = ".APP_NAME  + \".http.host\")" + NL + "  }" + NL + " " + NL + "  val askTimeout = FiniteDuration(config.getMilliseconds(";
  protected final String TEXT_6 = ".APP_NAME  + \"ask-timeout\"), TimeUnit.MILLISECONDS)" + NL + "}" + NL + " " + NL + "object ";
  protected final String TEXT_7 = " extends ExtensionId[";
  protected final String TEXT_8 = "] with ExtensionIdProvider {" + NL + "  override def lookup = ";
  protected final String TEXT_9 = NL + "  override def createExtension(system: ExtendedActorSystem) = new ";
  protected final String TEXT_10 = "(system.settings.config, system)" + NL + " " + NL + "  def apply(context: ActorContext): ";
  protected final String TEXT_11 = " = apply(context.system)" + NL + "}";
  protected final String TEXT_12 = NL;

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
     String appcfg = tplu.getClassName(Tid.SCALA_APP_CONFIG.name());
    stringBuffer.append(TEXT_1);
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(appcfg);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(classname);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
