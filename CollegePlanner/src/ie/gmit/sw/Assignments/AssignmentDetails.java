package ie.gmit.sw.Assignments;

public class AssignmentDetails {
	
	private String title;
	private String assignmentTitle;
	private String date;
	private int value;
	
	public AssignmentDetails(String title, String assignmentTitle, String date, int value) {
		this.title = title;
		this.assignmentTitle = assignmentTitle;
		this.date = date;
		this.value = value;
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
	 * @return the assignmentTitle
	 */
	public String getAssignmentTitle() {
		return assignmentTitle;
	}
	/**
	 * @param assignmentTitle the assignmentTitle to set
	 */
	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
}