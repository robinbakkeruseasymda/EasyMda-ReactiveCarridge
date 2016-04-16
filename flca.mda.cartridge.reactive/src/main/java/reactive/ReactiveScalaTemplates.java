package reactive;

import static flca.mda.codegen.data.TemplateMergeStrategy.SIMPLE_MERGE;
import static flca.mda.codegen.data.TemplateMergeStrategy.SKIP;

import java.util.ArrayList;
import java.util.List;

import mda.type.IDtoType;
import backend.app.ValidateModel;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;

public class ReactiveScalaTemplates implements ReactiveConstants {
	
	public static final String SCALA_BACKEND = "Backend"; 

	public static final String REACTIVE_BACKEND_BRANCH = "reactive-backend.scala";
	public static final String SCALA_ZIP_PROJECTNAME = "scala_project";
	
	private static final String SRCGEN = "<%=Backend%>/src-gen";
	private static final String TSTGEN = "<%=Backend%>/src-gen-test";
	
	protected static final String COMMON_PCK = "com.easymda.util";

	private static List<ITemplate> allTemplates;

	public ReactiveScalaTemplates() {
		super();
		if (allTemplates == null) {
			allTemplates = makeTemplates();
		}
	}

	@Override
	public List<ITemplate> getAllTemplates() {
		return allTemplates;
	}
	
	public List<ITemplate> makeTemplates() {
		List<ITemplate> result = new ArrayList<ITemplate>();

		//-- backend.slick
		result.add(make(Tid.SCALA_ENTITY, 				CLS, PCK, SRCGEN, backend.slick.ScalaEntity.class, ENTITIES, 5));
		
		result.add(make(Tid.SCALA_ENTITY_DAOBASE, 	CLS  + "DaoBase", PCK + ".dao",
				SRCGEN, backend.slick.ScalaEntityDaoBase.class, ENTITIES, 5));
//		result.add(make(Tid.SCALA_ENTITY_DAOBASE_NORMAL, 	CLS  + "DaoBase", PCK + ".dao", 
//				SRCGEN, backend.slick.ScalaEntityDaoBaseNormalCls.class, NONE, 5));
//		result.add(make(Tid.SCALA_ENTITY_DAOBASE_BASECLS, 	CLS  + "DaoBase", PCK + ".dao", 
//				SRCGEN, backend.slick.ScalaEntityDaoBaseBaseCls.class, NONE, 5));
//		result.add(make(Tid.SCALA_ENTITY_DAOBASE_SUBSCLS, 	CLS  + "DaoBase", PCK + ".dao", 
//				SRCGEN, backend.slick.ScalaEntityDaoBaseNormalCls.class, NONE, 5));

		result.add(make(Tid.SCALA_ENTITY_DAO, 		CLS  + "Dao", PCK + ".dao", 
				SRCGEN, backend.slick.ScalaEntityDao.class, ENTITIES, 5));

		result.add(make(Tid.SCALA_ENTITY_MOCK, 		CLS  + "Mock", PCK + ".mock", 
				TSTGEN, backend.slick.ScalaEntityMock.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_TEST_ENTITY_DAO, 	"Test" + CLS  + "Dao", PCK + ".test.dao", 
				TSTGEN, backend.slick.ScalaTestEntityDao.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_TEST_ENTITY_MAPPERS, "Test" + CLS  + "Mappers", PCK + ".test", 
				TSTGEN, backend.slick.ScalaTestEntityMappers.class, ENTITIES, 5));

		//-- backend.akka
		result.add(make(Tid.SCALA_DAO_SERVICE_INTF, CLS + "DaoSrv", PCK + ".srv", 
				SRCGEN, backend.akka.DaoServiceIntf.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_DAO_SERVICE_IMPL, CLS + "DaoSrvImpl", PCK + ".srv", 
				SRCGEN, backend.akka.DaoServiceImpl.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_DAO_SERVICE_FACT, CLS + "DaoSrvFact", PCK + ".srv", 
				SRCGEN, backend.akka.DaoServiceFact.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_DAO_SERVICE_MOCK, CLS + "DaoSrvMock", PCK + ".srv.mock", 
				TSTGEN, backend.akka.DaoServiceMock.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_DAO_SERVICE_ACTOR, CLS + "DaoSrvActor" , PCK + ".actor", 
				SRCGEN, backend.akka.DaoServiceActor.class, ENTITIES, 5));
		result.add(make(Tid.SCALA_DAO_REQHANDLER, CLS + "DaoReqHandler" , PCK + ".route", 
				SRCGEN, backend.akka.DaoServiceReqHandler.class, ENTITIES, 5));

		result.add(make(Tid.SCALA_SERVICE_INTF, CLS , PCK , 
				SRCGEN, backend.akka.ServiceIntf.class, SERVICES, 5));
		result.add(make(Tid.SCALA_SERVICE_IMPL, CLS + "Impl", PCK, 
				SRCGEN, backend.akka.ServiceImpl.class, SERVICES, 5));
		result.add(make(Tid.SCALA_SERVICE_FACT, CLS + "Fact", PCK, 
				SRCGEN, backend.akka.ServiceFact.class, SERVICES, 5));
		result.add(make(Tid.SCALA_SERVICE_MOCK, CLS + "Mock", PCK + ".mock", 
				TSTGEN, backend.akka.ServiceMock.class, SERVICES, 5));
		result.add(make(Tid.SCALA_SERVICE_ACTOR, CLS + "Actor", PCK, 
				SRCGEN, backend.akka.ServiceActor.class, SERVICES, 5));
		result.add(make(Tid.SCALA_SERVICE_REQHANDLER, CLS + "ReqHandler", PCK, 
				SRCGEN, backend.akka.ServiceReqHandler.class, SERVICES, 5));

		//-- enum & dto
		result.add(make(Tid.SCALA_ENUM, 	CLS , PCK, SRCGEN, backend.misc.ScalaEnum.class, new Class<?>[] {Enum.class}, 5));
		result.add(make(Tid.SCALA_DTO, 			CLS , PCK, SRCGEN, backend.misc.Dto.class, new Class<?>[] {IDtoType.class}, 5));
		result.add(make(Tid.SCALA_DTO_MOCK, 	CLS+"Mock" , PCK+".mock", TSTGEN, backend.misc.ScalaDtoMock.class, new Class<?>[] {IDtoType.class}, 5));

		//-- app
		result.add(makeAppTemplate(Tid.SCALA_SLICK_DATASOURCE,	APPNAME + "DataSource",  APP_PACKAGE, SRCGEN, backend.app.DataSource.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_DATASTORES,	APPNAME + "DataStores", APP_PACKAGE, SRCGEN, backend.app.DataStores.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_ROUTE,			APPNAME + "Route", APP_PACKAGE, SRCGEN, backend.app.AppRoute.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_MAIN, 			APPNAME + "Main", APP_PACKAGE, SRCGEN, backend.app.AppMain.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_STARTUP,		APPNAME + "Startup", APP_PACKAGE, SRCGEN, backend.app.AppStartup.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_CONSTANTS, 	APPNAME + "Constants", APP_PACKAGE, SRCGEN, backend.app.AppConstants.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_CONFIG, 		APPNAME + "Config", APP_PACKAGE, SRCGEN, backend.app.ApplicationConfig.class, SKIP ));
		//result.add(makeAppTemplate(Tid.SCALA_APP_STATICROUTE, 	APPNAME + "StaticRoute", APP_PACKAGE, SRCGEN, backend.app.AppStaticRoute.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_APP_JSON_MOCKS, 	APPNAME + "JsonMocks", APP_PACKAGE + ".test.mock", 
									TSTGEN, backend.app.AppJsonMocks.class, SKIP ));
		result.add(makeAppTemplate(Tid.SCALA_ZIP_RESOURCE, NA, NA, NA, backend.scala.ScalaZipFile.class, SKIP ));

		result.add(makeDummy(Tid.SCALA_SLICK_PROFILE, "Profile", COMMON_PCK));
		result.add(makeDummy(Tid.SCALA_BASECLASS, 	"BaseClass", COMMON_PCK));
//		result.add(makeDummy(Tid.SCALA_LOGGER, 				"Logger", COMMON_PCK));
		result.add(makeDummy(Tid.SCALA_ROWMAPPERS, 			"RowMappers",COMMON_PCK));
		result.add(makeDummy(Tid.SCALA_JSONUTILS,				"JsonUtils",  COMMON_PCK));
		result.add(makeDummy(Tid.SCALA_CREATION_SUPPORT,	"CreationSupport", COMMON_PCK));
		result.add(makeDummy(Tid.SCALA_RANDOMUTILS,			"RandomUtils", COMMON_PCK));
		
		result.add(makeModelValidate(Tid.SCALA_VALIDATE_MODEL));
		
		return result;
	}

	private ITemplate make(Tid aTid, String classname, String pck, String target, 
			Class<?> jetClass, Class<?>[] applyto, int rank) {
		return make(aTid, classname, pck, target, jetClass, applyto, rank, null);
	}

	private ITemplate make(Tid aTid, String classname, String pck, String target, 
			Class<?> jetClass, Class<?>[] applyto, int rank, String insertTag) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(pck);
		r.setClassname(classname);
		r.setTargetDir(target);
		r.setFileExtension(".scala");
		r.setGeneratorFqn(jetClass.getName());
//		r.setJetPath(jetPath);
		r.setMergeStrategy(SIMPLE_MERGE);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setApplyToClasses(applyto);
		r.setRank(rank);
		r.setInsertionTag(insertTag);
		r.setBranchName(REACTIVE_BACKEND_BRANCH);
		r.setHooks(new ScalaTemplateHooks());
		return r;
	}


	private ITemplate makeAppTemplate(Tid aTid, String classname, String pck, String target,	Class<?> jetClass, 
			String insertTag, TemplateMergeStrategy mergeStrategy ) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(pck);
		r.setClassname(classname);
		r.setTargetDir(target);
		r.setFileExtension(".scala");
		r.setGeneratorFqn(jetClass.getName());
//		r.setJetPath(jetPath);
		r.setMergeStrategy(SIMPLE_MERGE);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setApplyToClasses(APP);
		r.setRank(3);
		r.setInsertionTag(insertTag);
		r.setBranchName(REACTIVE_BACKEND_BRANCH);
		r.setHooks(new ScalaTemplateHooks());
		return r;
	}

	/**
	 * We need this 'dummy' template, that does not actually generate any code (the code is created via the project zip file!)
	 * so that we can lookup this template with commands like: tplu.getClassName(Tid.ROWMAPPERS.name()) 
	 * @param aTid
	 * @param classname
	 * @param pck
	 * @return
	 */
	private ITemplate makeDummy(Tid aTid, String classname, String pck) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(pck);
		r.setClassname(classname);
		r.setTargetDir(NA);
		r.setMergeStrategy(SIMPLE_MERGE);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setApplyToClasses(new Class[] {});
		r.setBranchName(REACTIVE_BACKEND_BRANCH);
		return r;
	}

	private ITemplate makeAppTemplate(Tid aTid, String classname, String pck, String target,	Class<?> jetClass, TemplateMergeStrategy mergeStrategy) {
		return makeAppTemplate(aTid, classname, pck, target, jetClass, null, mergeStrategy);
	}

//	private ITemplate make(Tid aTid, String pck, String target,	Class<?> jetClass, TemplateMergeStrategy mergeStrategy) {
//		return make(aTid, pck, target, jetClass, null, mergeStrategy);
//	}

	private ITemplate makeModelValidate(Tid aTid ) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(APP_PACKAGE);
		r.setClassname("model-validate");
		r.setTargetDir(SRCGEN);
		r.setFileExtension(".txt");
		r.setGeneratorFqn(ValidateModel.class.getName());
//		r.setJetPath(jetPath);
		r.setMergeStrategy(SIMPLE_MERGE);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setApplyToClasses(APP);
		r.setRank(3);
		r.setInsertionTag("validation-messages");
		r.setBranchName(REACTIVE_BACKEND_BRANCH);
		r.setHooks(new ScalaTemplateHooks());
		return r;
	}

	
	public static ITemplate getTemplate(Tid aTid) {
		if (allTemplates == null) {
			new ReactiveScalaTemplates();
		}

		for (ITemplate t : allTemplates) {
			if (t.getName().equals(aTid.name())) {
				return t;
			}
		}
		return null;
	}
	
}
