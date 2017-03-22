function getModuleTitle(title){
	document.getElementById("moduleAssignmentTitle").value = title.value;
};

function removeAssignment(assignment){
	var data= [];
	data = assignment.value.split("|");
	document.getElementById("deleteAssignmentModule").value = data[0];
	document.getElementById("deleteAssignmentTitle").value = data[1];
	document.getElementById("deleteAssignmentDate").value = data[2];
	document.getElementById("deleteAssignmentValue").value = data[3];
};