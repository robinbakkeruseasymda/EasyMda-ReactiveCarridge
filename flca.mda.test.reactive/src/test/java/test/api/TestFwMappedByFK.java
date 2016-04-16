package test.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import test.data.ReactiveData;
import flca.demo.data.Tstc;
import flca.demo.entity.Tsta;
import flca.mda.api.util.Fw;
import flca.mda.api.util.ScalaTypeUtils;
import flca.mda.api.util.TypeConstants;
import flca.test.base.AbstractTestTemplates;

/**
 * this class checks if the Fw methods that return the corresponding "mapped-by" field are correct.
 * Consider class Tsta :
    @OneToMany(mappedBy="tsta")
	Set<Tstc> tstcs;
	
	And inside class Tstc :
	@ManyToOne()
	Tsta tsta;
	
	From Tsta.tsts the mapped-by field should be Tstc.tsta
	From Tstc.tsta the mapped-by field should be Tsta.tstcs
 * @author ed45064
 *
 */
public class TestFwMappedByFK extends AbstractTestTemplates implements TypeConstants {

	private static final ScalaTypeUtils tu = new ScalaTypeUtils();
	
	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new ReactiveData());
	}

	@Test
	public void testhasMappedByFkField() {
		Fw fw1 = tu.getFieldByName(Tsta.class, "tstcs");
		assertTrue(fw1.hasMappedByFkField());
		
		Fw fw2 = tu.getFieldByName(Tstc.class, "tsta");
		assertFalse(fw2.hasMappedByFkField());
	}


	@Test
	public void testgetOwnerClassMappedByFkField() {
		Fw fw1 = tu.getFieldByName(Tstc.class, "tsta");
		Class<?> ownerClass1 = fw1.getOwnerClassMappedByFkField();
		assertTrue(ownerClass1.getSimpleName().equals("Tsta"));
		
		Fw fw2 = tu.getFieldByName(Tsta.class, "tstcs");
		Class<?> ownerClass2 = fw2.getOwnerClassMappedByFkField();
		assertTrue(ownerClass2 == null);
	}
	
	
	@Test 
	public void testgetOwnerMappedByFkField() {
		Fw fw1 = tu.getFieldByName(Tsta.class, "tstcs");
		Fw fkFw1 = fw1.getOwnerMappedByFkField();
		assertTrue(fkFw1 != null && fkFw1.name().equals("tsta"));
		
		Fw fw2 = tu.getFieldByName(Tstc.class, "tsta");
		Fw fkFw2 = fw2.getOwnerMappedByFkField();
		assertTrue(fkFw2 == null);
	}
	
	@Test
	public void testgetMappedByFkFieldName() {
		Fw fw1 = tu.getFieldByName(Tsta.class, "tstcs");
		String fkName1 = fw1.getMappedByFkFieldName();
		assertTrue(fkName1 != null && fkName1.equals("tsta"));
		
		Fw fw2 = tu.getFieldByName(Tstc.class, "tsta");
		String fkName2 = fw2.getMappedByFkFieldName();
		assertTrue(fkName2 != null && fkName2.startsWith("?"));
	}
	
}
