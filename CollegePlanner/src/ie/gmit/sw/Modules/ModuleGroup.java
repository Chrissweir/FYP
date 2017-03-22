package ie.gmit.sw.Modules;

import java.util.ArrayList;
import java.util.List;

public class ModuleGroup {

	private List<Module> moduleList = new ArrayList<Module>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return
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

	/**
	 * Method for removing classes
	 * @param module
	 */
	public void removeModule(Module module){
		moduleList.remove(module);
	}
}