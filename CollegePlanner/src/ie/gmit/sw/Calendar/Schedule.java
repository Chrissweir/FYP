package ie.gmit.sw.Calendar;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List classes = new ArrayList();

	/**
	 * @return the classes
	 */
	public List getClasses() {
		return classes;
	}

	// create a method to add new classes to the list
	public void addClass(CalanderSchedule schedule) {
		classes.add(schedule);
	}

}
