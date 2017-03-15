function getModuleTitle(title){
	document.getElementById("gradeModuleTitle").value = title.value;
};

function deleteModule(module){
	var data= [];
	data = module.value.split("|");
	document.getElementById("deleteModuleTitle").value = data[0];
	document.getElementById("deleteModuleLecturer").value = data[1];
};