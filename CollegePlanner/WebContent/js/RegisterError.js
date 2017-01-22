function validateForm() {
	var email = document.getElementById("signupEmail").value;
	var password = document.getElementById("signupPassword").value;
	var confpassword = document.getElementById("signupPasswordagain").value;
	if(password != confpassword)
	{
		document.getElementById("passLabel").style.display="block";
		document.getElementById("confpassLabel").style.display="block";
		return false;
	}
	else
	{
		document.getElementById("passLabel").style.display="none";
		document.getElementById("confpassLabel").style.display="block";
		return true;
	}
}