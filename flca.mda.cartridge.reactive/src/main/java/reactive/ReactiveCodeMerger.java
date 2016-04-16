package reactive;

import java.io.File;

import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.helpers.AbstractCodeMerger;

public class ReactiveCodeMerger extends AbstractCodeMerger {

	@Override
	public String specialMerge(String aNewCode, File aOldFile, Class<?> aCurrentClass, ITemplate aTemplate) {
		return aNewCode; 
	}

}
