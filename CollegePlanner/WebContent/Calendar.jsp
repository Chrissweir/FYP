<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel='stylesheet' href='css/fullcalendar.css' />


<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.2.0/fullcalendar.js"></script>

<title>Calendar</title>

</head>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand"> <span
				class="glyphicon glyphicon-education" aria-hidden="true"></span></a> <a
				class="navbar-brand" href="About.jsp">College Planner</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="Calendar.jsp">Calender</a></li>
				<li><a href="Timetable.jsp">Timetable</a></li>
				<li><a href="ToDoList">To do</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">My Profile<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="Profile">Account Details <span
								class="glyphicon glyphicon-cog" aria-hidden="true"></span></a></li>
						<li role="separator" class="divider"></li>
						<li><a href="Logout">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
	
	<div style="margin: 50px 50px 50px 50px;">
		<div id="calendar"></div>
	</div>
	<div>
	<div class="container">
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

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
          <form id="addEvent" name="addEvent" action="CalendarServlet" method="post">
           <div class="form-group">
           <label>Title:</label>
            <input class="form-control" type="text" name="Title" placeholder="Title max 17 characters"  maxlength="17" required>
            </div>
             <div class="form-group">
            <label>Start Date:</label>
            <input class="form-control" type="text" name="startDate" placeholder="YYYY-MM-DD" pattern="\d{4}-?\d{2}-?\d{2}"required>
            </div>
             <div class="form-group">
            <label>End Date:</label>
            <input class="form-control" type="text" name="endDate" placeholder="YYYY-MM-DD" pattern="\d{4}-?\d{2}-?\d{2}" required>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button form="addEvent" type="submit" class="btn btn-default">Save</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
	
	</div>
	
	<script>
		$(document).ready(function() {
			$('#calendar').fullCalendar({
				theme : false,
				editable : false,
				events : "CalendarServlet",
					 eventClick: function(calEvent, jsEvent, view, date) {

						 $("#editModal").modal();
						 //date = new Date(date.getFullYear());
					        alert('start: ' + moment(calEvent.start).format('YYYY/MM/DD'));
					       // alert(moment(start).format())
					        //alert(calEvent.end);
					        document.getElementById("editTitle").value = calEvent.title;
					        document.getElementById("editStart").value = moment(calEvent.start).format('YYYY/MM/DD');
					        document.getElementById("editEnd").value = moment(calEvent.end).format('YYYY/MM/DD');
					    }
			});

		});
	</script>
<!-- Modal -->
  <div class="modal fade" id="editModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Event</h4>
        </div>
        <div class="modal-body">
          <form id="addEvent" name="addEvent" action="CalendarServlet" method="post">
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
          </form>
        </div>
        <div class="modal-footer">
          <button form="addEvent" type="submit" class="btn btn-default">Save</button>
        </div>
      </div>
      
    </div>
  </div>
  

</body>
</html>