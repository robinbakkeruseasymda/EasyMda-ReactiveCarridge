package flca.demo.entity;

import java.math.BigDecimal;

import mda.annotation.jpa.DiscriminatorValue;
import mda.type.IEntityType;

@DiscriminatorValue("L")
public class Loan extends FinProd implements IEntityType
{
	BigDecimal amount;
}
