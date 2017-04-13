package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Timetable.Timetable;
import ie.gmit.sw.Timetable.TimetableModule;

public class TimetableTest {
	
	Timetable timetable = new Timetable();
	
	@Test
	public void test() {
		TimetableModule timeModule = new TimetableModule("Title", 9, 13, 4, "PF18");
		TimetableModule timeModule1 = new TimetableModule("Title", 11, 13, 7, "34");
		TimetableModule timeModule2 = new TimetableModule("Title", 10, 17, 9, "1000");
		
		timetable.addClass(timeModule);
		timetable.addClass(timeModule1);
		timetable.addClass(timeModule2);
		
		assertEquals(3, timetable.getClasses().size());
		assertEquals(11, timeModule1.getTimeStart());
	}
}