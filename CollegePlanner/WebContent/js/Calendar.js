/* error() calls the error methods that are then
 * called to the modals on the calendar.jsp page.
 * Overall this class is responsible for error handling 
 * in order for it to be fully functional.	
 */
function error() {

	check();
	checkDate();
	errorTime();
	// Valid input from the user
	if (check() && errorTime() && checkDate() == true) {
		return true;

	} else {
		return false;
	}
};

function check() {
	// File to throw error when wrong format is inputed
	if (document.getElementById("startTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("endTime").value.toUpperCase() != "ALL DAY") {
			// throw error
			document.getElementById("errorLabel").style.display = "block";

			return false;
		}
		return true;
	}
	// The time must be entered in HH:mm format
	if (/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
			.getElementById("startTime").value)) {
		if (!/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document
				.getElementById("endTime").value)) {
			// throw error
			document.getElementById("errorLabel").style.display = "block";

			return false;
		}
		return true;
	}
	return false;

};

// Checking that start date is before end date
function checkDate() {
	// parse the date to a value
	var d1 = Date.parse(document.getElementById("startDate").value);
	var d2 = Date.parse(document.getElementById("endDate").value);
	// if start date is greater than end date return false
	if (d1 > d2) {
		document.getElementById("errorDate").style.display = "block";
		return false;
	}
	return true;
};

/*	*/
function errorTime() {
	//using .toUpperCase so "All Day" will be converted to upper case and be accepted 
	var start = document.getElementById("startTime").value.toUpperCase();
	var end = document.getElementById("endTime").value.toUpperCase();

	// making sure start and end are both = All day
	if (start == "ALL DAY" && end == "ALL DAY") {
		return true;
	}
	// Only throw error if startTime is less than endTime on the SAME DAY
	var startDate = Date.parse(document.getElementById("startDate").value);
	var endDate = Date.parse(document.getElementById("endDate").value);

	var stime = parseInt(document.getElementById("startTime").value);
	var etime = parseInt(document.getElementById("endTime").value);

	// Compare string not num
	if (startDate == endDate) {

		if (stime < etime) {
			// error
			return true;
		}

		else {
			// error
			document.getElementById("errorTime").style.display = "block";

			return false;

		}

	}
	return true; // return true if start time is before end time on seperate
					// days
};
//Calling the editing methods for editing modal
function errorEdit() {
	checkEdit();
	checkEditDate();
	errorTimeEdit();
	//if are all correct return true
	if (checkEdit() && errorTimeEdit() && checkEditDate() == true) {

		return true;

	} else {

		return false;
	}
}

function checkEdit() {

	// throw error when editStartTime is all day and end time is not
	if (document.getElementById("editStartTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("editEndTime").value.toUpperCase() != "ALL DAY") {
			// throw error label 
			document.getElementById("editErrorTime").style.display = "none"; //invisible 
			document.getElementById("errorEditLabel").style.display = "block"; //visible
			return false;
		}
		return true;
	}
	
	// throw error when editStartTime is time and end time is not
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
//Function to throw error if start date is after end date as an event cannot start after the end date
function checkEditDate() {
	var d1 = Date.parse(document.getElementById("editStart").value);
	var d2 = Date.parse(document.getElementById("editEnd").value);
	if (d1 > d2) {
		document.getElementById("errorEditDate").style.display = "block";
		return false;
	}
	return true;
};

//
function errorTimeEdit() {
	var start = document.getElementById("editStartTime").value.toUpperCase();
	var end = document.getElementById("editEndTime").value.toUpperCase();

	var startDate = Date.parse(document.getElementById("editStart").value);
	var endDate = Date.parse(document.getElementById("editEnd").value);

	if (start == "ALL DAY" && end == "ALL DAY") {
		return true;
	}

	var stime = parseInt(document.getElementById("editStartTime").value);
	var etime = parseInt(document.getElementById("editEndTime").value);

	// Compare string not num
	if (startDate == endDate) {
		if (stime < etime) {
			// error
			document.getElementById("editErrorTime").style.display = "none";
			document.getElementById("errorEditLabel").style.display = "none";

			return true;
		} else {
			// error
			document.getElementById("editErrorTime").style.display = "block";
			document.getElementById("errorEditLabel").style.display = "none";
			return false;

		}
	}
	return true; // return true if start time is before end time on seperate
					// days
};
