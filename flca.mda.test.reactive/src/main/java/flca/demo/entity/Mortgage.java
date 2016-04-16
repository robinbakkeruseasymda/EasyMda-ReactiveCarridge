package flca.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import mda.annotation.jpa.DiscriminatorValue;
import mda.type.IEntityType;

@DiscriminatorValue("M")
public class Mortgage extends FinProd implements IEntityType
{
	Date startDate;
	Date endDate;
	BigDecimal capital;
}
