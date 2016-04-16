package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.common.BaseClass;
import backend.common.CreationSupport;
import backend.common.JsonUtils;
import backend.common.Profile;
import backend.common.RowMappers;
import flca.demo.ReactiveApp;

public class TestJetCommon extends TestJetBase {

	@Test
	public void testBaseclass() {
		BaseClass jet = BaseClass.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_BASECLASS.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testRowMapper() {
		RowMappers jet = RowMappers.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_ROWMAPPERS.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testJsonUtils() {
		JsonUtils jet = JsonUtils.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_JSONUTILS.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testCreationSupport() {
		CreationSupport jet = CreationSupport.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_CREATION_SUPPORT.name()));
		System.out.println(s);
		breakline();
	}

	@Test
	public void testProfile() {
		Profile jet = Profile.create(null);
		String s = jet.generate(makeJetArgument(new ReactiveApp(), Tid.SCALA_SLICK_PROFILE.name()));
		System.out.println(s);
		breakline();
	}


	private void breakline() {
		System.out.println("------------------------------------");
	}
}

