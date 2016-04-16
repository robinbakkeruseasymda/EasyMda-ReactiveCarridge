package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.akka.DaoServiceReqHandler;
import backend.akka.ServiceReqHandler;
import flca.mda.test.model.Testa;
import flca.mda.test.model.TstService;

public class TestJetRoutes extends TestJetBase {

	@Test
	public void testDaoRoute() {
		DaoServiceReqHandler jet = DaoServiceReqHandler.create(null);
		String s = jet.generate(makeJetArgument(new Testa(), Tid.SCALA_DAO_REQHANDLER.name()));
		System.out.println(s);
		breakline();
	}

//	@Test
	public void testServiceRoute() {
		ServiceReqHandler jet = ServiceReqHandler.create(null);
		String s = jet.generate(makeJetArgument(TstService.class, Tid.SCALA_SERVICE_REQHANDLER.name()));
		System.out.println(s);
		breakline();
	}


	private void breakline() {
		System.out.println("------------------------------------");
	}
}

