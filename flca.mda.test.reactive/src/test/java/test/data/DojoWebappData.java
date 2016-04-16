package test.data;

import java.io.File;
import java.util.List;

import reactive.ReactiveConstants;
import flca.mda.codegen.data.SubsValue;

public class DojoWebappData extends ReactiveData implements TestConstants 
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
		return new File(getProjectDir() + "/" + WEBAPP_CARTRIDE);
	}
	
	@Override
	public List<SubsValue> getSubsvalues() {
		List<SubsValue> r = super.getSubsvalues();

		r.add(new SubsValue(ReactiveConstants.FRONTEND_DOJO, "../flca.mda.test.target/dojo"));
		
//		if (ShellUtils.isLinux()) {
//			r.add(new SubsValue(WebappConstants.FRONTEND_DOJO, "/tmp/DojoTestGenerated"));
//		} else {
//			r.add(new SubsValue(WebappConstants.FRONTEND_DOJO, "../flca.mda.test.target/dojo"));
//		}
		
		return r;
	}
}

