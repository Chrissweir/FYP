package ie.gmit.sw.Modules;

import java.util.ArrayList;
import java.util.List;

public class ModuleData {

	private List<ModuleDetails> moduleGrades = new ArrayList<ModuleDetails>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return
	 */
	public List<ModuleDetails> getModuleGrades() {
		return moduleGrades;
	}

	/**
	 * Method for adding new classes to the list
	 * @param module
	 */
	public void addModule(ModuleDetails module)
	{
		moduleGrades.add(module);
	}

	/**
	 * Method for removing classes
	 * @param module
	 */
	public void removeModule(ModuleDetails module){
		moduleGrades.remove(module);
	}

}