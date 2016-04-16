package flca.mda.api.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScalaDataTypes {

	private static final Map<Class<?>, Tuple> SCALA_TYPES;
	
	private static final ScalaTypeUtils su = new ScalaTypeUtils();
	
	static {
		SCALA_TYPES = new HashMap<Class<?>, Tuple>();
		
		SCALA_TYPES.put(String.class, new Tuple("String", "", "null", "null"));
		SCALA_TYPES.put(int.class, new Tuple("Int", "", "0", "Int.minValue"));
		SCALA_TYPES.put(Integer.class, new Tuple("Int", "", "0", "Int.minValue"));
		SCALA_TYPES.put(long.class, new Tuple("Long", "", "0", "Long.minValue"));
		SCALA_TYPES.put(Long.class, new Tuple("Long", "", "0", "Long.minValue"));
		SCALA_TYPES.put(double.class, new Tuple("Double", "", "0.0", "Double.minValue"));
		SCALA_TYPES.put(Double.class, new Tuple("Double", "", "0.0", "Double.minValue"));
		SCALA_TYPES.put(BigDecimal.class, new Tuple("Double", "", "0.0", "Double.minValue"));
		SCALA_TYPES.put(boolean.class, new Tuple("Boolean", "", "false", "false"));  // for val's this will be replace by Some(Boolean)
		SCALA_TYPES.put(Boolean.class, new Tuple("Boolean", "", "false", "false"));
		SCALA_TYPES.put(java.util.Date.class, new Tuple("Date", "java.util.Date", "new Date()", "null", "java.sql.Date"));
		SCALA_TYPES.put(Set.class, new Tuple("Set", "", "Set()", "Set()") );
		SCALA_TYPES.put(List.class, new Tuple("List", "", "List()", "List()"));
		SCALA_TYPES.put(Collection.class, new Tuple("Set", "", "Set()", "Set()"));
		SCALA_TYPES.put(Map.class, new Tuple("Map", "", "Map()", "Map()"));
	}

	public static String getTypeName(Field aField) {
		return getTypeName(aField.getType());
	}
	
	public static String getTypeName(Class<?> aClass) {
		if (SCALA_TYPES.get(aClass) != null) {
			return SCALA_TYPES.get(aClass).dtype;
		} else if (aClass.isEnum()) {
			return aClass.getSimpleName() + ".Value";
		} else {
			return aClass.getSimpleName();
		}
	}

	public static String getRowTypeName(Class<?> aClass) {
		if (SCALA_TYPES.get(aClass) != null) {
			if (SCALA_TYPES.get(aClass).rowDtype == null) {
				return SCALA_TYPES.get(aClass).dtype;
			} else {
				return SCALA_TYPES.get(aClass).rowDtype;
			}
		} else if (aClass.isEnum()) {
			return "String";
		} else {
			return aClass.getSimpleName();
		}
	}
	
	public static String getImportString(Class<?> aClass) {
		if (SCALA_TYPES.get(aClass) != null) {
			return SCALA_TYPES.get(aClass).importStr;
		} else {
			return aClass.getName();
		}
	}
	
	public static String getDefaultValue(Class<?> aClass) {
		if (SCALA_TYPES.get(aClass) != null) {
			return SCALA_TYPES.get(aClass).defaultValue;
		} else if (aClass.isEnum()) {
			return null;
		} else if (su.isEntity(aClass)) {
			return aClass.getSimpleName() + "()";
		} else {
			return aClass.getSimpleName();
		}
	}	

	public static String getDefaultRowValue(Class<?> aClass) {
		if (SCALA_TYPES.get(aClass) != null) {
			return SCALA_TYPES.get(aClass).defaultRowValue;
		} else {
			return aClass.getSimpleName();
		}
	}	
	
	private static class Tuple {
		String dtype;
		String importStr;
		String defaultValue;
		String rowDtype = null;
		String defaultRowValue = null;
		
		public Tuple(String dtype, String importStr, String defaultValue, String defaultRowValue) {
			super();
			this.dtype = dtype;
			this.importStr = importStr;
			this.defaultValue = defaultValue;
			this.defaultRowValue = defaultRowValue;
		}
		
		public Tuple(String dtype, String importStr, String defaultValue, String defaultRowValue, String rowDtype) {
			this(dtype, importStr, defaultValue, defaultRowValue);
			this.rowDtype = rowDtype;
		}
	}
}
