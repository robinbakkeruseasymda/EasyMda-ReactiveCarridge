package reactive;

import java.util.Collection;

import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.helpers.IPostProcess;
import flca.mda.codegen.helpers.SourceCodeUtils;

/**
 * this postprocess will remove import(s) that start with " java" from
 * flex-files and replace some known import(s) by the correct name.
 * 
 * @author nly36776
 * 
 */
public class FormatImportsPostprocessor implements IPostProcess {
	
	private static SourceCodeUtils su = new SourceCodeUtils();


	@Override
	public String parse(String aInput, ITemplate aTemplate, Class<?> aTargetClass) {
		Collection<String> lines = su.getSourcecodeLines(aInput);
		return parseImportStatements(lines);
	}

	@Override
	public boolean isApplicable(ITemplate aTemplate) {
		return true;
	}

	private String parseImportStatements(Collection<String> lines) {
		StringBuffer sb = new StringBuffer();
		for (String line : lines) {
			sb.append(formatScalaImport(line) + "\n");
		}
		return sb.toString();
	}


	private String formatScalaImport(String aImportLine) {
		if (aImportLine.trim().endsWith(";")) {
			return aImportLine.trim().substring(0, aImportLine.trim().length() - 1);
		} else {
			return aImportLine;
		}
	}


}
