package ie.gmit.sw.Timetable;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
	
	private List<Module> classes = new ArrayList<Module>();

	public List getClasses() {
		return classes;
	}
	
	public void addClass(Module module)
	{
		classes.add(module);
	}
	
}
