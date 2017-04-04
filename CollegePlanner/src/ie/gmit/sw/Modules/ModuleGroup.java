package ie.gmit.sw.Modules;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christopher Weir - G00309429, Gareth Lynskey - G00312651, Patrick Griffin - G00314635
 *	
 * Responsible for the list of Modules.
 */
public class ModuleGroup {

	private List<Module> moduleList = new ArrayList<Module>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return moduleList
	 */
	public List<Module> getModuleList() {
		return moduleList;
	}

	/**
	 * Method for adding new classes to the list
	 * @param module
	 */
	public void addModule(Module module)
	{
		moduleList.add(module);
	}
}