package ie.gmit.sw.Todo;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
	
	//Creates a new array list of tasks and calls Task class
	private List<Task> tasks = new ArrayList<Task>(); 
	
	/**
	 * 
	 * Gets list of tasks from Task class and returns list of tasks
	 * 
	 * @return tasks
	 */
	public List<Task> getTasks(){
		return tasks;
	}
	
	
	/**
	 * 
	 * Adds tasks to list of tasks
	 * 
	 * @param task
	 */
	public void addTask(Task task)
	{
		tasks.add(task);
	}
}