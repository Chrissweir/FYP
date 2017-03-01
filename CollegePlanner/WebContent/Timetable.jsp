<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<link rel="stylesheet" href="css/Timetable.css">
	<title>Student Timetable</title>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>

<BODY>
<script>
var button = document.getElementById('button'),
	inputField = document.getElementById('moduleTitle');
	button.addEventListener('click', function(e) {
	inputField.value = this.id;
});
</script>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand"> <span class="glyphicon glyphicon-education" aria-hidden="true"></span></a> 
				<a class="navbar-brand" href="About.jsp">College Planner</a>
			</div>
		
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="Calendar.jsp">Calender</a></li>
					<li><a href="Timetable">Timetable</a></li>
					<li><a href="ToDoList">To do</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">My Profile<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="Profile">Account Details <span class="glyphicon glyphicon-cog" aria-hidden="true"></span></a></li>
							<li role="separator" class="divider"></li>
							<li><a href="Logout">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<form id="submitForm" action="TimetableServlet" method="post">
		Module Title: <INPUT type="text" name="title" size="35" maxlength="15"><BR>
		Room Number: <INPUT type="text" name="room" size="35" maxlength="5"><BR>
		Module Time: 
		Sun<INPUT type="checkbox" name="day" value="sun">
		Mon<INPUT type="checkbox" name="day" value="mon"> 
		Tue<INPUT type="checkbox" name="day" value="tue"> 
		Wed<INPUT type="checkbox" name="day" value="wed"> 
		Thu<INPUT type="checkbox" name="day" value="thu"> 
		Fri<INPUT type="checkbox" name="day" value="fri"> 
		Sat<INPUT type="checkbox" name="day" value="sat"> 
		<SELECT name="starttime">
			<OPTION value="8">8:00am</OPTION>
			<OPTION value="9">9:00am</OPTION>
			<OPTION value="10">10:00am</OPTION>
			<OPTION value="11">11:00am</OPTION>
			<OPTION value="12">12:00pm</OPTION>
			<OPTION value="13">1:00pm</OPTION>
			<OPTION value="14">2:00pm</OPTION>
			<OPTION value="15">3:00pm</OPTION>
			<OPTION value="16">4:00pm</OPTION>
			<OPTION value="17">5:00pm</OPTION>
			<OPTION value="18">6:00pm</OPTION>
			<OPTION value="19">7:00pm</OPTION>
			<OPTION value="20">8:00pm</OPTION>
			<OPTION value="21">9:00pm</OPTION>
		</SELECT> 
		to 
		<SELECT name="endtime">
			<OPTION value="9">9:00am</OPTION>
			<OPTION value="10">10:00am</OPTION>
			<OPTION value="11">11:00am</OPTION>
			<OPTION value="12">12:00pm</OPTION>
			<OPTION value="13">1:00pm</OPTION>
			<OPTION value="14">2:00pm</OPTION>
			<OPTION value="15">3:00pm</OPTION>
			<OPTION value="16">4:00pm</OPTION>
			<OPTION value="17">5:00pm</OPTION>
			<OPTION value="18">6:00pm</OPTION>
			<OPTION value="19">7:00pm</OPTION>
			<OPTION value="20">8:00pm</OPTION>
			<OPTION value="21">9:00pm</OPTION>
			<OPTION value="22">10:00pm</OPTION>
		</SELECT> <BR> <BR> 
		<INPUT type="submit" name="Submit" value="Add Course">
		<INPUT type="submit" name="Remove" value="Remove Course"><br>
	</form>
	
	<TABLE border="1" cellspacing="0">
		<TBODY>
			<TR>
				<TH align="center" valign="middle" width="80"></TH>
				<TH align="center" valign="middle" width="100">Sunday</TH>
				<TH align="center" valign="middle">Monday</TH>
				<TH align="center" valign="middle">Tuesday</TH>
				<TH align="center" valign="middle">Wednesday</TH>
				<TH align="center" valign="middle">Thursday</TH>
				<TH align="center" valign="middle">Friday</TH>
				<TH align="center" valign="middle">Saturday</TH>
			</TR>
			<c:forEach begin="8" end="21" step="1" var="time">
				<TR>
					<TD align="center" valign="middle" width="80"><c:choose>
							<c:when test="${time == 12}">
								<c:out value="${time}" />:00pm
							</c:when>
							<c:when test="${time > 12}">
								<c:out value="${time - 12}" />:00pm
							</c:when>
							<c:otherwise>
								<c:out value="${time}" />:00am
							</c:otherwise>
						</c:choose></TD>
					<c:forEach begin="0" end="6" step="1" var="day">
						<TD align="center" valign="middle" width="100">
							<c:forEach items="${timetable.classes}" var="modules">
								<c:if test="${modules.timeStart <= time 
									&& modules.timeEnd > time 
									&& modules.day == day}">
									<c:out value="${modules.title}"/>
									<sup><c:out value="${modules.room}"/></sup>
									<!-- Trigger the modal with a button -->
									<button onclick="passValue(this);" id="button" name="button" data-original-title="Edit"
										data-toggle="modal" type="button" class="btn btn-xs btn-default" data-target="#myModal">
										<i class="glyphicon glyphicon-edit"></i>
									</button>
								</c:if>
							</c:forEach>
						</TD>
					</c:forEach>
				</TR>
			</c:forEach>
		</TBODY>
	</TABLE>
	
	<!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
		    <!-- Modal content-->
		    <div class="modal-content">
		    <div class="modal-header">
		    	<h4 class="modal-title">Edit Module</h4>
		    </div>
		    <div class="modal-body">
			    <form id="editModule" name="editModule" action="TimetableServlet" method="post">
				    <div class="form-group">
				    	<label>Module Title:</label>
				    	<input class="form-control" type="text" id="moduleTitle" name="moduleTitle" 
				    		placeholder="Title max 15 characters" maxlength="15">
				    </div>
				    <div class="form-group">
				    	<label>Room Number:</label>
				    	<input class="form-control" type="text" id="roomNumber" name="roomNumber" 
				    		placeholder="Room max 5 characters" maxlength="5">
				    </div>
			    </form>
		    </div>
		    <div class="modal-footer">
		    	<button form="editModule" type="submit" class="btn btn-default">Save</button>
		    </div>
		    </div>
	    </div>
    </div>
	
</BODY>
</html>