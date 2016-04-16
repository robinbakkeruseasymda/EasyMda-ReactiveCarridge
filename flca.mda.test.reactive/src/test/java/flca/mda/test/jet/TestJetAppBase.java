package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.app.AppRoute;
import backend.app.AppStartup;
import backend.app.ApplicationConfig;
import backend.app.DataSource;
import backend.app.DataStores;
import flca.demo.ReactiveApp;

public class TestJetAppBase extends TestJetBase {

	//@Test
//	public void testSettings() {
//		backend.app.AkkaSettings jet = AkkaSettings.create(null);
//		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.AKKA_SETTINGS.name()));
//		System.out.println(s);
//		breakline();
//	}

	//@Test
	public void testAppConfig() {
		backend.app.ApplicationConfig jet = ApplicationConfig.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_APP_CONFIG.name()));
		System.out.println(s);
		breakline();
	}

	//@Test
	public void testDataSource() {
		backend.app.DataSource jet = DataSource.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_SLICK_DATASOURCE.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testPopulate() {
		backend.app.AppStartup jet = AppStartup.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_APP_STARTUP.name()));
		System.out.println(s);
		breakline();
	}

	//@Test
	public void testDatastores() {
		backend.app.DataStores jet = DataStores.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_APP_DATASTORES.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testReception() {
		backend.app.AppRoute jet = AppRoute.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_APP_ROUTE.name()));
		System.out.println(s);
		breakline();
	}


	private void breakline() {
		System.out.println("------------------------------------");
	}
}

