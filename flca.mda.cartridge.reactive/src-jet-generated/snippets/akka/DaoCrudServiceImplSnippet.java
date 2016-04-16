package snippets.akka;

import java.util.*;
import java.lang.reflect.*;
import mda.type.*;
import flca.mda.api.util.*;
import flca.mda.codegen.helpers.*;
import reactive.*;
import mda.annotation.crud.*;

public class DaoCrudServiceImplSnippet
{
  protected static String nl;
  public static synchronized DaoCrudServiceImplSnippet create(String lineSeparator)
  {
    nl = lineSeparator;
    DaoCrudServiceImplSnippet result = new DaoCrudServiceImplSnippet();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + "  override def find";
  protected final String TEXT_4 = ") = {" + NL + "    db withSession { implicit session:Session =>" + NL + "      dal.find";
  protected final String TEXT_5 = NL + "    }" + NL + "  }" + NL + "    ";
  protected final String TEXT_6 = NL + "    ";
  protected final String TEXT_7 = NL + "    ";
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL + "    ";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     NameUtils nu = new NameUtils();
     ScalaTypeUtils tu = new ScalaTypeUtils();
     TemplateUtils tplu = new TemplateUtils();
     AppUtils au = new AppUtils(); 
     ScalaInterfaceUtils iu = new ScalaInterfaceUtils(); 
     Object args[] = (Object[]) argument; 
     CrudMethod crudmethod = (CrudMethod) args[0]; 
     String entity = crudmethod.getEntity().getSimpleName(); 
     CrudOperation crudoper = crudmethod.getCrudOper(); 
     Class<?> crudent = crudmethod.getEntity(); 
     String entityName = tplu.getClassName(crudent, Tid.SCALA_ENTITY.name()); 
    stringBuffer.append(TEXT_1);
     String pkType =  tu.getIdTypeName(crudent); 
    stringBuffer.append(TEXT_2);
     if (CrudOperation.SAVE.equals(crudoper)) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(entity);
    stringBuffer.append(TEXT_5);
     } else if (CrudOperation.RETRIEVE.equals(crudoper)) { 
    stringBuffer.append(TEXT_6);
     } else if (CrudOperation.FIND_ALL.equals(crudoper)) { 
    stringBuffer.append(TEXT_7);
     } else if (CrudOperation.REMOVE.equals(crudoper)) { 
    stringBuffer.append(TEXT_8);
     } else if (CrudOperation.SEARCH.equals(crudoper)) { 
    stringBuffer.append(TEXT_9);
     } 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
