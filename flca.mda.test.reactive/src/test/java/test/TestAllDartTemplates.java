package test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import reactive.ReactiveDartTemplates;
import reactive.Tid;
import test.data.DartWebappData;
import test.data.TestConstants;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.ITemplate;
import flca.test.base.AbstractTestTemplates;

public class TestAllDartTemplates extends AbstractTestTemplates implements TestConstants {
	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new DartWebappData());
		// uncomment if you want to merge
		System.setProperty(CodegenConstants.OVERWRITE_WITHOUT_MERGING, "true"); 
	}

	@Test
	public void testAllDart() {
		List<ITemplate> tids = new ReactiveDartTemplates().getAllTemplates();

		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_ZIP_FILE));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_CONSTANTS));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_SERVICE_BASE));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_LIBRARY));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_LEFTTREE));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_MAINCTRL));
		generate(APP_CLASS, ReactiveDartTemplates.getTemplate(Tid.DART_APP_COMPONENTS));
		
		for (Class<?> object : ALL_ENTITIES ) {
			for (ITemplate t : tids) {
				generate(object, t);
			}
		}

		for (Class<?> object : ALL_SERVICES) {
			for (ITemplate t : tids) {
				generate(object, t);
			}
		}
		
		for (Class<?> dto : ALL_DTOS) {
			generate(dto, ReactiveDartTemplates.getTemplate(Tid.DART_DTO));
		}

		for (Class<?> enumClass : ALL_ENUMS) {
			generate(enumClass, ReactiveDartTemplates.getTemplate(Tid.DART_ENUM));
		}
	}


}
