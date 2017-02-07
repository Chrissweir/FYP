package ie.gmit.sw.Calendar;

public class CalanderSchedule {
	//this class is for creating a schedule in the calendar
	
	//Object will need to hold a title, day, start time and end time
	private String task;
	private int startTask;
	private int endTask;
	private int day;
	
	//constructor that accepts the four pieces of information
	public CalanderSchedule(String title, int startTask, int endTask, int day) {
		super();
		this.task = title;
		this.startTask = startTask;
		this.endTask = endTask;
		this.day = day;
	}

	public CalanderSchedule() {
		// TODO Auto-generated constructor stub
	}

	//getters to access this information
	/**
	 * @return the title
	 */
	public String getTitle() {
		return task;
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

	// create a method to add new classes to the list
	public void addClass(CalanderSchedule schedule) {
		task.getClass();
	}
	
	
	
	
	
	
	
	
	

}
