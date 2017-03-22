<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import = "com.google.gson.Gson"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


  <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.2.0/fullcalendar.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js"></script>
<link rel='stylesheet' href='https://fullcalendar.io/js/fullcalendar-3.2.0/fullcalendar.css' />
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.2.0/fullcalendar.js"></script>

<script type="text/javascript" src="js/Calendar.js"></script>

<!--   -->

<title>Calendar</title>

</head>
<body style="padding-top: 70px">
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">
					<span class="glyphicon glyphicon-education" aria-hidden="true"></span>
				</a>
				<a class="navbar-brand" href="About.jsp">College Planner</a>
			</div>
	
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="Calendar.jsp">Calendar</a></li>
					<li><a href="Timetable">Timetable</a></li>
					<li><a href="ToDoList">To do</a></li>
					<li><a href="Modules">Modules</a></li>
					<li><a href="Assignments">Assignments</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${username}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li>
								<a href="Profile">Account Details
									<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
								</a>
							</li>
							<li role="separator" class="divider"></li>
							<li><a href="Logout">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div style="margin: 50px 100px 50px 100px;">
		<div id="calendar"></div>
	</div>
	<div>
	<div class="container">


  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Event</h4>
        </div>
        <div class="modal-body">
        
          <form id="addEvent" name="addEvent" onsubmit="return error();" action="CalendarServlet" method="post">
           <div class="form-group">
           <label>Title:</label>
            <input class="form-control" type="text" name="Title" placeholder="Title max 17 characters"  maxlength="17" required>
            </div>
             <div class="form-group">
            <label>Start Date:</label>
            <input class="form-control" type="date" name="startDate" required>
            </div>
             <div class="form-group">
            <label>End Date:</label>
            <input class="form-control" type="date" name="endDate"  required>
            
            </div>
            
             <div class="form-group">
            <label>Start Time:</label>
            <input class="form-control" type="text" name="startTime" id="startTime" placeholder="HH:mm or All Day" >
            </div>
             <div class="form-group">
            <label>End Time:</label>
            <input class="form-control" type="text" name="endTime" id="endTime" placeholder="HH:mm or All Day"  >
            <label id="errorLabel" style="color: red; display: none">Entry must be HH:mm or All Day</label>
            <label id="errorTime" style="color: red; display: none">start time must start before end time</label>

            </div>
            
            <div class="form-group">
      <label for="color">Select Color (select one):</label>
      <select class="form-control" name="color" id="color">
        <option value="#00ff0c">Green</option>
        <option value="#ff0000">Red</option>
        <option value="#000000">Black</option>
        <option value="#0021ff">Blue</option>
        <option value="#ff00ee">Purple</option>
        <option value="#ff8300">Orange</option>
      </select>
     
    </div>
     
            
          </form>
        </div>
        <div class="modal-footer">
          <button form="addEvent" type="submit" name="btn" value="save" class="btn btn-default">Save</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
	
	</div>
	
	<script>
		$(document).ready(function() {
			$('#calendar').fullCalendar({
				
				customButtons: {
			        myCustomButton: {
			            text: 'Create Event',
			            click: function() {
			            	$("#myModal").modal();
			            }
			        }
			    },
				
				header: {
					left: 'prev,next today myCustomButton',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				theme : false,
				editable : false,
				slotLabelFormat:"HH:mm",
				events : "CalendarServlet",
				
					 eventClick: function(calEvent, event, view, date) {
						 
						 if(calEvent.color =="#00ffff"){
							 $("#assignmentModal").modal();
								 document.getElementById("assignmentTitle").value = calEvent.title;
								 document.getElementById("assignmentDate").value = moment(calEvent.start).format('YYYY-MM-DD');
						 }
						 else{

							 $("#editModal").modal();
						        document.getElementById("editTitle").value = calEvent.title;
						        document.getElementById("Otitle").value = calEvent.title;
						        
						        document.getElementById("editStart").value = moment(calEvent.start).format('YYYY-MM-DD');
						        document.getElementById("Ostart").value = moment(calEvent.start).format('YYYY-MM-DD');
						        
						        document.getElementById("editColor").value = calEvent.color;//.format('YYYY-MM-DD');
						       
						        document.getElementById("Ocolor").value = calEvent.color; //$(this).css('background-color');//.format('YYYY-MM-DD');
						        
						        var test = moment(calEvent.end).format('YYYY-MM-DD');
						       // alert(test);
						        if(test.toString() == "Invalid date"){
						        	document.getElementById("editEnd").value = moment(calEvent.start).format('YYYY-MM-DD');
						        	document.getElementById("Oend").value = moment(calEvent.start).format('YYYY-MM-DD');
						        }else{
							        document.getElementById("editEnd").value = moment(calEvent.end).format('YYYY-MM-DD');
							        document.getElementById("Oend").value = moment(calEvent.end).format('YYYY-MM-DD');
						        }
						        var test1 = moment(calEvent.end).format('HH:mm');
						        var test2 = moment(calEvent.start).format('HH:mm');
						        if(test1.toString() == "Invalid date" || test1.toString() == "00:00", test2.toString() == "00:00"){
						        	document.getElementById("editStartTime").value = "All Day";
							        document.getElementById("editEndTime").value = "All Day";
							        document.getElementById("OstartTime").value = "All Day";
							        document.getElementById("OendTime").value = "All Day";
						        }else{
						        	document.getElementById("editStartTime").value = moment(calEvent.start).format('HH:mm');
							        document.getElementById("editEndTime").value = moment(calEvent.end).format('HH:mm');
							        document.getElementById("OstartTime").value = moment(calEvent.start).format('HH:mm');
							        document.getElementById("OendTime").value = moment(calEvent.end).format('HH:mm');
						        }
						    }
					 }
					
				});
				

			});
			
			
		</script>
<!-- Assignment Modal -->
<div class="modal fade" id="assignmentModal" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content ">
			<div class="modal-header">
				<h4 class="modal-title">Assignment Details</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Title:</label> <input class="form-control" type="text" id="assignmentTitle" name="assignmentTitle" readonly>
				</div>
				<div class="form-group">
					<label>Date:</label> <input class="form-control" type="text" id="assignmentDate" pattern="\d{4}-?\d{2}-?\d{2}" name="assignmentDate" readonly>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
  <div class="modal fade" id="editModal" role="dialog">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Event</h4>
        </div>
        <div class="modal-body">
        
          <form id="editEvent" name="editEvent" onsubmit="return errorEdit();" action="CalendarServlet" method="post">
           <div class="form-group">
           <label>Edit Title:</label>
            <input class="form-control" type="text" id="editTitle" name="editTitle" placeholder="Title max 17 characters"  maxlength="17" required>
            </div>
             <div class="form-group">
            <label>Start Date:</label>
            <input class="form-control" type="text" id="editStart" name="editStartDate" placeholder="YYYY-MM-DD" pattern="\d{4}-?\d{2}-?\d{2}"required>
            </div>
             <div class="form-group">
            <label>End Date:</label>
            <input class="form-control" type="text" id="editEnd" name="editEndDate" placeholder="YYYY-MM-DD" pattern="\d{4}-?\d{2}-?\d{2}" required>
            </div>
            
             <div class="form-group">
            <label> Edit Start Time:</label>
            <input class="form-control" type="text" name="editStartTime" id="editStartTime" placeholder="HH:mm" >
            </div>
             <div class="form-group">
            <label>Edit End Time:</label>
            <input class="form-control" type="text" name="editEndTime" id="editEndTime" placeholder="HH:mm">
            <label id="errorEditLabel" style="color: red; display: none">Entry must be HH:mm or All Day</label>
            <label id="editErrorTime" style="color: red; display: none">start time must start before end time</label>
            </div>
            
             
            <div class="form-group">
      <label for="editColor">Select Color (select one):</label>
      <select class="form-control" name="editColor" id="editColor">
        <option value="#00ff0c">Green</option>
        <option value="#ff0000">Red</option>
        <option value="#000000">Black</option>
        <option value="#0021ff">Blue</option>
        <option value="#ff00ee">Purple</option>
        <option value="#ff8300">Orange</option>
      </select>
     
    </div>
            
          </form>
        </div>
        <div class="modal-footer">
          <button form="editEvent" type="submit" name="btn" value="edit" class="btn btn-default">Save</button>
          <button form="editEvent" type="submit" name="btn" value="delete" class="btn btn-default">Delete</button>
        </div>
      </div>
      
    </div>
  </div>
<input form="editEvent" type="text" id="Otitle" name="Otitle" style="visibility: hidden"></input>
<input form="editEvent" type="text" id="Ostart" name="Ostart" style="visibility: hidden"></input>
<input form="editEvent" type="text" id="Oend" name="Oend" style="visibility: hidden" ></input>
<input form="editEvent" type="text" id="OstartTime" name="OstartTime" style="visibility: hidden"></input>
<input form="editEvent" type="text" id="OendTime" name="OendTime" style="visibility: hidden"></input>
<input form="editEvent" type="text" id="Ocolor" name="Ocolor" style="visibility: hidden" ></input>
</body>
</html>