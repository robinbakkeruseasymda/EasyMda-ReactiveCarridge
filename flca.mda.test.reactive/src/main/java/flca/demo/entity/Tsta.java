package flca.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import mda.annotation.Gui;
import mda.annotation.RestService;
import mda.annotation.jpa.JoinColumn;
import mda.annotation.jpa.OneToMany;
import mda.annotation.jpa.OneToOne;
import mda.type.IEntityType;
import flca.demo.data.Tstc;
import flca.demo.types.TstEnum;

@RestService (generateService=true)
@Gui
public class Tsta implements IEntityType
{
	@mda.annotation.validation.NotNull
	String name;
	
	Date dob;
	int count;
	BigDecimal salary;
	boolean flag;
	TstEnum testEnum;
	
//	@AnnotationLiteral(annotation="@com.xxx.SuperAnnotation(flag=true)")
	String testLiteralAnno;
	
	@OneToOne
	@JoinColumn(name="TSTA_TSTB_FK")
	Tstb b;
	
	@OneToMany(mappedBy="tsta")
	Set<Tstc> tstcs;
}
