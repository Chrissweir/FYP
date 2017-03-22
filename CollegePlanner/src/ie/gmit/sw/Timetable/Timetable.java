package ie.gmit.sw.Timetable;

import java.util.ArrayList;
import java.util.List;

//The timetable class will act as a container for all of the classes
public class Timetable {

	//private list that takes in modules
	private List<TimetableModule> classes = new ArrayList<TimetableModule>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return
	 */
	public List<TimetableModule> getClasses() {
		return classes;
	}
	
	/**
	 * Method for adding new classes to the list
	 * @param module
	 */
	public void addClass(TimetableModule module)
	{
		classes.add(module);
	}
	
	/**
	 * Method for removing classes
	 * @param module
	 */
	public void removeClass(TimetableModule module){
		classes.remove(module);
	}
	
}
