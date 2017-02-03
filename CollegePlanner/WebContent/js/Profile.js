function path(){
	var x = document.getElementById("imgFile").value;
	document.getElementById("image").src= URL.createObjectURL(event.target.files[0]);
	alert("1");
	 var files = document.getElementById('imgFile').files;
	 alert("2");
	  if (files.length > 0) {
	    getBase64(files[0]);
	    alert("3");
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
	document.getElementById("firstname").contentEditable= true;
	document.getElementById("lastname").contentEditable= true;
	document.getElementById("email").contentEditable= true;
	document.getElementById("college").contentEditable= true;
};

function save(){
	document.getElementById("saveBtn").style.visibility = "hidden";
	document.getElementById("firstname").contentEditable= false;
	document.getElementById("lastname").contentEditable= false;
	document.getElementById("email").contentEditable=false;
	document.getElementById("college").contentEditable=false;
	document.getElementById("imageEdit").style.visibility = "hidden";
};