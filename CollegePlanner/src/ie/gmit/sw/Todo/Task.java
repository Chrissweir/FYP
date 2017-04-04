package ie.gmit.sw.Todo;

/**
 * @author Paul Dolan - G00297086
 * Getters and setters 
 *
 */

public class Task {
	
	private String title;
	private String description;

  
    /**
     * Constructor
     * 
     * @param title
     * @param description
     */
    public Task(String title, String description) 
	{
        this.title = title;
        this.description = description;

    }	

	/**
	 * @return title
	 */
	public String getTitle() {
	  return title;
	}
	
	/**
	 * @param title
	 */
	public void setTitle(String title) {
	  this.title = title;
	}
	
	
	/**
	 * @return description
	 */
	public String getDescription() {
	  return description;
	}
	
	
	/**
	 * @param description
	 */
	public void setDescription(String description) {
	  this.description = description;
	}

}