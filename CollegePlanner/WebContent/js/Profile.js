function path(){
	var x = document.getElementById("imgFile").value;
	document.getElementById("image").src= URL.createObjectURL(event.target.files[0]);
	var files = document.getElementById('imgFile').files;
	if (files.length > 0) {
		getBase64(files[0]);
	}
};

function getBase64(file) {
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function () {
		document.getElementById("imgPath").value = reader.result;
	};
	reader.onerror = function (error) {
		console.log('Error: ', error);
	};
};

function edit(){
	document.getElementById("saveBtn").style.visibility = "visible";
	document.getElementById("imageEdit").style.visibility = "visible";
	document.getElementById("fname").readOnly = false;
	document.getElementById("lname").readOnly = false;
	document.getElementById("em").readOnly = false;
	document.getElementById("coll").readOnly = false;
	document.getElementById("cour").readOnly = false;
	document.getElementById("biog").readOnly = false;
};

function save(){
	document.getElementById("saveBtn").style.visibility = "hidden";
	document.getElementById("fname").readOnly = true;
	document.getElementById("lname").readOnly = true;
	document.getElementById("em").readOnly = true;
	document.getElementById("coll").readOnly = true;
	document.getElementById("cour").readOnly = true;
	document.getElementById("biog").readOnly = true;
	document.getElementById("imageEdit").style.visibility = "hidden";
};