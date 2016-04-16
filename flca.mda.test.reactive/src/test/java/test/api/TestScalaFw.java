package test.api;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;

import org.junit.Test;

import flca.demo.data.Tstc;
import flca.demo.dto.TstDto;
import flca.demo.entity.FinProd;
import flca.demo.entity.Tsta;
import flca.demo.entity.Tstb;
import flca.demo.entity.Tste;
import flca.mda.api.util.Fw;
import flca.mda.api.util.ScalaTypeUtils;
import flca.mda.api.util.TypeConstants;

public class TestScalaFw implements TypeConstants {

	private static final ScalaTypeUtils tu = new ScalaTypeUtils();
	
	//@Test
	public void testGetTypeBigDecimal() {
		Fw fw = tu.getFieldByName(TestClass.class, "salary");
		assertTrue(fw.type() + " instead of "  + "Double", fw.type().equals(BigDecimal.class));
		assertTrue(fw.typeName() + " instead of "  + "Double", fw.typeName().equals("Double"));
	}
	
	//@Test
	public void test01() {
		List<Fw> fields = tu.getFieldsExc(Tsta.class, VAR_FLD);
		String r = tu.format(fields, "%v %n:%o[%t]=%f<getDefaultValue()>", ",\n\t");
		System.out.println(r);
		
		fields = tu.getFieldsExc(Tsta.class, VAL_FLD);
		r = tu.format(fields, "private %v _%n : %o[%t] = %f<getDefaultValue()>", "\n\t");
		System.out.println(r);

		fields = tu.getFieldsExc(Tstc.class, VAL_FLD);
		r = tu.format(fields, "private %v _%n : %o[%t] = %d", "\n\t");
		System.out.println(r);
		
		fields = tu.getFieldsExc(Tstb.class, VAR_FLD);
		System.out.println(fields.size());
		assertTrue(fields.size() == 2);
		
		// deze volgorde moet gelijk blijven
		String prev = null;
		for (int i=0; i < 10; i++) {
			fields = tu.getFieldsExc(FinProd.class, VAR_FLD);
			tu.format(fields, "%n", "");
			if (prev == null) {
				prev = r;
			} else {
				assertTrue(prev.equals(r));
			}
			
		}

	}
	
	//@Test
	public void testSortAllFields() {
		for (int i=0; i<10; i++) {
			List<Fw> fields = tu.getFieldsExc(Tstb.class, VAR_FLD);
			String r = tu.format(fields, "%n,", "");
			assertTrue(r.equals("id,ofd,"));
		}
	}

	//@Test
	public void testGetType() {
		Fw fw = tu.getFieldByName(TestClass.class, "name");
		test("name", fw.type(), String.class);
		fw = tu.getFieldByName(TestClass.class, "primitiveInt");
		test("primitiveInt", fw.type(), int.class);
		fw = tu.getFieldByName(TestClass.class, "simpleInt");
		test("simpleInt", fw.type(), Integer.class);
		fw = tu.getFieldByName(TestClass.class, "many2one");
		test("many2one", fw.type(), TestMany2One.class);
		fw = tu.getFieldByName(TestClass.class, "one2many");
		test("one2many", fw.type(), Set.class);
	}

	//@Test
	public void testGetGenericType() {
		Fw fw = tu.getFieldByName(TestClass.class, "name");
		test("name", fw.genericType(), String.class);
		fw = tu.getFieldByName(TestClass.class, "primitiveInt");
		test("primitiveInt", fw.genericType(), int.class);
		fw = tu.getFieldByName(TestClass.class, "simpleInt");
		test("simpleInt", fw.genericType(), Integer.class);
		fw = tu.getFieldByName(TestClass.class, "many2one");
		test("many2one", fw.genericType(), TestMany2One.class);
		fw = tu.getFieldByName(TestClass.class, "one2many");
		Class<?> gentyp = fw.genericType();
		System.out.println(gentyp);
		test("one2many", fw.genericType(), TestOne2Many.class);
	}
	
	//@Test
	public void testGetTypeName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "String");
		map.put("dob", "Date");
		map.put("primitiveInt", "Int");
		map.put("simpleInt", "Int");
		map.put("salary", "Double");
		map.put("testEnum", "TstEnum.Value");
		map.put("primitiveBool", "Boolean");
		map.put("simpleBool", "Boolean");
		map.put("many2one", "TestMany2One");
		map.put("one2many", "Set[TestOne2Many]");

		for (String name : map.keySet()) {
			Fw fw = tu.getFieldByName(TestClass.class, name);
				String typename = fw.typeName();
				String expectedTypename = map.get(name);
				test(name, typename, expectedTypename);
		}
	}

	//@Test
	public void testGetGenericTypeName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "String");
		map.put("dob", "Date");
		map.put("primitiveInt", "Int");
		map.put("simpleInt", "Int");
		map.put("salary", "Double");
		map.put("testEnum", "TstEnum.Value");
		map.put("primitiveBool", "Boolean");
		map.put("simpleBool", "Boolean");
		map.put("many2one", "TestMany2One");
		map.put("one2many", "TestOne2Many");

		for (String name : map.keySet()) {
			Fw fw = tu.getFieldByName(TestClass.class, name);
				String typename = fw.genericTypeName();
				String expectedTypename = map.get(name);
				test(name, typename, expectedTypename);
		}
	}

	//@Test
	public void testgetMappedByFkField() {
		Fw fw = tu.getFieldByName(Tstc.class, "tstes");
		Fw mappedby = fw.getMappedByFkField();
		System.out.println(mappedby);
		
		fw = tu.getFieldByName(Tste.class, "tstcId");
		mappedby = fw.getMappedByFkField();
		assertTrue(mappedby == null);

		fw = tu.getFieldByName(Tste.class, "fs");
		mappedby = fw.getMappedByFkField();
		assertTrue(mappedby.name().equals("tste"));

		//Class ownerMappedBy = fw.getOwnerClassMappedByFkField()
	}
	
	//@Test
	public void testgetOwnerClassMappedByFkField() {
		Fw fw = tu.getFieldByName(Tste.class, "tstcId");
		Class<?> clz = fw.getOwnerClassMappedByFkField();
		System.out.println(clz);
		
	}
	
	//@Test
	public void testConcreteType() {
		Fw fw = tu.getFieldByName(Tstc.class, "tstes");
		String r = fw.concreteType();
		assertTrue(r.equals("HashSet<Tste>"));
		
		fw = tu.getFieldByName(Tstc.class, "tsta");
		r = fw.concreteType();
		assertTrue(r.equals("Tsta"));

		fw = tu.getFieldByName(Tstc.class, "d");
		r = fw.concreteType();
		assertTrue(r.equals("Tstd"));
	}
	
	@Test
	public void testRandomValue() {
		Fw fw = tu.getFieldByName(TstDto.class, "dtoId");
		String r = fw.randomValue();
		assertTrue(r.equals("Some(intValue)"));
	}
	
	
	private void test(String name, Object value, Object expected) {
		assertTrue(name + " should be " + expected + " instead of " + value, value.equals(expected)); 
	}
	
	//----------------------------------------------------------------

	class TestClass implements IEntityType {
		String name;
		Date dob;
		int primitiveInt;
		Integer simpleInt;
		BigDecimal salary;
		TstEnum testEnum;
		boolean primitiveBool;
		Boolean simpleBool;

		@OneToOne()
		TestOne2One one2one;

		@ManyToOne()
		TestMany2One many2one;

		@OneToMany
		Set<TestOne2Many> one2many;
	}

	class TestMany2One {
		String bname;
	}

	class TestOne2Many {
		String cname;
	}

	class TestOne2One {
		String name;
	}

	enum TstEnum {
		AAA;
	}
	
}
