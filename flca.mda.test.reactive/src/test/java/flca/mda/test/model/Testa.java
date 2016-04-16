package flca.mda.test.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import mda.annotation.RestService;
import mda.annotation.jpa.Column;
import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.annotation.jpa.Table;
import mda.annotation.scala.Val;
import mda.annotation.validation.NotNull;
import mda.type.IEntityType;

@Table(name="TEST_A")
@RestService
public class Testa implements IEntityType {

	String name;

	@Column(name="GEBOORTE_DATUM")
	@Val
	Date dob;
	
	@NotNull
	int primitiveInt;
	
	Integer simpleInt;
	BigDecimal salaray;
	
	TstEnum testEnum;

	@NotNull
	boolean primitiveBool;
	
	Boolean simpleBool;
	
	@OneToOne
	@JoinColumn(name="TESTB_FK")
	Testb testb;
	
	@OneToMany(mappedBy="testaId")
	Set<Testc> testcs;
}
