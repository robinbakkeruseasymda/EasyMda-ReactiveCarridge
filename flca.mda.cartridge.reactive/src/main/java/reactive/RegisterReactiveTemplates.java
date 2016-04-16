package reactive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mda.type.IApplicationType;
import mda.type.IRegisterTemplates;
import flca.mda.api.util.ReactiveTypeUtils;
import flca.mda.codegen.CodegenConstants;
import flca.mda.codegen.data.DataStore;
import flca.mda.codegen.data.ITemplate;
import flca.mda.codegen.data.SubsValue;
import flca.mda.codegen.data.SubsValueType;
import flca.mda.codegen.data.TemplatesBranch;
import flca.mda.common.api.helpers.RegisterTemplatesHelper;

public class RegisterReactiveTemplates implements IRegisterTemplates, ReactiveConstants {
	
	private static final ReactiveScalaTemplates sScalaTemplates = new ReactiveScalaTemplates();
	private static final ReactiveDartTemplates sDartTemplates = new ReactiveDartTemplates();
	
	private static final ReactiveTypeUtils tu = new ReactiveTypeUtils();
	
//	private static final ReactiveDojoConstants sReactiveDojoTemplates = new ReactiveDojoConstants();
	
	@Override
	public String getName() {
		return "reactive";
	}

	@Override
	public String getDescription() {
		return "reactive enterprise webapp";
	}

	@Override
	public String getVersion() {
		return "2.1";
	}

	@Override
	public List<ITemplate> getAllTemplates() {
		return sScalaTemplates.getAllTemplates();
	}

	@Override
	public List<TemplatesBranch> getTemplateBranches() {
		List<TemplatesBranch> result = new ArrayList<TemplatesBranch>();

		result.add(makeBranch(sScalaTemplates, "reactive.backend", "Scala reactive backend based spray, akka, slick"));
		result.add(makeBranch(sDartTemplates, "reactive.frontend.dart", "Reactive frontend based on Dart"));
//		result.add(makeBranch(sReactiveDojoTemplates, "reactive.frontend.dojo", "scala reactive frontend based on Dojo"));

		return result;
	}

	private TemplatesBranch makeBranch(ReactiveConstants constants, String aName, String aDescr) {
		TemplatesBranch result = new TemplatesBranch();
		result.setName(aName);
		result.setDescription(aDescr);

		for (ITemplate template : constants.getAllTemplates()) {
			result.addTemplate(template);
		}

		return result;
	}

//	private final static SubsValueType TEXT = SubsValueType.TEXTINPUT;
	private final static SubsValueType FOLDER = SubsValueType.FOLDER;
	private final static String[] ALL_REACTIVE_CARTRIDGES = new String[] {
		ReactiveScalaTemplates.REACTIVE_CARTRIDGE_NAME
	};
	
	@Override
	public Collection<SubsValue> getSubstituteValues() {

		Collection<SubsValue> result = RegisterTemplatesHelper.getCommomSubsValues(ALL_REACTIVE_CARTRIDGES);
		
		result.add(makeSubsValue(ReactiveScalaTemplates.SCALA_BACKEND, FOLDER, "target directory for Scala backend code", 3, null ));

		result.add(makeSubsValue(ReactiveDartTemplates.FRONTEND_DART, FOLDER, "target directory for dart code", 3, null ));

		return result;
	}

	private SubsValue makeSubsValue(String name, SubsValueType type, String help, 
			int rank, String aDefaultValue) {
		return RegisterTemplatesHelper.makeSubsValue(name, type, name, help, rank, aDefaultValue, 
				new String[] {ReactiveScalaTemplates.REACTIVE_CARTRIDGE_NAME});
	}

//	private SubsValue makeComboSubsValue(String name, String label, String[] items, String help, 
//			int rank, String aDefaultValue, String aForCartridges[]) {
//		return RegisterTemplatesHelper.makeComboSubsValue(name, label, items, help, rank, aDefaultValue, aForCartridges);
//	}

	/**
	 * This method is called before the first file(s) is generated.
	 * Here we will fill the SubsVal BASE_PACKAGE with the actual package of the (single) class that implements IApplicationType
	 */
	@Override
	public void doBefore() {
		IApplicationType apptyp = tu.getApplicationBaseType();
		if (apptyp != null) {
			String fqn = apptyp.getClass().getName();
			String pck = fqn.substring(0, fqn.lastIndexOf("."));
			SubsValue subsval =  DataStore.getInstance().getSubsValue(CodegenConstants.BASE_PACKAGE);
			if (subsval != null) {
				subsval.setValue(pck);
			}
		} else {
			System.out.println("Could not find the model class that implements IApplicationType");
		}
	}

	@Override
	public void doAfter() {
		// TODO Auto-generated method stub
	}


}
