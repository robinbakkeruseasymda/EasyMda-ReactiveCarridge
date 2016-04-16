package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.akka.DaoServiceActor;
import backend.akka.DaoServiceFact;
import backend.akka.DaoServiceImpl;
import backend.akka.DaoServiceIntf;
import backend.akka.DaoServiceMock;
import flca.mda.test.model.Testa;

public class TestJetDaoServices extends TestJetBase {

//	@Test
	public void testIntf() {
		DaoServiceIntf jet = DaoServiceIntf.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_SERVICE_INTF.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testImpl() {
		DaoServiceImpl jet = DaoServiceImpl.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_SERVICE_IMPL.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testFact() {
		DaoServiceFact jet = DaoServiceFact.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_SERVICE_FACT.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testMock() {
		DaoServiceMock jet = DaoServiceMock.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_SERVICE_MOCK.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testActor() {
		DaoServiceActor jet = DaoServiceActor.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_REQHANDLER.name()));
		System.out.println(s);
		breakline();
	}

	private void breakline() {
		System.out.println("------------------------------------");
	}
}

