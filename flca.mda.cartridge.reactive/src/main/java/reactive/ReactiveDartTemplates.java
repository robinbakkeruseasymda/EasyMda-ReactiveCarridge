package reactive;

import static flca.mda.codegen.data.TemplateMergeStrategy.SIMPLE_MERGE;

import java.util.ArrayList;
import java.util.List;

import mda.type.IApplicationType;
import reactive.dart.DartAppConstants;
import reactive.dart.DartAppLibrary;
import reactive.dart.DartBrowseForm;
import reactive.dart.DartBrowseFormHtml;
import reactive.dart.DartComponents;
import reactive.dart.DartDaoService;
import reactive.dart.DartDetailForm;
import reactive.dart.DartDetailFormHtml;
import reactive.dart.DartDto;
import reactive.dart.DartEntity;
import reactive.dart.DartEntityController;
import reactive.dart.DartEnum;
import reactive.dart.DartLeftTree;
import reactive.dart.DartMainCtrl;
import reactive.dart.DartSearchForm;
import reactive.dart.DartSearchFormHtml;
import reactive.dart.DartService;
import reactive.dart.DartServiceBase;
import reactive.dart.DartServiceImpl;
import reactive.dart.DartTestService;
import reactive.dart.DartZipFile;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.Template;
import flca.mda.codegen.data.TemplateMergeStrategy;

public class ReactiveDartTemplates implements ReactiveConstants  {
	
	public static final String FRONTEND_DART = "dart";
	public static final String REACTIVE_DART_BRANCH = "reactive-backend.dart";
	protected static final String NA = "N.A";
	
	protected static final String SRCGEN = LE + FRONTEND_DART + RI + "/web";

	private static List<ITemplate> allTemplates;
	
	public ReactiveDartTemplates() {
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

		result.add(make(Tid.DART_DTO, 				CLS, PCK, SRCGEN, DartDto.class, DTOS, 5));
		result.add(make(Tid.DART_ENUM, 				CLS, PCK, SRCGEN, DartEnum.class, ENUMS, 5));
		result.add(make(Tid.DART_ENTITY, 			CLS, PCK, SRCGEN, DartEntity.class, ENTITIES, 5));
		result.add(make(Tid.DART_ENTITY_FORM, 		CLS + "Form", PCK + ".view", SRCGEN, DartDetailForm.class, ENTITIES, 5));
		result.add(make(Tid.DART_ENTITY_BROWSE, 	CLS + "Browse", PCK + ".view", SRCGEN, DartBrowseForm.class, ENTITIES, 5));
		result.add(make(Tid.DART_ENTITY_SEARCH, 	CLS, PCK, SRCGEN, DartSearchForm.class, ENTITIES, 5));
		result.add(make(Tid.DART_ENTITY_CTRL, 		CLS + "Ctrl", PCK + ".ctrl", SRCGEN, DartEntityController.class, ENTITIES, 5));
		result.add(make(Tid.DART_DAO_SERVICE, 		CLS + "DaoService", PCK + ".srv", SRCGEN, DartDaoService.class, ENTITIES, 5));
		result.add(make(Tid.DART_SERVICE, 			CLS, PCK, SRCGEN, DartService.class, SERVICES, 5));
		result.add(make(Tid.DART_SERVICE_IMPL, 		CLS, PCK, SRCGEN, DartServiceImpl.class, SERVICES, 5));
		result.add(make(Tid.DART_TEST_SERVICES, 	CLS, PCK, SRCGEN, DartTestService.class, SERVICES, 5));
//		result.add(make(Tid.DART_ENTITY_SEARCH, 	CLS, PCK, SRCGEN, DartS.class, ENTITIES, 5));

		result.add(makeHtml(Tid.DART_ENTITY_FORM_HTML, CLS + "Form", PCK + ".view", SRCGEN, DartDetailFormHtml.class, ENTITIES, 5));
		result.add(makeHtml(Tid.DART_ENTITY_BROWSE_HTML, CLS + "Browse", PCK + ".view", SRCGEN, DartBrowseFormHtml.class, ENTITIES, 5));
		result.add(makeHtml(Tid.DART_ENTITY_SEARCH_HTML, CLS, PCK, SRCGEN, DartSearchFormHtml.class, ENTITIES, 5));

		result.add(makeApp(Tid.DART_APP_ZIP_FILE, NA, NA, DartZipFile.class, NA, null, 1));
		result.add(makeAppDart(Tid.DART_APP_CONSTANTS, APPNAME + "Constants", "", DartAppConstants.class, null, 2));
		result.add(makeAppDart(Tid.DART_APP_LIBRARY, APPNAME + "Library", "", DartAppLibrary.class, null, 2));
		result.add(makeAppDart(Tid.DART_APP_SERVICE_BASE,  APPNAME + "ServiceBase", "", DartServiceBase.class, null, 2));
		result.add(makeAppDart(Tid.DART_APP_MAINCTRL, "MainController", "ctrl", DartMainCtrl.class, null, 2));
		result.add(makeAppDart(Tid.DART_APP_LEFTTREE, "LeftTree", "view", DartLeftTree.class, null, 2));
		result.add(makeAppHtml(Tid.DART_APP_COMPONENTS, APPNAME + "_components", "", DartComponents.class, null, 2));

		return result;
	}

	private ITemplate make(Tid aTid, String classname, String pck, String target, 
			Class<?> jetClass, Class<?>[] applyto, int rank) {
		return make(aTid, classname, pck, target, jetClass, applyto, rank, null, ".dart");
	}

	private ITemplate makeHtml(Tid aTid, String classname, String pck, String target, 
			Class<?> jetClass, Class<?>[] applyto, int rank) {
		return make(aTid, classname, pck, target, jetClass, applyto, rank, null, ".html");
	}

	private ITemplate make(Tid aTid, String classname, String pck, String target, 
			Class<?> jetClass, Class<?>[] applyto, int rank, String insertTag, String ext) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(pck);
		r.setClassname(classname);
		r.setTargetDir(target);
		r.setFileExtension(ext);
		r.setGeneratorFqn(jetClass.getName());
//		r.setJetPath(jetPath);
		r.setMergeStrategy(SIMPLE_MERGE);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setApplyToClasses(applyto);
		r.setRank(rank);
		r.setInsertionTag(insertTag);
		r.setBranchName(REACTIVE_DART_BRANCH);
		r.setHooks(new DartTemplateHooks());
		return r;
	}

//	private ITemplate makeApp(Tid aTid, String aClassname, Class<?> aJetClass) {
//		return makeApp(aTid, aClassname, aJetClass, null, 3);
//	}
	
//	private ITemplate makeApp(Tid aTid, String aClassname, Class<?> aJetClass, String aInsertTag, int aRank) {
//		return makeApp(aTid, aClassname, APP_PACKAGE, aJetClass, aInsertTag, aRank);
//	}

	private ITemplate makeAppHtml(Tid aTid, String aClassname, String aPackage, Class<?> aJetClass, String aInsertTag, int aRank) {
		return makeApp(aTid, aClassname, aPackage, aJetClass, ".html", aInsertTag, aRank);
	}

	private ITemplate makeAppDart(Tid aTid, String aClassname, String aPackage, Class<?> aJetClass, String aInsertTag, int aRank) {
		return makeApp(aTid, aClassname, aPackage, aJetClass, ".dart", aInsertTag, aRank);
	}

	private ITemplate makeApp(Tid aTid, String aClassname, String aPackage, Class<?> aJetClass, String aExt, String aInsertTag, int aRank) {
		Template r = new Template();
		r.setName(aTid.name());
		r.setPackage(aPackage);
		r.setClassname(aClassname);
		r.setTargetDir(SRCGEN); //todo DART_WEB
		r.setFileExtension(aExt);
		r.setGeneratorFqn(aJetClass.getName());
		r.setJetPath("/frontend/dart/" + aTid.name() +  ".jsjet");
		r.setMergeStrategy(TemplateMergeStrategy.SKIP);
		r.setCartridgeName(REACTIVE_CARTRIDGE_NAME);
		r.setBranchName(REACTIVE_DART_BRANCH);
		r.setApplyToClasses(new Class<?> [] {IApplicationType.class});
		r.setInsertionTag(aInsertTag);
		r.setRank(aRank);
		r.setHooks(new DartTemplateHooks());
		return r;
	}
	

	public static ITemplate getTemplate(Tid aTid) {
		if (allTemplates == null) {
			new ReactiveDartTemplates();
		}
		
		for (ITemplate t : allTemplates) {
			if (t.getName().equals(aTid.name())) {
				return t;
			}
		}
		return null;
	}


}
