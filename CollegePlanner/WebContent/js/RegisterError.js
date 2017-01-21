function validateForm() {
	var email = document.getElementById("signupEmail").value;
	var password = document.getElementById("signupPassword").value;
	var confpassword = document.getElementById("signupPasswordagain").value;
	var emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	alert("Thatvg kg")
	if(email.match(emailreg)){
		if(password == confpassword){
			document.getElementById("passwordLabel").style.display="none";
			return true;
		}
		else
		{
			document.getElementById("passwordLabel").style.display="block";
			return false;
		}
	}else
	{
		document.getElementById("emailLabel").style.display="block";
		return false;
	}
	return false;
}