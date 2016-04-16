package test.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import reactive.ReactiveConstants;
import reactive.ReactiveDartTemplates;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.helpers.ShellUtils;

public class DartWebappData extends ReactiveData implements TestConstants 
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
	
	@Override
	public List<SubsValue> getSubsvalues() {
		List<SubsValue> r = new ArrayList<SubsValue>();

		r.add(new SubsValue(CodegenConstants.APP_NAME, "Demo"));
		r.add(new SubsValue(ReactiveConstants.FRONTEND_DART, "../flca.mda.test.target/dart"));
		r.add(new SubsValue(CodegenConstants.APP_PACKAGE, ""));		
		
		if (ShellUtils.isLinux()) {
			String basedir = "/tmp/dart/web";
			r.add(new SubsValue(ReactiveDartTemplates.FRONTEND_DART , basedir ));
			
		} else {
			String basedir = "c:/temp/easymda/dart/web";
			r.add(new SubsValue(ReactiveDartTemplates.FRONTEND_DART , basedir ));
		}
		
		
		return r;
	}
}

