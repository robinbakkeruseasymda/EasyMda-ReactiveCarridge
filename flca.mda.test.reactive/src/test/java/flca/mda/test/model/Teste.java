package flca.mda.test.model;

import java.util.Set;

import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.type.IEntityType;

public class Teste implements IEntityType {
	String ename;
	int eCount;
	
	@ManyToOne
	Long testCId;
	
	@OneToMany(mappedBy="teste")
	Set<Testf> fs;
}
