package test.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import flca.mda.api.util.ScalaTypeUtils;
import flca.mda.test.jet.TestJetBase;
import flca.mda.test.model.Root;
import flca.mda.test.model.Testa;

public class TestValidate extends TestJetBase{

	private static final ScalaTypeUtils tu = new ScalaTypeUtils();
	
	@Test
	public void test1() {
		makeJetArgument(new Root());
		List<String> msgs = new ArrayList<>();
		boolean b = tu.validate(tu.currentClass(), msgs);
		System.out.println(msgs);
		Assert.assertTrue(b);
	}

	@Test
	public void test2() {
		makeJetArgument(new Testa());
		List<String> msgs = new ArrayList<>();
		boolean b = tu.validate(tu.currentClass(), msgs);
		System.out.println(msgs);
		Assert.assertTrue(b);
	}

}
