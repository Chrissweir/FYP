function error() {

	// File to throw error when wrong format is inputed
	if (document.getElementById("startTime").value.toUpperCase() == "ALL DAY") {
		if (document.getElementById("endTime").value.toUpperCase() != "ALL DAY") {
			// throw error
			document.getElementById("errorLabel").style.display = "block";
			return false;
		}
		return true;
	}

	if (/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document.getElementById("startTime").value)) {
		if (!/^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/.test(document.getElementById("endTime").value)) {
			// throw error
			document.getElementById("errorLabel").style.display = "block";
			return false;
		}
		return true;
	}
	return false;
}