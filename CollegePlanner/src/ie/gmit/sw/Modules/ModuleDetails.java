package ie.gmit.sw.Modules;

/**
 * @author Christopher Weir - G00309429, Gareth Lynskey - G00312651, Patrick Griffin - G00314635
 * 
 * Getters and setters for module details.
 *
 */
public class ModuleDetails {
	private String title;
	private String gradeTitle;
	private String date;
	private int value;
	private int result;
	private double grade;
	
	/**
	 * Constructor
	 * 
	 * @param title
	 * @param gradeTitle
	 * @param date
	 * @param value
	 * @param result
	 * @param grade
	 */
	public ModuleDetails(String title, String gradeTitle, String date, int value, int result, double grade) {
		this.title = title;
		this.gradeTitle = gradeTitle;
		this.date = date;
		this.value = value;
		this.result = result;
		this.grade = grade;
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
	/**
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}
}