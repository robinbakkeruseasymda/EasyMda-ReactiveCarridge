package flca.mda.api.util;


public class FetchDepthFieldWrapper extends ScalaFw {

	private Class<?> parentClass;
	
	public FetchDepthFieldWrapper(Class<?> parentClass) {
		super(new SpecialField(FetchDepth.class, ScalaTypeUtils.OFD, false, null, FwSelectType.OFD), parentClass);
		this.parentClass = parentClass;
	}

	@Override
	public String typeName() {
		return new ScalaTypeUtils().getFetchDepthTypeName(parentClass);
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
		return true;
	}

}
