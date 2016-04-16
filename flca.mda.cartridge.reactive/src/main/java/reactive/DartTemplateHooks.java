package reactive;

import java.io.File;

import flca.mda.api.util.NameUtils;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.ITemplateHooks;

public class DartTemplateHooks implements ITemplateHooks {

	private static final ReactiveCodeMerger sCodeMerger = new ReactiveCodeMerger();

	private static final NameUtils nu = new NameUtils();
	
	
	@Override
	public void doBeforeGenerate(Class<?> aCurrentClass, ITemplate aTemple) {
	}

	@Override
	public String doAfterGenerate(String aGeneratedSource, Class<?> aCurrentClass, ITemplate aTemplate) {
		return aGeneratedSource;
	}

	@Override
	public String doMerge(String aNewCode, File aOldFile, Class<?> aCurrentClass, ITemplate aTemplate) {
		return sCodeMerger.doMerge(aNewCode, aOldFile, aCurrentClass, aTemplate);
	}

	@Override
	public String doGenerateFilename(String aGeneratedFilename, ITemplate aTemplate, Class<?> aCurrentClass) {
		String result = nu.unCamelLower(aGeneratedFilename, '_');
		return result;
	}

	@Override
	public boolean copyModelClass(Class<?> aModelclass) {
		return false;
	}
}
