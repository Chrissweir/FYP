function validateForm(){
	var title=document.forms["submitForm"]["title"].value;
	var room=document.forms["submitForm"]["room"].value;

	var day0 = document.getElementById("day0");
	var day1 = document.getElementById("day1");
	var day2 = document.getElementById("day2");
	var day3 = document.getElementById("day3");
	var day4 = document.getElementById("day4");
	var day5 = document.getElementById("day5");
	var day6 = document.getElementById("day6");


	if(day0.checked == false && day1.checked == false && day2.checked == false && day3.checked == false && 
			day4.checked == false && day5.checked == false && day6.checked == false){
		document.getElementById("dayLabel").style.display = "block";
		return false;
	}

	if (title==null || title=="", room==null || room=="", day==false)
	{
		alert("Please Fill All Required Field");
		return false;
	}
};

function getModule(module){
	var data = [];
	data = module.value.split("|");
	document.getElementById("OModuleTitle").value = data[0];
	document.getElementById("ORoomNumber").value = data[1];
	document.getElementById("OStartTime").value = data[2];
	document.getElementById("OEndTime").value = data[3];
	document.getElementById("ODay").value = data[4];
	
	document.getElementById("editModuleTitle").value = data[0];
	document.getElementById("editRoomNumber").value = data[1];
	document.getElementById("editStartTime").value = data[2];
	document.getElementById("editEndTime").value = data[3];

    if(data[4] == 0){
    	document.getElementById("sun").checked = true;
    }
    if(data[4] == 1){
    	document.getElementById("mon").checked = true;
    }
    if(data[4] == 2){
    	document.getElementById("tue").checked = true;
    }
    if(data[4] == 3){
    	document.getElementById("wed").checked = true;
    }
    if(data[4] == 4){
    	document.getElementById("thu").checked = true;
    }
    if(data[4] == 5){
    	document.getElementById("fri").checked = true;
    }
    if(data[4] == 6){
    	document.getElementById("sat").checked = true;
    }
};