package ie.gmit.sw.Todo;

public class ToDoClass {
	
	private int id;
	private String name;
	private String description;
	private boolean complete = false;
	
	
	public void setId(int id) {
	  this.id = id;
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

}