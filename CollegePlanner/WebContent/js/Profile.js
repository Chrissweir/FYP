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
	document.getElementById("fname").contentEditable= true;
	document.getElementById("lname").contentEditable= true;
	document.getElementById("em").contentEditable= true;
	document.getElementById("coll").contentEditable= true;
};

function save(){
	document.getElementById("saveBtn").style.visibility = "hidden";
	document.getElementById("fname").contentEditable= false;
	document.getElementById("lname").contentEditable= false;
	document.getElementById("em").contentEditable=false;
	document.getElementById("coll").contentEditable=false;
	document.getElementById("imageEdit").style.visibility = "hidden";
};