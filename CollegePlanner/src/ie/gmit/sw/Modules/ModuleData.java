package ie.gmit.sw.Modules;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christopher Weir - G00309429, Gareth Lynskey - G00312651, Patrick Griffin - G00314635
 *
 *	Responsible for the list of Module Grades.
 */
public class ModuleData {

	private List<ModuleDetails> moduleGrades = new ArrayList<ModuleDetails>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return moduleGrades
	 */
	public List<ModuleDetails> getModuleGrades() {
		return moduleGrades;
	}

	/**
	 * Method for adding new modules to the list
	 * @param module
	 */
	public void addModule(ModuleDetails module)
	{
		moduleGrades.add(module);
	}
}