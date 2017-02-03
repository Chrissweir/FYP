package ie.gmit.sw.Timetable;

public class Module {
	
	private String title;
	private int day;
	private int timeStart;
	private int timeEnd;
	
	public Module(String title, int day, int timeStart, int timeEnd){
		this.title=title;
		this.day=day;
		this.timeStart=timeStart;
		this.timeEnd=timeEnd;
	}

	public String getTitle() {
		return title;
	}

	public int getDay() {
		return day;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public int getTimeEnd() {
		return timeEnd;
	}	
}
