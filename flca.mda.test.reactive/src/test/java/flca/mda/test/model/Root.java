package flca.mda.test.model;


import mda.annotation.jpa.ManyToOne;
import mda.type.IEntityType;

public class Root implements IEntityType {

	@ManyToOne
	Nest1a nest1a;

	@ManyToOne
	Nest1b nest1b;
}
