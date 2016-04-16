package test.merge;

import java.io.File;

public class TestJavaMerge2 {

	/**
	 * @generated
	 * We want to keep this documentation aftyer the merge !
	 * @param args
	 */
	public void genMethod(String[] args) {
		System.out.println("we should see this line in the merged code!");
	}
	
	
	//-- and we also want to keep als this code as well
	private static void keepThisMethod() {
		File f = new File(""); // and we want to keep this import as well!
	}

}
