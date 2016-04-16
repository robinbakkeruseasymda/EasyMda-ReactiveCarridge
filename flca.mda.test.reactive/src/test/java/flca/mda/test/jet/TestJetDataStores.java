package flca.mda.test.jet;

import org.junit.Test;

import backend.app.DataStores;
import flca.demo.ReactiveApp;

public class TestJetDataStores extends TestJetBase {

	@Test
	public void test() {
		DataStores jet = DataStores.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp()));
		System.out.println(s);
	}

}
