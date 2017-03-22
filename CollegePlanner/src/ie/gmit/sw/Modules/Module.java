package ie.gmit.sw.Modules;

public class Module {
	private String title;
	private String lecturer;
	private double average;
	private int id;
	
	public Module(String title, String lecturer, double average, int i) {
		this.title = title;
		this.lecturer = lecturer;
		this.average = average;
		this.id=i;
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
	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}
	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}