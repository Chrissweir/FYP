package ie.gmit.sw.Assignments;

import java.util.ArrayList;
import java.util.List;

public class AssignmentData {

	private List<AssignmentDetails> moduleAssignments = new ArrayList<AssignmentDetails>();

	/**
	 * Getter for the list in order to access the lists object information
	 * @return
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

	/**
	 * Method for removing classes
	 * @param module
	 */
	public void removeAssignment(AssignmentDetails assignment){
		moduleAssignments.remove(assignment);
	}

}