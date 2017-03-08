function validateForm(){
    var title=document.forms["submitForm"]["title"].value;
    var room=document.forms["submitForm"]["room"].value;
    
    if (title==null || title=="", room==null || room=="", day==false)
    {
    	alert("Please Fill All Required Field");
    	return false;
    }
}

function isChecked(){
	var day=document.getElementById("day").checked;
	alert("Please check a boc");
}