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
<script
	src="http://arshaw.com/js/fullcalendar-1.5.4/fullcalendar/fullcalendar.min.js"></script>


<link rel='stylesheet' href='css/fullcalendar.css' />
<!--  <script src='js/jquery.min.js'></script> -->
<script src='js/moment.min.js'></script>
<script src='js/fullcalendar.js'></script>
<title>Calendar</title>

</head>
<body>

	<body>
	<div style="margin: 100px 150px 100px 80px;">
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
            <input class="form-control" type="text" name="Title" placeholder="Title" required>
            </div>
             <div class="form-group">
            <label>Start Date:</label>
            <input class="form-control" type="text" name="startDate" placeholder="YYYY-MM-DD">
            </div>
             <div class="form-group">
            <label>End Date:</label>
            <input class="form-control" type="text" name="endDate" placeholder="YYYY-MM-DD">
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
				events : "CalendarServlet"
			});

		});
	</script>

</body>

</body>
</html>