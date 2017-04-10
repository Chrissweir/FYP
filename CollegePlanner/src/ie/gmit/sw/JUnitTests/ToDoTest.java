package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Todo.Task;
import ie.gmit.sw.Todo.ToDo;

public class ToDoTest {
	ToDo todo = new ToDo();
	@Test
	public void test() {
		Task task = new Task("Title", "Description");
		Task task1 = new Task("Title", "Description");
		Task task2 = new Task("Title", "Description");
		Task task3 = new Task("Title", "Description");
		
		todo.addTask(task);
		todo.addTask(task1);
		todo.addTask(task2);
		todo.addTask(task3);
		
		assertEquals(4, todo.getTasks().size());
		assertEquals("Title", task.getTitle());
		assertEquals("Description", task.getDescription());
	}

}
