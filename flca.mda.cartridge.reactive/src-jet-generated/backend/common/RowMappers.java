package backend.common;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;

public class RowMappers
{
  protected static String nl;
  public static synchronized RowMappers create(String lineSeparator)
  {
    nl = lineSeparator;
    RowMappers result = new RowMappers();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = NL + "  " + NL + "import java.sql.Date;" + NL + "import java.util.Date;" + NL + " " + NL + "trait RowMappers {" + NL + "  def toDate(date: java.sql.Date) = new java.util.Date(date.getTime())" + NL + "  def toDate(date: Option[java.sql.Date]) = if (date.isDefined) new java.util.Date(date.get.getTime()) else null" + NL + "  def toSqlDate(date: java.util.Date) = new java.sql.Date(date.getTime)" + NL + "  def toSqlDate(date: Option[java.util.Date]) = if (date.isDefined) new java.sql.Date(date.get.getTime()) else null" + NL + "}";
  protected final String TEXT_3 = NL;

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
    stringBuffer.append(pck);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
