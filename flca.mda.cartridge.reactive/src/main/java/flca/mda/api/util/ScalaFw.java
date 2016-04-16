package flca.mda.api.util;

import java.lang.reflect.Field;

import reactive.Tid;
import flca.mda.codegen.helpers.StrUtil;

public class ScalaFw extends Fw implements ScalaTypeConstants {

	protected static TemplateUtils tplu = new TemplateUtils();
	protected static ScalaTypeUtilsHelper th = new ScalaTypeUtilsHelper(new ScalaTypeUtils(), new NameUtils());
	
	public ScalaFw(Field field, Class<?> ownerClass) {
		super(new ScalaTypeUtils(), field, ownerClass);
	}

	public ScalaFw(SpecialField specialField, Class<?> ownerClass) {
		super(new ScalaTypeUtils(), specialField, ownerClass);
	}
	
	
	/**
	 * Surround the input string with Some( and ) if required
	 * 
	 * @param string
	 * @param required
	 * @return
	 */
	public String some(String string, boolean doSurround) {
		return (doSurround) ? SOME + string + ")" : string;
	}

	/**
	 * Surround the input string with Some( and ) if required
	 * 
	 * @param string
	 * @param required
	 * @return
	 */
	public String option(String string, boolean doSurround) {
		return (doSurround) ? OPTION + string + "]" : string;
	}

	/**
	 * Returns the Scala class name for the given Type that will be used in Slick
	 * row's
	 */
	public String getRowTypeName() {

		Class<?> clz = type();
		Class<?> genericType = type(); //TODO

		if (isId()) {
			return OPTION + ScalaDataTypes.getRowTypeName(clz) + "]";
		} else if (isSpecial()) {
			return OPTION + ScalaDataTypes.getRowTypeName(clz) + "]";
		} else if (clz.isPrimitive()) {
			if (isRequired()) {
				return ScalaDataTypes.getRowTypeName(clz);
			} else {
				return OPTION + ScalaDataTypes.getRowTypeName(clz) + "]";
			}
		} else if (tu.isCollection(clz)) {
			if (genericType != null) {
				return ScalaDataTypes.getRowTypeName(clz) + "[" + genericType.getSimpleName() + "]";
			} else {
				return ScalaDataTypes.getRowTypeName(clz);
			}
		} else if (clz.isEnum()) {
			return option("String", !isRequired());
		} else {
			return option(ScalaDataTypes.getRowTypeName(clz), !isRequired());
		}

	}

	@Override
	public String getDefaultValue() {
		if (isId()) {
			return NONE;
		} else if (isSpecial() && getSpecialfield().getDefaultValue() != null) {
			return getSpecialfield().getDefaultValue();
		} else if (isOneToManyField()) {
			return "Set()";
		} else if (isRequired()) {
			return ScalaDataTypes.getDefaultValue(type());
		} else {
			return NONE;
		}
	}

	/**
	 * returns the slick row field datatype
	 * 
	 * @param fw
	 * @return
	 */
	public String rowDatatype() {
		if (isManyToOneField()) {
			return (isRequired()) ? "Long" : OPTION + "Long" + "]";
		} else {
			return getRowTypeName();
		}
	}

	
	@Override
	public String randomValue() {
		String result = null;

		if (isEnum()) {
			String name = type().getSimpleName();
			result = name + ".values.toSeq(intValue(" + name + ".values.size))";
		} else if (isNumeric()) {
			result = "intValue";
		} else if (isDecimal()) {
			result = "doubleValue";
		} else if (isBoolean()) {
			result = "boolValue";
		} else if (isDate()) {
			result = "dateValue";
		} else if (isString()) {
			result = "stringValue";
		} else if (isOneToManyField()) {
			Class<?> typ = type();
			String mockname = tplu.getClassName(typ, Tid.SCALA_ENTITY_MOCK.name());
			// no Some() here, so we return !!
			return "Set(" + mockname + ".makeRandom(None), " + mockname + ".makeRandom(None))";
		} else if (isManyToOneField() || isOneToOneField()) {
			String mockname = tplu.getClassName(type(), Tid.SCALA_ENTITY_MOCK.name());
			result = mockname + ".makeRandom(None)";
		}

		return some(result, !isRequired());
	}
	
	/**
	 * returns a string where the field formats are substituted ex: s =
	 * fieldFormat(fld, "private %t %n = %d;" => private String name = null;
	 * 
	 * @param field
	 * @param formatString
	 * @return
	 */
	public String format(final String formatString) {
		String result = formatString;
		result = StrUtil.replace(result, FieldFormat.NAME.getValue(), name());
		
		String valvar = isVal() ? "val" : "var";
		result = StrUtil.replace(result, FieldFormat.VAR.getValue(), valvar);
		
		if (formatString.indexOf(FieldFormat.OPTIONTYP.getValue()) >= 0) {
			result = StrUtil.replace(result, FieldFormat.OPTIONTYP.getValue(), 
					option(typeName(), needsOption()));
		} else {
			result = StrUtil.replace(result, FieldFormat.TYPE.getValue(), 
					typeName());
			result = StrUtil.replace(result, FieldFormat.OPTION.getValue(), 
					option(typeName(), needsOption()));
		}

		result = StrUtil.replace(result, FieldFormat.DEFAULT.getValue(), getDefaultValue());
		
		if (result.indexOf(FieldFormat.FUNCTION.getValue()) >= 0) {
			result = FwFormatHelper.fieldFormatFunction(this, result);
		}		
		
		return result;
	}

	/*
	 * this one may be called via formatField %f
	 */
	public String getFromRowMapper() {
		if (isDate()) {
			return isRequired() ? "toDate(row." + name() + ")" : "Some(toDate(row." + name() + "))";
		} else if (isEnum()) {
			return type().getSimpleName() + ".parse(row." + name() + ")";
		} else {
			return "row." + name();
		}
	}

	/*
	 * this one may be called via formatField %f
	 */
	public String getToRowMapper() {
		if (isEnum()) {
         return name();
		} else if (isDate() ) {
			return some("toSqlDate(" + OBJ + "." + name() + ")", !isRequired());
		} else if (isManyToOneField() || isOneToOneField()) {
         String fldname = (isEntity()) ? name() + ID_SUFFIX : name();
         return OBJ + "." + fldname;
		} else {
			return OBJ + "." + name();
		}
	}

	/*
	 * this one may be called via formatField %f
	 */
	public String daoRowProperty () {
		String posfix = isRequired() ? ", O.NotNull)" : ")";
		String columname = Q + getColumnName() + Q;
		
		if (isId()) {
			return "def " + name() + " = column[" + type().getSimpleName() + "](" + columname +  ", O.PrimaryKey, O.AutoInc)";
		} else if (isOneToOneField() || isManyToOneField()) {
			String dtype = (isRequired()) ? "Long" : "Option[Long]";
			if (getColumnName().equals(name())) columname = Q + getColumnName() + ID_SUFFIX + Q; 
			return "def " + rowFieldname() + " = column[" +  dtype + "](" + columname + posfix;
		} else {
			return "def " + name() + " = column[" + getRowTypeName() + "](" + columname + posfix;
		}
	}

	/**
	 * returns the slick row fieldname
	 * 
	 * @param fw
	 * @return
	 */
	public String rowFieldname() {
		return isManyToOneField() ? name() : name(); //TODO
	}

	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	} 
	
	@Override 
	public String toString() {
		return super.toString();
	}
}
