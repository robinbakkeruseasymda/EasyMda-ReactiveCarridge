package flca.mda.test.model;

import java.util.Set;

import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;

public class Testc implements IEntityType {
	String cname;
	
	@ManyToOne
	@JoinColumn(name="TESTA_PK")
	Testa testaId;
	
	@OneToOne
	@JoinColumn(name="FK_TESTC_TESTD")
	Testd testd;
	
	@OneToMany(mappedBy="testCId")
	Set<Teste> es;	
}
