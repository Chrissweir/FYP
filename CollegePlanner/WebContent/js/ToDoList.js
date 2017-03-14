function move(task)
{
	var data = [];
	data = task.value.split("|");
	
	document.getElementById("taskTitle").value = data[0];
	document.getElementById("taskDescription").value = data[1];
	task.form.submit();
};

function remove(task){
	var data = [];
	data = task.value.split("|");
	
	document.getElementById("deleteTaskTitle").value = data[0];
	document.getElementById("deleteTaskDescription").value = data[1];
	
}

function moveBack(task)
{
	var data = [];
	data = task.value.split("|");
	
	document.getElementById("taskTitle").value = data[0];
	document.getElementById("taskDescription").value = data[1];
	task.form.submit();
};
 

