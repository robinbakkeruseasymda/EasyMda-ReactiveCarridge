package flca.mda.test.jet;

import org.junit.Test;

import backend.slick.ScalaTestEntityDao;
import flca.mda.test.model.Testa;

public class TestJetTestEntityDao extends TestJetBase {

	@Test
	public void test() {
		ScalaTestEntityDao jet = ScalaTestEntityDao.create(null);
		String s = jet.generate(makeJetArgument(new Testa()));
		System.out.println(s);
	}

}
