function error() {
	check();
	errorTime();
	//return false;
	
	if(check() && errorTime() == true){
		//alert("TRUE");
		return true;
		
	}else{
		//alert("FALS");
		return false;
	}
}
	
	function check(){

	// File to throw error when wrong format is inputed
	if (document.getElementById("startTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("endTime").value.toUpperCase() != "ALL DAY") {
			// throw error
			document.getElementById("errorLabel").style.display = "block";
			document.getElementById("errorEditLabel").style.display = "block";
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
			document.getElementById("errorEditLabel").style.display = "block";
			return false;
		}
		return true;
	}
	return false;

};

/*	*/
function errorTime() {
	var stime = document.getElementById("startTime").value;
	var etime = document.getElementById("endTime").value;
	
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
