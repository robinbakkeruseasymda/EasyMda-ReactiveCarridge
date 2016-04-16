package test;


import org.junit.BeforeClass;
import org.junit.Test;

import reactive.ReactiveDartTemplates;
import reactive.Tid;
import test.data.DartWebappData;
import test.data.TestConstants;
import flca.demo.entity.Tsta;
import flca.demo.entity.Tstb;
import flca.demo.entity.Tstd;
import flca.demo.entity.Tste;
import flca.demo.entity.Tstf;
import flca.demo.types.TstEnum;
import flca.mda.codegen.CodegenConstants;
import flca.test.base.AbstractTestTemplates;

public class TestReactiveDartTemplates extends AbstractTestTemplates implements TestConstants {

	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new DartWebappData());
		 System.setProperty(CodegenConstants.OVERWRITE_WITHOUT_MERGING,"true");
	}

	@Test
	public void testEntityTemplates() {
		Class<?> entities[] = new Class<?>[] { Tsta.class, Tstb.class, 
				flca.demo.data.Tstc.class, Tstd.class, Tste.class, Tstf.class,
		};
		entities = new Class<?>[] { Tsta.class };

		for (Class<?> ent : entities) {
			generate(ent, ReactiveDartTemplates.getTemplate(Tid.DART_ENTITY));
//			generate(ent, ReactiveDartTemplates.getTemplate(Tid.DART_ENTITY_BROWSE));
//			generate(ent, ReactiveDartTemplates.getTemplate(Tid.DART_ENTITY_BROWSE_HTML));
//			generate(ent, ReactiveDartTemplates.getTemplate(Tid.DART_ENTITY_FORM));
//			generate(ent, ReactiveDartTemplates.getTemplate(Tid.DART_ENTITY_FORM_HTML));
//			generate(ent, DartConstants.getTemplate(Tid.DART_ENTITY_SEARCH));
//			generate(ent, DartConstants.getTemplate(Tid.DART_ENTITY_SEARCH_HTML));
//			generate(ent, DartConstants.getTemplate(Tid.DART_ENTITY_CTRL));
//			generate(ent, DartConstants.getTemplate(Tid.DART_DAO_SERVICE));
		}
	}

	@Test
	public void testServiceTemplates() {
		Class<?> services[] = new Class<?>[] { flca.demo.srv.TstService.class };

		for (Class<?> service : services) {
//			generate(service, DartConstants.getTemplate(Tid.DART_SERVICE));
//			generate(service, DartConstants.getTemplate(Tid.DART_SERVICE_IMPL));	
//			generate(service, DartConstants.getTemplate(Tid.DART_TEST_SERVICES));
		}
	}

//	@Test
	public void testOtherTemplate() {
		generate(TstEnum.class, ReactiveDartTemplates.getTemplate(Tid.DART_ENUM));
	}

//	@Test
	public void testDtoTemplates() {
		for (Class<?> dto : ALL_DTOS) {
			generate(dto, ReactiveDartTemplates.getTemplate(Tid.DART_DTO));
		}
	}

	@Test
	public void testAppTemplates() {
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_APP_CONSTANTS));
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_SERVICE_BASE));
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_APP_LEFTTREE));
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_APP_MAINCTRL));
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_APP_LIBRARY));
//		generate(APP_CLASS, DartConstants.getTemplate(Tid.DART_ZIP_FILE));
	}

}
