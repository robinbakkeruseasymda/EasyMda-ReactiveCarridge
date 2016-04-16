package flca.mda.api.util;


public class DiscriminatorFieldWrapper extends ScalaFw {

	private Class<?> parentClass;
	
	private static final ScalaTypeUtils tu = new ScalaTypeUtils();
	
	public DiscriminatorFieldWrapper(Class<?> parentClass) {
		super(new SpecialField(String.class, "dummy", false, "null", FwSelectType.DISCRIMINATOR), parentClass);
		this.parentClass = parentClass;
		this.getSpecialfield().setName(tu.getDiscriminatorName(this.parentClass));
	}

	@Override
	public String typeName() {
		return "String"; //TODO 
	}
	
	@Override
	public boolean isRequired() {
		return false;
	}

	public boolean isVal() {
		return true;
	}
	
	@Override
	public boolean needsOption() {
		return false;
	}

}
