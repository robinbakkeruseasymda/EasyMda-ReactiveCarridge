package flca.mda.api.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import mda.annotation.Gui;
import mda.annotation.crud.CrudOperation;
import mda.type.IEntityType;
import mda.type.IServiceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flca.mda.codegen.helpers.FilenameHelper;


public class ReactiveTypeUtils extends TypeUtils {

	public static Logger logger = LoggerFactory.getLogger(ReactiveTypeUtils.class);
	
	protected static NameUtils nu = new NameUtils();
	protected static InterfaceUtils iu = new InterfaceUtils();
	protected static TemplateUtils tplu = new TemplateUtils();
	protected static ValidatorUtils vu = new ValidatorUtils();
	protected static ImportUtils impu = new ImportUtils();
	protected static FilenameHelper fnh = new FilenameHelper();
	protected static ScalaTypeUtilsHelper th = new ScalaTypeUtilsHelper(new ScalaTypeUtils(), new NameUtils());

	/**
	 * indicates if a Json/Rest service should be generated for the given entity
	 * @param aEntity
	 * @return
	 */
	public boolean generateRestService(Class<?> aEntity) {
		return th.generateRestService(aEntity);
	}

	/**
	 * indicates if the given entity has Gui annotation
	 * @param aEntity
	 * @return
	 */
	public boolean hasGuiAnnotation(Class<?> aEntity) {
		return hasAnnotation(aEntity, Gui.class);
	}
	
	/**
	 * return bool that indicates that Gui grid should be generated
	 * @param aEntity
	 * @return
	 */
	public boolean generateGuiGrid(Class<?> aEntity) {
		if (hasGuiAnnotation(aEntity)) {
			return ((Gui) getAnnotation(aEntity, Gui.class)).generateGrid();
		} else {
			return false;
		}
	}

	/**
	 * return bool that indicates that Gui detail-form should be generated
	 * @param aEntity
	 * @return
	 */
	public boolean generateGuiForm(Class<?> aEntity) {
		if (hasGuiAnnotation(aEntity)) {
			return ((Gui) getAnnotation(aEntity, Gui.class)).generateForm();
		} else {
			return false;
		}
	}

	
	/**
	 * return all service methods of all the modelled services
	 * @return
	 */
	public List<String> getAllServiceMethods() {
		List<String> result = new ArrayList<>();

		List<Class<?>> services = ModelClassesUtils.findModelClassesWithInterface(IServiceType.class);
		for (Class<?> srv : services) {
			for (Method m : srv.getMethods()) {
				result.add(m.getName());
			}
		}
		
		return result;
	}
	

	/**
	 * return all dao method of all the modelled entities, for with a json/rest service should be generated
	 * @return
	 */
	public List<String> getAllDaoMethods() {
		List<String> result = new ArrayList<>();

		List<Class<?>> entities = ModelClassesUtils.findModelClassesWithInterface(IEntityType.class);
		for (Class<?> entity : entities) {
			if (generateRestService(entity)) {
				for (String prefix : CrudOperation.allPrefix()) {
					result.add(prefix + entity.getSimpleName());
				}
			}
		}

		return result;
	}
	
}
