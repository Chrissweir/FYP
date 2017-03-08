package ie.gmit.sw.Todo;

/**
 * @author Paul Dolan - G00297086
 * Getters and setters 
 *
 */

public class Task {
	
	private int id;
	private String title;
	private String description;

  
    public Task(String title, String description, int id) 
	{
    	this.id = id;
        this.title = title;
        this.description = description;

    }	
    
	public int getId() {
	  return id;
	}
	
	public void setId(int id) {
	  this.id = id;
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

}