package flca.mda.api.util;

import flca.mda.common.api.helpers.ImportFilter;

public class ScalaImportFilter implements ImportFilter {

	private final static String SKIP_JAVA[] = new String[] {
		"java.util.List", "java.util.Set", "java.util.Map", "java.util.Collection",
		"java.util.ArrayList", "java.util.HashSet", "java.util.HashMap",
	};
	
	@Override
	public String filter(String aImport) {
		if (aImport == null) return null;
		
		for (String skip : SKIP_JAVA) {
			if (aImport.startsWith(skip)) {
				return null;
			}
		}
		
		if (aImport.endsWith(".Value")) return null;
		
		return aImport;
	}

}
