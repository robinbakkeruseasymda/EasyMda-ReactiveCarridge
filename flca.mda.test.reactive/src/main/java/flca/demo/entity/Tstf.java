package flca.demo.entity;

import mda.annotation.jpa.ManyToOne;
import mda.type.IEntityType;

public class Tstf implements IEntityType
{
	String fname;
	int fcount;
	
	@ManyToOne
	Tste tste;
}
