package ie.gmit.sw.Todo;

public class ToDoClass {
	
	private String title;
	private String description;
	
  
    public ToDoClass(String title, String description) 
	{
        this.title = title;
        this.description = description;

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
	
	
	
	/*@Override
	public String toString() {
	  return "Task: " + "Title: " + title +  ", Description: " + description +  ", complete: " + complete;
	}*/

}