package flca.mda.test.model;

import java.util.Date;

import mda.type.IEntityType;

public class Testb implements IEntityType {
	String bname;
	int bcount;
	Date bdate;
	
//	option for inversie relation
//	@OneToOne(mappedBy="b")
//	Testa testa;
}
