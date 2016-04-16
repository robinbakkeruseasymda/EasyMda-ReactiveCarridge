package flca.mda.api.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import mda.annotation.AnnotationLiteral;
import mda.annotation.jpa.Inheritance;
import mda.annotation.jpa.InheritanceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import flca.mda.codegen.helpers.AnnotationsHelper;
import flca.mda.codegen.helpers.StrUtil;
import flca.mda.common.api.helpers.ImportHelper;

public class ScalaAnnotationParser  {

	private static Logger logger = LoggerFactory.getLogger(ScalaAnnotationParser.class);
	
	private static final String Q = "\"";

	private static TypeUtils tu = new TypeUtils();

	/**
	 * parse the class annotations and return a string that must be put in the generated source code. 
	 * That is all the "mda.annotation.jpa.xxx" annotations, more or less as-is 
	 * plus all inside the AnnotationLiteral, and if needed the @Entity is added.
	 * 
	 * @param aClass
	 * @return
	 */
	public static String parseAnnotations(Class<?> aClass) {
		StringBuffer sb = new StringBuffer();

		Annotation annots[] = aClass.getAnnotations();
		for (Annotation annotation : annots) {
			sb.append("\n" + parseAnnotation(annotation));
		}
		
		insertEntityAnnotationIfNeeded(aClass, sb);
		
		return cleanupAnnotationString(sb.toString());
	}

	/**
	 * parse the filed annotations and return a string that must be put in the generated source code. 
	 * That is all the "mda.annotation.jpa.xxx" annotations, more or less as-is 
	 * plus all inside the AnnotationLiteral, and if needed the @Entity is added.
	 * 
	 * @param aField Field
	 * @return
	 */
	public static String parseAnnotations(Field aField) {
		StringBuffer sb = new StringBuffer();

		Annotation annots[] = aField.getAnnotations();
		for (Annotation annotation : annots) {
			sb.append("\n" + parseAnnotation(annotation));
		}
		
		return cleanupAnnotationString(sb.toString());
	}
	
	

	/**
	 * TODO
	 * @param aMethod
	 * @param aBuffer
	 */
	public static String parseAnnotations(Method aMethod) {
		return "parseAnnotations(Method) : todo";
	}

	/**
	 * insert the standard annotation for an Entity class, if needed
	 * 
	 * @param aClass
	 * @return
	 */
	private static void insertEntityAnnotationIfNeeded(Class<?> aClass, StringBuffer sb) {

		if (sb.toString().indexOf("@Entity") < 0) {
			ImportHelper.addImport("javax.persistence.Entity");
			sb.insert(0, "@Entity ");

			if (tu.isBaseClass(aClass)) {
				getEntityInheritanceAnnotatsBaseclass(aClass, sb);
			}
		}
	}

	private static void getEntityInheritanceAnnotatsBaseclass(Class<?> aClass, StringBuffer sb) {
		Class<?> superclz = tu.getSuperClass(aClass);
		Inheritance inheritAnno = (Inheritance) tu.getAnnotation(superclz, Inheritance.class);
		if (inheritAnno != null) {
			if (inheritAnno.strategy().equals(InheritanceType.JOINED)) {
				ImportHelper.addImport("javax.persistence.PrimaryKeyJoinColumn");
				sb.append("\n@PrimaryKeyJoinColumn(name=" + Q + tu.getSimpleClassname(superclz) + Q + ")");
			} else if ((inheritAnno.strategy().equals(InheritanceType.SINGLE_TABLE))) {

			}
		}
	}

	private static String parseAnnotationLiteral(AnnotationLiteral anno) {
		String s = anno.annotation();
		if (s.startsWith("@")) {
			s = s.substring(1);
		}
		String fqn = s.substring(0, s.indexOf("("));
		String name = fqn.substring(fqn.lastIndexOf(".") + 1);

		ImportHelper.addImport(fqn);
		return "@" + name + s.substring(s.indexOf("("));
	}

	private static boolean isJpaAnnotation(Class<?> aClass) {
		return isJpaAnnotation(aClass.getSimpleName());
	}

	private static boolean isJpaAnnotation(String aSimpleClassname) {
		for (String s : sJpaAnnotations) {
			if (s.equals(aSimpleClassname.trim())) {
				return true;
			}
		} 
		for (String s : sJpaClassAnnotations) {
			if (s.equals(aSimpleClassname.trim())) {
				return true;
			}
		} 
		
		return false;
	}


	/**
	 * recursive method to generate the string value belonging to this annotation
	 * method
	 * 
	 * @param anno
	 * @param method
	 * @param sb
	 */
	public static String getAnnotationValue(Annotation anno, Method method) {
		String result = "";

		if (!AnnotationsHelper.isValidMethod(method)) {	return result;	}
		
		try {
			Object value = method.invoke(anno, new Object[] {});
			if (value != null) {
				if (value instanceof String) {
					result = getStringAnnotationValue(method, (String) value);
				} else if (value.getClass().isArray()) {
					result = getArrayAnnotationValue(method, value);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("error in getAnnotationValue " + method.getName() + "  " + e);
			return "";
		} 
	}

	private static String getStringAnnotationValue(Method method, String value) {
		String result = "";
		if (value != null && value.trim().length() > 0) {
			result = method.getName() + "=" + Q + value + Q;
		}
		return result;
	}

	private static String getArrayAnnotationValue(Method method, Object value) {
		String result = "";
		
		int arrlen = Array.getLength(value);
		if (arrlen > 0) {
			addJpaImport(value.getClass());
			result = method.getName() + "={";
			for (int i = 0; i < Array.getLength(value); i++) {
				Object item = Array.get(value, 0);
				if (item instanceof String) {
					result += Q + item.toString() + Q;
				} else {
					String comma = (arrlen > 1) ? "," : "";
					result += tu.getSimpleClassname(value.getClass()) + "." +  item.toString() + comma;
				}
			}
			result += "}";
		}

		return result;
	}
	
	private static final String sJpaAnnotations[] = new String[] { "Basic", "CascadeType", "Column", "Entity",
			"FetchType", "Id", "Inheritance", "InheritanceType", "JoinColumn", "JoinColumns", "JoinTable", "ManyToMany",
			"ManyToOne", "OneToMany", "Transient", "UniqueConstraint", "Version", };

	private static final String sJpaClassAnnotations[] = new String[] { "Entity", "Table", "UniqueConstraint",
			"Inheritance", "DiscriminatorColumn", "DiscriminatorValue", };


	private static void addJpaImport(Class<?> annoType) {
		if (isJpaAnnotation(annoType)) {
			ImportHelper.addImport("javax.persistence." + tu.getSimpleClassname(annoType));
		} else {
			ImportHelper.addImport(annoType);
		}
	}
	
	
	private static String parseAnnotation(Annotation annot) {
		if (isJpaAnnotation(annot.annotationType())) {return parseJpaAnnot(annot);}
		else if (annot instanceof AnnotationLiteral) {return parseAnnotationLiteral((AnnotationLiteral) annot);}
		else return "";
	}

	private static String parseJpaAnnot(Annotation annot) {
		String annotName = annot.annotationType().getSimpleName();
		addJpaImport(annot.annotationType());
		StringBuffer sb = new StringBuffer("@" + annotName + "(");
		sb.append(parseAllAnnotationStringProperties(annot));
		sb.append(")");
		return sb.toString();
	}

	private static String parseAllAnnotationStringProperties(Annotation annot) {
		StringBuffer sb = new StringBuffer();
		try {
			Method methods[] = annot.getClass().getMethods();
			for (Method method : methods) {
				if (AnnotationsHelper.isValidMethod(method) && 
					method.getParameterTypes().length == 0) {
					sb.append(parseAnnotationProperty(annot, method.getName()));
				}
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error("error in parseAllAnnotationStringProperties "  + annot + " "  + e);
			return "";
		} 
	}

	
	private static String parseAnnotationProperty(Annotation annot, String aPropertyName) {
		try {
			Method m = annot.getClass().getMethod(aPropertyName, new Class[]{});
			Object o = m.invoke(annot, new Object[]{});
			if (o != null) {
				if (o.getClass().isArray()) {
					return parseAnnotationArrayProperty(m, o);
				} else {
					return parseTheAnnotationProperty(m, o);
				}
			} 
			return "";
		} catch (Exception e) {
			logger.error("error in parseAnnotationProperty "  + annot + " " + aPropertyName + " " + e);
			return "";
		} 
	}

	private static String parseTheAnnotationProperty(Method m, Object aValue) {
		if (aValue instanceof String && !empty(aValue.toString())) {
			return m.getName() + "=" + Q + aValue + Q + ",";
		} else if (aValue instanceof Annotation) {
			return parseAnnotationProperty((Annotation) aValue, m.getName());
		} else {
			return "";
		}
	}

	private static String parseAnnotationArrayProperty(Method m, Object aValues) {
		StringBuffer sb = new StringBuffer();
		if (Array.getLength(aValues) > 0) {
			sb.append(m.getName() + "={");

			for (int i = 0; i < Array.getLength(aValues); i++) {
				Object value = Array.get(aValues, i);
				if (value instanceof String && !empty(value.toString())) {
					sb.append(Q + value + Q + ",");
				} else if (value instanceof Annotation) {
					sb.append(parseJpaAnnot((Annotation) value));
				}
			}

			sb.append("}");
		}
		
		if (!sb.toString().endsWith("={}")) {
			return sb.toString();
		} else {
			return "";
		}
	}
	

	private static boolean empty(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	private static String cleanupAnnotationString(String s) {
		String r = s;
		if (r.indexOf(",)") > 0) {
			r = StrUtil.replace(r, ",)", ")");
		}
		return r;
	}
}
