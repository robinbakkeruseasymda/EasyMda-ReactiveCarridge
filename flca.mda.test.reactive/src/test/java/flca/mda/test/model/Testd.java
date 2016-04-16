package flca.mda.test.model;

import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.ManyToOne;
import mda.type.IEntityType;

public class Testd implements IEntityType {
	String cname;
	
	@ManyToOne
	@JoinColumn(name="TESTA_PK")
	Testa testa;
}
