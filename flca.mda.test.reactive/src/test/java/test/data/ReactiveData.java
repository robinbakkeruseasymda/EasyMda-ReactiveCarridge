package test.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import reactive.ReactiveConstants;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.helpers.ShellUtils;
import flca.test.base.AbstractTestTemplatesData;


public class ReactiveData extends AbstractTestTemplatesData implements TestConstants
{
	@Override
	public File getPluginDir() {
		return new File(getProjectDir() + "/" + PLUGIN_DIR);
	}

	@Override
	public File getModelDir() {
		return new File(getProjectDir() + "/" + REACTIVE_MODEL_DIR);
	}

	@Override
	public File getTemplateDir() {
		return new File(getProjectDir() + "/" + REACTIVE_CARTRIDE);
	}

	
//	@Override
//	public List<ITemplate> getAllTemplates() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<SubsValue> getSubsvalues() {
		List<SubsValue> r = new ArrayList<SubsValue>();
		r.add(new SubsValue(CodegenConstants.APP_NAME, "Xyz"));
		r.add(new SubsValue(CodegenConstants.APP_PACKAGE, "org.demo"));
		r.add(new SubsValue(ReactiveConstants.BACKEND, BACKEND_SRC_GEN));
		if (ShellUtils.isLinux()) {
			String basedir = "/tmp/easymda/reactive-generated";
			r.add(new SubsValue("Backend" , basedir));
			
		} else {
			String basedir = "c:/temp/easymda/reactive-generated";
			r.add(new SubsValue("Backend" , basedir));
		}
		
		return r;
	}
}

