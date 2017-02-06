package ie.gmit.sw.Timetable;

import java.util.List;
import java.util.ArrayList;

public class Timetable {
	
	private List classes = new ArrayList();

	public List getClasses() {
		return classes;
	}
	
	public void addClass(Module module)
	{
		classes.add(module);
	}
	
}
