
package ie.gmit.sw.Calendar;

/**
 * @author Patrick Griffin - G00314635
 * This class handles the getters and setters for the users events data.
 * The setters are used in the CalendarEvents to set the user data.
 * The getters are using in the MongoConnection to retrieve data.
 *
 */

public class CalendarValues {
	// Values for events
	public int id;
	public String title;
	public String start;
	public String end;
	public String color;

	public String startTime;
	public String endTime;

	/**
	 * @return get the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	
	/**
	 * @param set startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return get the endTime
	 */
	public String getEndTime() {
		return endTime;
	}


	/**
	 * @param set the endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return get the id
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * @param set id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return get the title
	 */
	public String getTitle() {
		return title;
	}

	
	/**
	 * @param set the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return get the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param set the start
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return get the end
	 */
	public String getEnd() {
		return end;
	}

	
	/**
	 * @param set the end
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	
	/**
	 * @return get color
	 */
	public String getColor() {
		return color;
	}

	
	/**
	 * @param set color
	 */
	public void setColor(String color) {
		this.color = color;
	}

}
