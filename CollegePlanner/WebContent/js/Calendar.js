function error() {
	check();
	checkDate();
	errorTime();
	//return false;
	
	if(check() && errorTime() && checkDate() == true){
		//alert("TRUE");
		return true;
		
	}else{
		//alert("FALS");
		return false;
	}
};
	
function check(){
	// File to throw error when wrong format is inputed
	if (document.getElementById("startTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("endTime").value.toUpperCase() != "ALL DAY") {
			// throw error
			document.getElementById("errorLabel").style.display = "block";
			//document.getElementById("errorEditLabel").style.display = "block";
			return false;
		}
		return true;
	}

	if (/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
			.getElementById("startTime").value)) {
		if (!/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
				.getElementById("endTime").value)) {
			// throw error
			document.getElementById("errorLabel").style.display = "block";
			//document.getElementById("errorEditLabel").style.display = "block";
			return false;
		}
		return true;
	}
	return false;

};

function checkDate(){
	var d1 = Date.parse(document.getElementById("startDate").value);
	var d2 = Date.parse(document.getElementById("endDate").value);
	if (d1 > d2) {
	    document.getElementById("errorDate").style.display = "block";
	    return false;
	}
	return true;
};

/*	*/
function errorTime() {
	var start = document.getElementById("startTime").value.toUpperCase();
	var end = document.getElementById("endTime").value.toUpperCase();
	
	if(start == "ALL DAY" && end == "ALL DAY"){
		return true;
	}
	var stime = parseInt(document.getElementById("startTime").value);
	var etime = parseInt(document.getElementById("endTime").value);
	
	//Compare string not num
	if (stime < etime) {
		// error
		//document.getElementById("errorTime").style.display = "block";
	//	document.getElementById("errorEditLabel").style.display = "block";

		return true;
	} else{
		// error
		document.getElementById("errorTime").style.display = "block";
		//document.getElementById("errorEditLabel").style.display = "block";
		return false;
	
	}
};

function errorEdit() {
	checkEdit();
	checkEditDate();
	errorTimeEdit();
	//return false;
	
	if(checkEdit() && errorTimeEdit() && checkEditDate() == true){
		//alert("TRUE");
		return true;
		
	}else{
		//alert("FALS");
		return false;
	}
}
	
function checkEdit(){

	// File to throw error when wrong format is inputed
	if (document.getElementById("editStartTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("editEndTime").value.toUpperCase() != "ALL DAY") {
			// throw error
			document.getElementById("editErrorTime").style.display = "none";
			document.getElementById("errorEditLabel").style.display = "block";
			return false;
		}
		return true;
	}

	if (/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
			.getElementById("editStartTime").value)) {
		if (!/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
				.getElementById("editEndTime").value)) {
			// throw error
			document.getElementById("editErrorTime").style.display = "none";
			document.getElementById("errorEditLabel").style.display = "block";
			return false;
		}
		document.getElementById("editErrorTime").style.display = "none";
		document.getElementById("errorEditLabel").style.display = "none";
		return true;
	}
	return false;

};

function checkEditDate(){
	var d1 = Date.parse(document.getElementById("editStart").value);
	var d2 = Date.parse(document.getElementById("editEnd").value);
	if (d1 > d2) {
	    document.getElementById("errorEditDate").style.display = "block";
	    return false;
	}
	return true;
};

/*	*/
function errorTimeEdit() {
	var start = document.getElementById("editStartTime").value.toUpperCase();
	var end = document.getElementById("editEndTime").value.toUpperCase();
	
	if(start == "ALL DAY" && end == "ALL DAY"){
		return true;
	}
	
	var stime = parseInt(document.getElementById("editStartTime").value);
	var etime = parseInt(document.getElementById("editEndTime").value);
	
	//Compare string not num
	
	if (stime < etime) {
		// error
		document.getElementById("editErrorTime").style.display = "none";
		document.getElementById("errorEditLabel").style.display = "none";

		return true;
	} else{
		// error
		document.getElementById("editErrorTime").style.display = "block";
		document.getElementById("errorEditLabel").style.display = "none";
		return false;
	
	}
};
