package flca.mda.test.jet;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;

import reactive.ReactiveScalaTemplates;
import reactive.RegisterReactiveTemplates;
import flca.mda.api.util.JetArgument;
import flca.mda.api.util.TemplateUtils;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.TemplatesBranch;
import flca.mda.codegen.data.TemplatesStore;
import flca.mda.codegen.data.TemplatesStoreData;
import flca.mda.codegen.data.TemplatesTree;

public class TestJetBase {

	private static final String MODELDIR = "flca.mda.test.model.reactive";
	private static final String CARTRIDGEDIR = "flca.mda.reactive.cartridge";

	@BeforeClass
	public static void setupOnce() {
		setupTemplatesStore();
		Set<SubsValue> subsvals = setupSubsValues();
		DataStore.getInstance().setSubsvalues(subsvals);
	}

	private static Set<SubsValue> setupSubsValues() {
		Set<SubsValue> subsvals = new HashSet<>();
		subsvals.add(new SubsValue(CodegenConstants.APP_PACKAGE, "com.base"));
		subsvals.add(new SubsValue(CodegenConstants.APP_NAME, "TestApp"));
		subsvals.add(new SubsValue(CodegenConstants.BASE_PACKAGE, "com.test"));
		return subsvals;
	}

	@Deprecated
	protected JetArgument makeJetArgument(Object obj) {
		if (obj instanceof Class<?>) {
			throw new RuntimeException("Only object are supported, use the other method");
		}
		DataStore.getInstance().setModelProjectDir(new File(MODELDIR));
		JetArgument jetarg = new JetArgument(obj.getClass(), null, null);
		JetArgument.setCurrent(jetarg);
		jetarg.setElement(obj);
		return jetarg;
	}

	protected JetArgument makeJetArgument(Object obj, String templateName) {
		DataStore.getInstance().setModelProjectDir(new File(MODELDIR));
		ITemplate template = new TemplateUtils().getTemplate(templateName);
		JetArgument jetarg = new JetArgument(obj.getClass(), template, null);
		JetArgument.setCurrent(jetarg);
		jetarg.setElement(obj);
		return jetarg;
	}


	protected JetArgument makeJetArgument(Class<?> service, String templateName) {
		DataStore.getInstance().setModelProjectDir(new File(MODELDIR));
		ITemplate template = new TemplateUtils().getTemplate(templateName);
		JetArgument jetarg = new JetArgument(service, template, null);
		JetArgument.setCurrent(jetarg);
		jetarg.setElement(null);
		return jetarg;
	}
	
	private static void setupTemplatesStore() {
		TemplatesBranch branch = new TemplatesBranch();
		branch.setTemplates(new ReactiveScalaTemplates().makeTemplates());
		TemplatesTree tree = new TemplatesTree();
		tree.addBranch(branch);
		TemplatesStoreData tsdata = new TemplatesStoreData(new File(CARTRIDGEDIR), RegisterReactiveTemplates.class, tree);
		Map<File, TemplatesStoreData> tsData = new HashMap<File, TemplatesStoreData>();
		tsData.put(new File(MODELDIR), tsdata);
		TemplatesStore.getInstance().setData(tsData);
	}
}
