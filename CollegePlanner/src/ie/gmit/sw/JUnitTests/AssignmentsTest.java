package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Assignments.AssignmentData;
import ie.gmit.sw.Assignments.AssignmentDetails;

public class AssignmentsTest {
	AssignmentData aData = new AssignmentData();
	
	@Test
	public void test() {
		AssignmentDetails aDetails = new AssignmentDetails("Module", "Assignment", "17/04/2017", 50);
		AssignmentDetails aDetails1 = new AssignmentDetails("Module", "Assignment", "17/04/2017", 50);
		AssignmentDetails aDetails2 = new AssignmentDetails("Module", "Assignment", "17/04/2017", 50);
		AssignmentDetails aDetails3 = new AssignmentDetails("Module", "Assignment", "17/04/2017", 50);
		AssignmentDetails aDetails4 = new AssignmentDetails("Module", "Assignment", "17/04/2017", 50);
		
		aData.addAssignment(aDetails);
		aData.addAssignment(aDetails1);
		aData.addAssignment(aDetails2);
		aData.addAssignment(aDetails3);
		aData.addAssignment(aDetails4);
		
		
		//Is there 5 entries in the lists
		assertEquals(5, aData.getModuleAssignments().size());
	}

}
