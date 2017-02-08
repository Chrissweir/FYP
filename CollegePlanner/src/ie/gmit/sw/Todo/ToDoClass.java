package ie.gmit.sw.Todo;

public class ToDoClass {

	private String name;
	private String description;
	private boolean complete = false;
	
    public ToDoClass(String name, String description, boolean complete) 
	{
        this.name = name;
        this.description = description;
        this.complete = complete;
    }
	
	
	public String getName() {
	  return name;
	}
	
	public void setName(String name) {
	  this.name = name;
	}
	
	public String getDescription() {
	  return description;
	}
	
	public void setDescription(String description) {
	  this.description = description;
	}
	
	public boolean isComplete() {
	  return complete;
	}
	
	public void setComplete(boolean complete) {
	  this.complete = complete;
	}
	
	/*
	@Override
	public String toString() {
	  return "Task" + "name='" + name +  ", category='" + description +  ", complete=" + complete;
	}*/

}