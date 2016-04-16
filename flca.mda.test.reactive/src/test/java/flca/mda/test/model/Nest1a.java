package flca.mda.test.model;

import mda.annotation.jpa.ManyToOne;
import mda.type.IEntityType;

public class Nest1a implements IEntityType {

	@ManyToOne
	Nest2 nest2;
}
