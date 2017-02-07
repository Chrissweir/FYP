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

	public String getTitle() {
		return title;
	}

	public int getStartTask() {
		return startTask;
	}

	public int getEndTask() {
		return endTask;
	}

	public int getDay() {
		return day;
	}
	
	//getters/setters to access this information
	
	
	
	
	

}
