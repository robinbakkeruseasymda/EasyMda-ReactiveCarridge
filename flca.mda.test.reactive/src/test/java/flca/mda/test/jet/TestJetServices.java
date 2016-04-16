package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.akka.ServiceActor;
import backend.akka.ServiceFact;
import backend.akka.ServiceImpl;
import backend.akka.ServiceIntf;
import backend.akka.ServiceMock;
import flca.mda.test.model.TstService;

public class TestJetServices extends TestJetBase {

//	@Test
	public void testIntf() {
		ServiceIntf jet = ServiceIntf.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_INTF.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testImpl() {
		ServiceImpl jet = ServiceImpl.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_IMPL.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testFact() {
		ServiceFact jet = ServiceFact.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_FACT.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testMock() {
		ServiceMock jet = ServiceMock.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_MOCK.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testActor() {
		ServiceActor jet = ServiceActor.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_ACTOR.name()));
		System.out.println(s);
		breakline();
	}

	private void breakline() {
		System.out.println("------------------------------------");
	}
}

