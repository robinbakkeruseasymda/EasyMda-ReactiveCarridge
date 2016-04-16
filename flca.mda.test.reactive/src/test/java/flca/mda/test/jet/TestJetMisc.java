package flca.mda.test.jet;

import org.junit.Test;

import reactive.Tid;
import backend.misc.Dto;
import backend.misc.ScalaEnum;
import flca.mda.test.model.TestDto;
import flca.mda.test.model.TstEnum;

public class TestJetMisc extends TestJetBase {

	@Test
	public void testEnum() {
		ScalaEnum jet = ScalaEnum.create(null);
		String s = jet.generate(makeJetArgument(TstEnum.AAA, Tid.SCALA_ENUM.name()));
		System.out.println(s);
	}

	@Test
	public void testDto() {
		Dto jet = Dto.create(null);
		String s = jet.generate(makeJetArgument(new TestDto(), Tid.SCALA_DTO.name()));
		System.out.println(s);
	}
}
