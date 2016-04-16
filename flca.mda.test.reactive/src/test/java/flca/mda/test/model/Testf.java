package flca.mda.test.model;

import mda.annotation.jpa.ManyToOne;
import mda.type.IEntityType;

public class Testf implements IEntityType {
	String fName;
	int fCount;
	
	@ManyToOne
	Teste teste;
}
