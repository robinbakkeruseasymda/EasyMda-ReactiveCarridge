package reactive;

import java.util.Collection;

import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.helpers.IPostProcess;
import flca.mda.codegen.helpers.SourceCodeUtils;

/**
 * In scala you should not import classes that reside in the same package
 * @author robin
 *
 */
public class RemoveSamePackageImportsPostprocessor implements IPostProcess {
	private static SourceCodeUtils su = new SourceCodeUtils();
	
	@Override
	public String parse(String aInput, ITemplate aTemplate, Class<?> aTargetClass)
	{
		String result = aInput;
		StringBuffer sb = new StringBuffer();

		Collection<String> lines = su.getSourcecodeLines(result);
		String thisPackage = getThisPackage(lines);
		if (thisPackage == null) {
			return aInput;
		} else {
			for (String line : lines) {
				if (su.isImportStatement(line)) {
					if (!isInSamePackage(line, thisPackage)) {
						sb.append(line + "\n");
					} // else we skip this import
				} else {
					sb.append(line + "\n");
				}
			}
			return sb.toString();
		}
	}

	@Override
	public boolean isApplicable(ITemplate aTemplate) {
		return true;
	}

	
	private String getThisPackage(Collection<String> lines) {
		for (String line : lines) {
			String pck = su.getPackageClassname(line);
			if (pck != null) {
				return pck;
			}
		}		
		
		return null;
	}
	private boolean isInSamePackage(final String line, final String thisPackage) {
		String importClz = su.getImportClassname(line);
		String pck = importClz.substring(0, importClz.lastIndexOf("."));
		return pck.equals(thisPackage);
	}
}
