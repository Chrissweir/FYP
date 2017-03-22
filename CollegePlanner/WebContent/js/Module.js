function getModuleTitle(title){
	document.getElementById("gradeModuleTitle").value = title.value;
};

function deleteModule(module){
	var data= [];
	data = module.value.split("|");
	document.getElementById("deleteModuleTitle").value = data[0];
	document.getElementById("deleteModuleLecturer").value = data[1];
};

function removeGrade(grade){
	var data= [];
	data = grade.value.split("|");
	document.getElementById("deleteGradeModule").value = data[0];
	document.getElementById("deleteGradeTitle").value = data[1];
	document.getElementById("deleteGradeDate").value = data[2];
	document.getElementById("deleteGradeValue").value = data[3];
	document.getElementById("deleteGradeResult").value = data[4];
	//grade.form.submit();
};