package ie.gmit.sw.Assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Dolan - G00297086
 *
 */
public class AssignmentData {

	//List to hold the module assignments
	private List<AssignmentDetails> moduleAssignments = new ArrayList<AssignmentDetails>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return moduleAssignments
	 */
	public List<AssignmentDetails> getModuleAssignments() {
		return moduleAssignments;
	}

	/**
	 * Method for adding new classes to the list
	 * @param module
	 */
	public void addAssignment(AssignmentDetails assignment)
	{
		moduleAssignments.add(assignment);
	}
}