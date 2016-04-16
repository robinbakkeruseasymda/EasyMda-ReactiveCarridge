package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import reactive.ReactiveScalaTemplates;
import reactive.Tid;
import test.data.ReactiveData;
import test.data.TestConstants;

import com.flca.mda.codegen.helpers.LogHelper;

import flca.mda.codegen.CodegenConstants;
import flca.test.base.AbstractTestTemplates;


public class TestAReactiveTemplates extends AbstractTestTemplates implements TestConstants
{
	@BeforeClass
	public static void beforeOnce() {
		AbstractTestTemplates.beforeOnceBase(new ReactiveData());
		 System.setProperty(CodegenConstants.OVERWRITE_WITHOUT_MERGING, "false");// uncomment if you want to merge
	}

	@AfterClass
	public static void afterOnce() {
		if (LogHelper.getLogbackErrorCount() > startLogbackErrorCount) {
			System.err.println("errors encountered see logfile ");
		}
	}

//	@SuppressWarnings("unused")
   @Test 
	public void testEntityTemplates() 	{
		Class<?> entities[] = new Class<?>[] {
//				Tsta.class, 
//				Tstb.class, 
//				Tstc.class, 
//				Tstd.class,
//				Tste.class,
//				Tstf.class,
//				flca.demo.dto.TstDto.class,
//				FinProd.class,
//				Loan.class,
//				Mortgage.class,
				};
		
		for (Class<?> s : entities) {
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENTITY ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENTITY_DAOBASE ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENTITY_DAO ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_DAO_REQHANDLER ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_DAO_SERVICE_ACTOR ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENTITY_MOCK ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_TEST_ENTITY_DAO ));
//			generate(s, ReactiveScalaTemplates.getTemplate(Tid.SCALA_TEST_ENTITY_MAPPERS ));
		}
	}

	@Test 
	public void testOtherTemplate() {
//		generate(TstEnum.class, ReactiveScalaTemplates.getTemplate(Tid.SCALA_ENUM));
//		generate(TstDto.class, ReactiveScalaTemplates.getTemplate(Tid.SCALA_DTO));
//		generate(TstDto.class, ReactiveScalaTemplates.getTemplate(Tid.SCALA_DTO_MOCK));
	}


//	@SuppressWarnings("unused")
	@Test 
	public void testServiceTemplates() {
		Class<?> services[] = new Class<?>[] {flca.demo.srv.TstService.class};
		
		for (Class<?> service : services) {
//			generate(service, ReactiveScalaTemplates.getTemplate(Tid.SCALA_SERVICE_INTF));
			generate(service, ReactiveScalaTemplates.getTemplate(Tid.SCALA_SERVICE_REQHANDLER));
		}
		
	}
	
	@Test
	public void testAppTemplates() {
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_APP_MAIN));
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_APP_ROUTE));
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_SLICK_DATASOURCE));
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_APP_ROUTE));
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_APP_JSON_MOCKS));
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.SCALA_APP_CONSTANTS));
	}

//	@Test
	public void testValidateModelTemplate() {
//		generate(APP_CLASS, ReactiveScalaTemplates.getTemplate(Tid.VALIDATE_MODEL));
	}
	
//	@Test
	public void testDojoServices() {
		Class<?> services[] = new Class<?>[] {flca.demo.srv.TstService.class};
		
		for (Class<?> service : services) {
//			generate(service, ReactiveDojoConstants.getTemplate(Tid.DOJO_TEST_SERVICES));
		}
	}
	
//	@Test
	public void testDojoAppTemplates() {
//		generate(APP_CLASS, ReactiveDojoConstants.getTemplate(Tid.DOJO_APP_LEFTTREE));
	}
	
}
