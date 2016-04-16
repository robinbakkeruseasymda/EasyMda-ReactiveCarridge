package reactive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import flca.mda.api.util.ScalaImportFilter;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.ITemplateHooks;
import flca.mda.codegen.helpers.IPostProcess;
import flca.mda.common.api.helpers.ImportHelper;

public class ScalaTemplateHooks implements ITemplateHooks {

	private static final ReactiveCodeMerger sCodeMerger = new ReactiveCodeMerger();

	private static List<IPostProcess> sPostprocessors = null;
	static {
		sPostprocessors = new ArrayList<>();
		sPostprocessors.add(new RemoveSamePackageImportsPostprocessor());
		sPostprocessors.add(new ReplaceImportWildcardPostprocessor());
	}
	
	@Override
	public void doBeforeGenerate(Class<?> aCurrentClass, ITemplate aTemple) {
		ImportHelper.importFilter = new ScalaImportFilter();
	}

	@Override
	public String doAfterGenerate(String aGeneratedSource, Class<?> aCurrentClass, ITemplate aTemplate) {
		
		String result = aGeneratedSource;
		
		for (IPostProcess postproc : sPostprocessors) {
			if (result != null && !result.isEmpty()) {
				result = postproc.parse(result, aTemplate, aCurrentClass);
			}
		}
		return result;
	}

	@Override
	public String doMerge(String aNewCode, File aOldFile, Class<?> aCurrentClass, ITemplate aTemplate) {
		return sCodeMerger.doMerge(aNewCode, aOldFile, aCurrentClass, aTemplate);
	}

	@Override
	public String doGenerateFilename(String aGeneratedFilename, ITemplate aTemplate, Class<?> aCurrentClass) {
		return null;
	}

	@Override
	public boolean copyModelClass(Class<?> aModelclass) {
		return false;
	}

	
}
