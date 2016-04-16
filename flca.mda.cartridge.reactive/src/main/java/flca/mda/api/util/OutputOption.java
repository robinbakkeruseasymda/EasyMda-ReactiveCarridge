package flca.mda.api.util;

import java.util.ArrayList;
import java.util.List;

public enum OutputOption
{
	EXCLUDE_ID,   // By default the Val Id will be include
	EXCLUDE_COMMON, //By default the common Val's (Ohc en Fd) will be included
	EXCLUDE_VAL,
	EXCLUDE_VAR,
	EXCLUDE_ONETOMANY,
	EXCLUDE_MANYTOMANY,
	EXCLUDE_MANYTOONE,
	EXCLUDE_ONETOONE,
	EXCLUDE_RELATIONS,
	WITH_DATATYPE,
	WITH_DEFAULT,
	WITH_PARAMNAME,
	WITH_SOME,   // surround id with Some when nesc.
	WITH_OPTION,  // surround id with Option when nesc.
	WITH_VAL_OR_VAR, // prefix with "val " or "var "
	GIVE_VALUE;  // this is one is used to generated something like id=id, name=name ...
//	NO_SOME, // don't surround with Some
	
	/**
	 * Eventually add OutputOption to input array of OutputOption's
	 * @param opts
	 * @param addOpt
	 * @return
	 */
	public static OutputOption[] addOption(OutputOption[] opts,
			OutputOption... addOpts) {

		OutputOption[] result = opts; 
		List<OutputOption> list = new ArrayList<>();
		for (OutputOption opt : opts) {
			list.add(opt);
		}
		
		for (OutputOption opt : addOpts) {
			if (!list.contains(opt)) {
				list.add(opt);
			} 
		}

		result = new OutputOption[list.size()];
		list.toArray(result);

		return result;
		
	}
	
}
