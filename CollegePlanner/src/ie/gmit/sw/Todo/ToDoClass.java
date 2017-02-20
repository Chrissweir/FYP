package ie.gmit.sw.Todo;

public class ToDoClass {
	
	private String title;
	private String description;
	private boolean complete = false;
	
  
    public ToDoClass(String title, String description, boolean complete) 
	{
        this.title = title;
        this.description = description;
        this.complete = complete;
    }	

	public String getTitle() {
	  return title;
	}
	
	public void setTitle(String title) {
	  this.title = title;
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
	
	
	/*@Override
	public String toString() {
	  return "Task: " + "Title: " + title +  ", Description: " + description +  ", complete: " + complete;
	}*/

}