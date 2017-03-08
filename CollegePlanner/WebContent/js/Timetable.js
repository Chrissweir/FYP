function validateForm(){
    var title=document.forms["submitForm"]["title"].value;
    var room=document.forms["submitForm"]["room"].value;
    if (title==null || title=="", room==null || room=="")
      {
    	alert("Please Fill All Required Field");
    	return false;
      }
}