package ie.gmit.sw.Modules;

public class Module {
	private String title;
	private String lecturer;
	
	public Module(String title, String lecturer) {
		this.title = title;
		this.lecturer = lecturer;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the lecturer
	 */
	public String getLecturer() {
		return lecturer;
	}
	/**
	 * @param lecturer the lecturer to set
	 */
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
}