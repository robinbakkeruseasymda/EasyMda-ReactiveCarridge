package flca.demo.entity;

import java.util.Set;

import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.type.IEntityType;

public class Tste implements IEntityType
{
	String ename;
	int ecount;
	
	@ManyToOne
	Long tstcId;
	
	@OneToMany(mappedBy="tste")
	Set<Tstf> fs;

}
