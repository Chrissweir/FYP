function confirmPassword() {
	var password = document.getElementById("signupPassword").value;
	var confpassword = document.getElementById("signupPasswordagain").value;
	if(password == confpassword) {
		document.getElementById("passwordLabel").style.display="block";
		window.alert("Passwords Dont match");
	}
	else{
		document.getElementById("passwordLabel").style.display="none";
	}
}

function confirmEmail() {
	var email = document.getElementById("signupEmail").value;
	var confemail = document.getElementById("signupEmailagain").value;
	if(email != confemail) {
		document.getElementById("emailLabel").style.display="block";
	}
	else{
		document.getElementById("emailLabel").style.display="none";
	}
}



function confirmCollege() {
	var college = document.getElementById("signupCollege").value;
	if(college =="") {
		document.getElementById("collegeLabel").style.display="block";
	}
	else{
		document.getElementById("collegeLabel").style.display="none";
	}
}
