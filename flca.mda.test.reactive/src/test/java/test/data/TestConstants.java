package test.data;

import flca.demo.ReactiveApp;
import flca.demo.dto.TstDto;
import flca.demo.entity.FinProd;
import flca.demo.entity.Loan;
import flca.demo.entity.Mortgage;
import flca.demo.entity.Tsta;
import flca.demo.entity.Tstb;
import flca.demo.entity.Tstd;
import flca.demo.entity.Tste;
import flca.demo.entity.Tstf;
import flca.demo.types.TstEnum;



public interface TestConstants
{

	String WEBAPP_MODEL_DIR = "flca.mda.test.webapp";
	String REACTIVE_MODEL_DIR = "flca.mda.test.reactive";
	String WEBAPP_CARTRIDE = "flca.mda.templates.webapp";
	String REACTIVE_CARTRIDE = "flca.mda.templates.reactive";
	String DOJO_CARTRIDE = "flca.mda.templates.dojo";
	String WIZARD_CARTRIDGE = "flca.mda.templates.wizzard";
	String PLUGIN_DIR = "flca.mda.generator";
	
	String BACKEND_SRC_GEN = "../flca.mda.test.target/src-backend-generated";
	String ZK_SRC_GEN = "../flca.mda.test.target/src-zk-generated";
	String FLEX_SRC_GEN = "../flca.mda.test.target/src-flex-generated";

	Class<?> ALL_ENTITIES[] = new Class<?>[] { Tsta.class, Tstb.class, 
			flca.demo.data.Tstc.class, Tstd.class, Tste.class, Tstf.class,
			FinProd.class, Loan.class, Mortgage.class };
//			flca.demo.entity.InheritSingleBase.class, 
//			flca.demo.entity.InheritSingleSuperA.class,
//			flca.demo.entity.InheritSingleSuperB.class};

	Class<?> ALL_SERVICES[] = new Class<?>[] {flca.demo.srv.TstService.class};
	
	Class<?> ALL_DTOS[] = new Class<?>[] { TstDto.class };
	
	Class<?> ALL_ENUMS[] = new Class<?>[] {TstEnum.class};
	
	Class<?> APP_CLASS = ReactiveApp.class;
}
