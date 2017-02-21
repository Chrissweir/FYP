package ie.gmit.sw.Timetable;

public class Module {
	
	//private variables
	private String title;
	private int timeStart;
	private int timeEnd;
	private int day;
	
	
	/**
	 * Module is a constructor that takes in the four parameters and sets the class variables
	 * 
	 * @param title
	 * @param timeStart
	 * @param timeEnd
	 * @param day
	 * 
	 */
	public Module(String title, int timeStart, int timeEnd, int day){
		this.title=title;
		this.timeStart=timeStart;
		this.timeEnd=timeEnd;
		this.day=day;
	}
	
	/**
	 * Getter for title in order to access the objects information
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Getter for timeStart in order to access the objects information
	 * @return
	 */
	public int getTimeStart() {
		return timeStart;
	}

	/**
	 * Getter for timeEnd in order to access the objects information
	 * @return
	 */
	public int getTimeEnd() {
		return timeEnd;
	}	

	/**
	 * Getter for day in order to access the objects information
	 * @return
	 */
	public int getDay() {
		return day;
	}

	/**
	 * This returns a custom toString of the title, timeStart, timeEnd and day
	 * @return
	 */
	public String toString() {
		return title+" "+timeStart+" "+timeEnd+" "+day;
	}
}
