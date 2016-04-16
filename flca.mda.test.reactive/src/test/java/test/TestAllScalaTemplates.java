package test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import reactive.ReactiveScalaTemplates;
import reactive.Tid;
import test.data.ReactiveData;
import test.data.TestConstants;

import com.flca.mda.codegen.helpers.LogHelper;

import flca.demo.types.TstEnum;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.ITemplate;
import flca.test.base.AbstractTestTemplates;

public class TestAllScalaTemplates extends AbstractTestTemplates implements TestConstants {
	
	private static List<ITemplate> ALL_TEMPLATES;
	
	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new ReactiveData());
		System.setProperty(CodegenConstants.OVERWRITE_WITHOUT_MERGING, "true"); //uncomment if you want to merge
		ReactiveScalaTemplates reactiveScalaTemplates = new ReactiveScalaTemplates();
		ALL_TEMPLATES = reactiveScalaTemplates.makeTemplates();
	}

	@AfterClass
	public static void afterOnce() {
		if (LogHelper.getLogbackErrorCount() > startLogbackErrorCount) {
			System.err.println("errors encountered see logfile ");
		}
	}

	@Test
	public void testEntityTemplates() {
		for (Class<?> ent : ALL_ENTITIES) {
			for (ITemplate template : ALL_TEMPLATES) {
				generate(ent, template);
			}
		}
	}

	@Test
	public void testOtherTemplate() {
		generate(TstEnum.class, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENUM));
	}

	@Test
	public void testServiceTemplates() {
		for (Class<?> service : ALL_SERVICES) {
			for (ITemplate template : ALL_TEMPLATES) {
				generate(service, template);
			}
		}
	}

	@Test
	public void testDtoTemplates() {
		for (Class<?> dto : ALL_DTOS) {
			for (ITemplate template : ALL_TEMPLATES) {
				generate(dto, template);
			}
		}
	}

	@Test
	public void testAppTemplates() {
		for (ITemplate template : ALL_TEMPLATES) {
			generate(APP_CLASS, template);
		}
	}

}