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