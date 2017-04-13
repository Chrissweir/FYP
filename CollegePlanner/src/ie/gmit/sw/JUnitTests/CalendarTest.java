package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Calendar.CalendarValues;

public class CalendarTest {
	CalendarValues cv = new CalendarValues();
	
	@Test
	public void test() {
		cv.setColor("Blue");
		cv.setEnd("04/04/2017");
		cv.setEndTime("12:00");
		cv.setId(1);
		cv.setStart("03/04/2017");
		cv.setStartTime("13:00");
		cv.setTitle("Title");
		
		cv.getColor();
		cv.getEnd();
		cv.getEndTime();
		cv.getId();
		cv.getStart();
		cv.getStartTime();
		cv.getTitle();
		
		assertEquals("Blue", cv.getColor());
		assertEquals("03/04/2017", cv.getStart());
	}

}
