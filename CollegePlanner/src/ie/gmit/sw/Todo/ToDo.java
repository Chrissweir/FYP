package ie.gmit.sw.Todo;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
	
	private List<Task> tasks = new ArrayList<Task>(); 
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public void addTask(Task task)
	{
		tasks.add(task);
	}
}