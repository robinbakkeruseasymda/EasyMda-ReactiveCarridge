package flca.demo.entity;

import mda.annotation.jpa.Inheritance;
import mda.annotation.jpa.InheritanceType;
import mda.type.IEntityType;

@Inheritance(strategy=InheritanceType.JOINED)
public class FinProd implements IEntityType
{
	String accountNr;
	
}
