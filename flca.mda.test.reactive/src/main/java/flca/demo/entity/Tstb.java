package flca.demo.entity;

import mda.annotation.jpa.Column;
import mda.annotation.jpa.Id;
import mda.annotation.jpa.Table;
import mda.annotation.jpa.UniqueConstraint;
import mda.type.IEntityType;

@Table(	uniqueConstraints=
	{@UniqueConstraint(columnNames={"bname", "uniek"})}
)
public class Tstb implements IEntityType
{
	@Id
	Long id;
	
	@Column(name="bname")
	String bname;
	
	@Column(name="baantal")
	int baantal;

	@Column(name="uniek")
	int uniek;
	
}
