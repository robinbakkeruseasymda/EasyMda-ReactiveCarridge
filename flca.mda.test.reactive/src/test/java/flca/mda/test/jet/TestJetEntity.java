package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.slick.ScalaEntity;
import backend.slick.ScalaEntityDao;
import backend.slick.ScalaEntityDaoBase;
import backend.slick.ScalaEntityMock;
import backend.slick.ScalaTestEntityMappers;
import flca.mda.test.model.Testa;

public class TestJetEntity extends TestJetBase {

	@Test
	public void test() {
		ScalaEntity jet = ScalaEntity.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_ENTITY.name()));
		System.out.println(s);
	}

//	@Test
	public void testDaoBase() {
		ScalaEntityDaoBase jet = ScalaEntityDaoBase.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_ENTITY_DAOBASE.name()));
		System.out.println(s);
	}

//	@Test
	public void testDao() {
		ScalaEntityDao jet = ScalaEntityDao.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_ENTITY_DAO.name()));
		System.out.println(s);
	}

//	@Test
	public void testMock() {
		ScalaEntityMock jet = ScalaEntityMock.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_ENTITY_MOCK.name()));
		System.out.println(s);
	}
	
//	@Test
	public void testMapper() {
		ScalaTestEntityMappers jet = ScalaTestEntityMappers.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_TEST_ENTITY_MAPPERS.name()));
		System.out.println(s);
	}	
}
