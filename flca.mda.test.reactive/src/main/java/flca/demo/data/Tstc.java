package flca.demo.data;

import java.util.Set;

import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.ManyToOne;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;
import flca.demo.entity.Tsta;
import flca.demo.entity.Tstd;
import flca.demo.entity.Tste;

public class Tstc implements IEntityType
{
	String cname;

	@ManyToOne()
	Tsta tsta;
	
	@OneToOne
	@JoinColumn(name="TSTC_TSTD_FK")
	Tstd d;	
	
	@OneToMany(mappedBy="tstcId")
	Set<Tste> tstes;
}

