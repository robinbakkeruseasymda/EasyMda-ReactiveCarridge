package test.api;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import flca.mda.api.util.Fw;
import flca.mda.api.util.FwSelectType;
import flca.mda.api.util.JetArgument;
import flca.mda.api.util.NameUtils;
import flca.mda.api.util.NestedObjects;
import flca.mda.api.util.ScalaTypeUtils;
import flca.mda.api.util.TypeConstants;
import flca.mda.test.jet.TestJetBase;
import flca.mda.test.model.Testa;

public class TestScalaUtils extends TestJetBase implements TypeConstants {
	private static final ScalaTypeUtils tu = new ScalaTypeUtils();
	private static final NameUtils nu = new NameUtils();

	private static final String[] A_FIELDS = new String[] { "id", "name", "primitiveInt", "simpleInt", "salaray", "testEnum", "simpleBool" };

	@Test
	public void testGetAllValParams1() {
		setCurrentJetArgument(Testa.class);
		List<Fw> r = tu.getFieldsExc(Testa.class, FwSelectType.VAR);
		System.out.println("testGetAllValParams1 = " + r);
		assertTrue(r.size() == 3);
	}

	@Test
	public void testScalaDtypes1() {
		setCurrentJetArgument(Testa.class);
		for (Fw fw : tu.getAllFields(Testa.class)) {
			System.out.println(fw.typeName());
		}
	}

	@Test
	public void testGetProfileMixins() {
		setCurrentJetArgument(Testa.class);
		String r = tu.getProfileMixins();
		System.out.println(r);
	}

	@Test
	public void testGetRowCloneParams() {
		setCurrentJetArgument(Testa.class);
		String r = tu.getRowCloneParams(tu.currentClass());
		System.out.println(r);
	}

	@Test
	public void testGetIdTypeName() {
		setCurrentJetArgument(Testa.class);
		for (Fw fw : tu.getFieldsInc(tu.currentClass(), M2O_FLD)) {
			String r = tu.getIdTypeName(fw.type());
			System.out.println(r);
		}
	}

	@Test
	public void testIfFieldIsRequired() {
		setCurrentJetArgument(Testa.class);
		for (Fw fw : tu.getFieldsExc(tu.currentClass(), VAL_FLD, REL_FLD)) {
			boolean b = fw.isRequired();
			System.out.println(fw.name() + " = " + b);
		}
	}

	@Test
	public void testGetFields() {
		setCurrentJetArgument(Testa.class);
		List<Fw> fields = tu.getFieldsExc(Testa.class, FwSelectType.NONE);
		validateHasFields(A_FIELDS, fields);
		validateHasFields(new String[] { "testcs", "testb" }, fields);
		System.out.println(fields);
		fields = tu.getFieldsExc(Testa.class, M2O_FLD, M2M_FLD);
		validateHasFields(A_FIELDS, fields);
		validateDoesNotHasFields(new String[] { "many2one" }, fields);
		System.out.println(fields);
		fields = tu.getFieldsExc(Testa.class, M2O_FLD, M2M_FLD);
		validateHasFields(A_FIELDS, fields);
		System.out.println(fields);
		fields = tu.getFieldsExc(Testa.class, O2M_FLD, O2O_FLD);
		validateHasFields(A_FIELDS, fields);
		validateDoesNotHasFields(new String[] { "one2many" }, fields);
		System.out.println(fields);
		fields = tu.getFieldsExc(Testa.class, VAL_FLD);
		validateDoesNotHasFields(new String[] { "id", "dob" }, fields);
		System.out.println(fields);
	}

	@Test
	public void testGetAllRowParams() {
		setCurrentJetArgument(Testa.class);
		String r1 = tu.getRowCaseClassParams(Testa.class);
		System.out.println(r1);
	}

	@Test
	public void testSplitLine() {
		String s = "this is a very,long line, and should, be splitted, let see";
		String r = nu.splitLongLine(s, 10);
		System.out.println(r);
		String elem[] = r.split("/n");
		Assert.assertTrue(elem.length == 1);// TODO
	}

	@Test
	public void testmakeIsFetchDefinedStr() {
		String r1 = tu.makeIsFetchDefinedStr(Testa.class);
		System.out.println(r1);
	}

	@Test
	public void testMakeRetrieveStr() {
		String r1 = tu.genRetrieveNestedObjectsStr(Testa.class);
		System.out.println(r1);
	}

	@Test
	public void testmakeRetrieveNestedObjectsStr() {
		String r1 = tu.genRetrieveNestedObjectsStr(Testa.class);
		System.out.println(r1);
	}

	@Test
	public void testGetNestedObjects() {
		NestedObjects result = tu.getNestedObjects(Testa.class);
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.getNestedObjects().size() == 5);
		System.out.println(result);
	}

	@Test
	public void testGenRetrieveAndMergeNestedObjects() {
		String r1 = tu.genRetrieveAndMergeNestedObjects(Testa.class);
		System.out.println(r1);
	}

	@Test
	public void testTrim() {
		String s = ",aaa, bbb, ccc, ";
		String r = nu.trimChars(s, ",");
		Assert.assertTrue(r.equals("aaa, bbb, ccc"));
	}

	@Test
	public void testFieldFormat() {
		Fw fw = tu.getFieldByName(Testa.class, "name");
		String fmt = "private %v %n:%t = %d";
		String result = fw.format(fmt);
		Assert.assertTrue(result.equals("private var name:String = None"));
	}

	@Test
	public void testgetProfileMixins() {
		String r = tu.getProfileMixins();
		System.out.println(r);
		assertTrue(r.length() == 70);
	}

	@Test
	public void testgenRetrieveNestedObjectsStr() {
		String r = tu.genRetrieveNestedObjectsStr(Testa.class);
		System.out.println(r);
		assertTrue(r.length() == 812);
	}

	@Test
	public void test() {
		StringBuffer sb = new StringBuffer();
		for (Fw fw : tu.getAllFields(Testa.class)) {
			String fldname = nu.capName(fw.name());
			boolean b1 = fw.isEntity();
//			boolean b2 = fw.isManyToOneField();
//			boolean b3 = fw.isOneToManyField();
//			boolean b4 = fw.isOneToOneField();
			boolean b5 = fw.isOwner();
			boolean b6 = fw.isRelation();
			if (b1 && b5 && b6) {
				sb.append(fldname + ", ");
			}
		}
		System.out.println(sb.toString());
	}

	// ----------------

	private void setCurrentJetArgument(Class<?> aClass) {
		JetArgument jetarg = new JetArgument(aClass, null, null);
		JetArgument.setCurrent(jetarg);
	}

	private void validateHasFields(String[] names, List<Fw> fields) {
		for (String name : names) {
			Assert.assertTrue("field " + name + " is NOT included", hasFieldname(name, fields));
		}
	}

	private void validateDoesNotHasFields(String[] names, List<Fw> fields) {
		for (String name : names) {
			Assert.assertTrue("field " + name + " IS included", !hasFieldname(name, fields));
		}
	}

	private boolean hasFieldname(String name, List<Fw> fields) {
		for (Fw fw : fields) {
			if (fw.name().equals(name))
				return true;
		}
		return false;
	}

}
