function validateForm() {
	var password = document.getElementById("password").value;
	var confpassword = document.getElementById("confpassword").value;
	if(password == confpassword)
	{
		document.getElementById("passLabel").style.display="none";
		return true;
	}
	else
	{
		document.getElementById("passLabel").style.display="block";
		return false;
	}
}