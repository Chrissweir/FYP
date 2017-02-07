package ie.gmit.sw.Calendar;

public class CalanderSchedule {
	//this class is for creating a schedule in the calendar
	
	//Object will need to hold a title, day, start time and end time
	private String title;
	private int startTask;
	private int endTask;
	private int day;
	
	//constructor that accepts the four pieces of information
	public CalanderSchedule(String title, int startTask, int endTask, int day) {
		super();
		this.title = title;
		this.startTask = startTask;
		this.endTask = endTask;
		this.day = day;
	}

	//getters to access this information
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the startTask
	 */
	public int getStartTask() {
		return startTask;
	}

	/**
	 * @return the endTask
	 */
	public int getEndTask() {
		return endTask;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	
	
	
	
	
	
	
	
	

}
