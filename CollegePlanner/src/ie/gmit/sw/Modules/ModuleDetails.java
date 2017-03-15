package ie.gmit.sw.Modules;

public class ModuleDetails {
	private String title;
	private String gradeTitle;
	private String date;
	private int value;
	private int result;
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
	 * @return the gradeTitle
	 */
	public String getGradeTitle() {
		return gradeTitle;
	}
	/**
	 * @param gradeTitle the gradeTitle to set
	 */
	public void setGradeTitle(String gradeTitle) {
		this.gradeTitle = gradeTitle;
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
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	public ModuleDetails(String title, String gradeTitle, String date, int value, int result) {
		this.title = title;
		this.gradeTitle = gradeTitle;
		this.date = date;
		this.value = value;
		this.result = result;
	}	
}