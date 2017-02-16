package ie.gmit.sw.Timetable;


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
