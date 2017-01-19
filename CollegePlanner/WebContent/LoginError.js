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
