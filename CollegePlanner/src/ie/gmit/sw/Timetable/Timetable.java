package ie.gmit.sw.Timetable;

import java.util.ArrayList;
import java.util.List;

public class Timetable {

	//private list that takes in modules
	private List<Module> classes = new ArrayList<Module>();

	/**
	 * Getter for module list in order to access the lists object information
	 * @return
	 */
	public List<Module> getClasses() {
		return classes;
	}
	
	/**
	 * Method for adding new classes
	 * @param module
	 */
	public void addClass(Module module)
	{
		classes.add(module);
	}
	
}
