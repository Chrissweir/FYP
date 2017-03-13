function validateForm() {
	var password = document.getElementById("signupPassword").value;
	var confpassword = document.getElementById("signupPasswordagain").value;
	if(password == confpassword)
	{
		document.getElementById("passLabel").style.display="none";
		document.getElementById("confpassLabel").style.display="none";
		return true;
	}
	else
	{
		document.getElementById("passLabel").style.display="block";
		document.getElementById("confpassLabel").style.display="block";
		return false;
	}
}
