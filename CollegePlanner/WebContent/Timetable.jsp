<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/Timetable.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/Timetable.js"></script>

<title>Student Timetable</title>
</head>

<BODY style="padding-top: 70px">
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
				<li><a href="Calendar.jsp">Calender</a></li>
				<li><a href="Timetable">Timetable</a></li>
				<li><a href="ToDoList">To do</a></li>
				<li><a href="Grades">Grades Tracker</a></li>
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
<aside>
	<div class="timetableForm-Box timetableForm">
		<form id="submitForm" name="submitForm" action="Timetable" method="post" onsubmit="return validateForm();">
			<div class="form-group">
				<button id="newModule" name="newModule" data-original-title="Edit"
						data-toggle="modal" type="button" class="btn btn-lg btn-info"
						data-target="#moduleModal">Create Module
				</button>
			</div>
			<div class="form-group">
				<label style="color: #FFF;">Module:</label>
				<select name="selectedModule">
					<c:forEach var="module" items="${moduleList}">
						<option value="${module}">${module}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label style="color: #FFF;">Room:</label><br>
				<INPUT type="text" id="room" name="room" maxlength="5" required>
			</div>
			<div class="form-group">
				<label style="color: #FFF;">Module Day:</label><br>
				<label style="color: #FFF;">Sun</label><INPUT type="radio" name="day" id="day0" value="sun">
				<label style="color: #FFF;">Mon</label><INPUT type="radio" name="day" id="day1" value="mon"> 
				<label style="color: #FFF;">Tue</label><INPUT type="radio" name="day" id="day2" value="tue"> 
				<label style="color: #FFF;">Wed</label><INPUT type="radio" name="day" id="day3" value="wed"><br>
				<label style="color: #FFF;">Thu</label><INPUT type="radio" name="day" id="day4" value="thu"> 
				<label style="color: #FFF;">Fri</label><INPUT type="radio" name="day" id="day5" value="fri"> 
				<label style="color: #FFF;">Sat</label><INPUT type="radio" name="day" id="day6" value="sat">
				
				<label id="dayLabel" style="color: red; display: none">Day not selected!</label>
			</div>
			<div class="form-group">
				<label style="color: #FFF;">Module Time:</label><br>
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
				 <label style="color: #FFF;">to</label>
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
				</SELECT>
			</div>
			<div class="form-group">
				<INPUT type="submit" name="submitBtn" class="btn btn-lg btn-info" value="AddModule" onclick="isChecked()">
			</div>
		</form>
	</div>
	</aside>
	<section class="timetable">
	<div>
		<TABLE border="1" cellspacing="0">
			<TBODY>
				<TR>
					<TH align="center" valign="middle" width="90">${username}</TH>
					<TH style="background: lightgrey;" align="center" valign="middle" width="100">Sunday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Monday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Tuesday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Wednesday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Thursday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Friday</TH>
					<TH style="background: lightgrey;" align="center" valign="middle">Saturday</TH>
				</TR>
				<c:forEach begin="8" end="21" step="1" var="time">
					<TR>
						<TD align="center" valign="middle" width="80" style="background: lightgrey;"><c:choose>
							<c:when test="${time == 12}">
								<c:out value="${time}" />:00pm
							</c:when>
							<c:when test="${time > 12}">
								<c:out value="${time - 12}" />:00pm
							</c:when>
							<c:otherwise>
								<c:out value="${time}" />:00am
							</c:otherwise>
							</c:choose>
						</TD>
						<c:forEach begin="0" end="6" step="1" var="day">
							<TD align="center" valign="middle" width="100">
								<c:forEach items="${timetable.classes}" var="modules">
									<c:if test="${modules.timeStart <= time 
												&& modules.timeEnd > time 
												&& modules.day == day}">
										<c:out value="${modules.title}" />
										<sup>
											<c:out value="${modules.room}" />
										</sup>

										<button onclick="getModule(this);"
												value="${modules.title}|${modules.room}|${modules.timeStart}|${modules.timeEnd}|${modules.day}"
												id="button" name="button" data-original-title="Edit"
												data-toggle="modal" type="button"
												class="btn btn-xs btn-default" data-target="#myModal">
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
	</div>
	</section>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Edit Module</h4>
				</div>
				<div class="modal-body">
					<form id="editModule" name="editModule" action="Timetable" method="post">
						<div class="form-group">
							<label>Module Title:</label>
							<input class="form-control" type="text" id="editModuleTitle" name="editModuleTitle" placeholder="Title max 15 characters" maxlength="15">
						</div>
						<div class="form-group">
							<label>Room Number:</label>
							<input class="form-control" type="text" id="editRoomNumber" name="editRoomNumber" placeholder="Room max 5 characters" maxlength="5">
						</div>
						<div class="form-group">
							<label>Start Time:</label>
							<input class="form-control" type="text" id="editStartTime" name="editStartTime" placeholder="Room max 5 characters" maxlength="5">
						</div>
						<div class="form-group">
							<label>End Time:</label>
							<input class="form-control" type="text" id="editEndTime" name="editEndTime" placeholder="Room max 5 characters" maxlength="5">
						</div>
						<div class="form-group">
							<label>Day:</label><br>
							<INPUT type="radio" name="editDay" id="sun" value="sun">Sun
							<INPUT type="radio" name="editDay" id="mon" value="mon">Mon
							<INPUT type="radio" name="editDay" id="tue" value="tue">Tue
							<INPUT type="radio" name="editDay" id="wed" value="wed">Wed
							<INPUT type="radio" name="editDay" id="thu" value="thu">Thu
							<INPUT type="radio" name="editDay" id="fri" value="fri">Fri
							<INPUT type="radio" name="editDay" id="sat" value="sat">Sat
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button form="editModule" type="submit" name="submitBtn" value="SaveModule" class="btn btn-default">Save</button>
					<button form="editModule" type="submit" name="submitBtn" value="RemoveModule" class="btn btn-default">Remove</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Module Modal -->
	<div class="modal fade" id="moduleModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Create Module</h4>
				</div>
				<div class="modal-body">
					<form id="createModule" action="Timetable" method="post">
						<div class="form-group">
							<label>Module Title:</label> <input class="form-control" type="text" id="moduleTitle" name="moduleTitle" placeholder="Title max 15 characters" maxlength="15">
						</div>
						<div class="form-group">
							<label>Lecturer:</label> <input class="form-control" type="text" id="lecturer" name="lecturer" placeholder="Lecturer" maxlength="20">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button form="createModule" name="submitBtn" value="CreateModule" type="submit" class="btn btn-default">Create Module</button>
				</div>
			</div>
		</div>
	</div>
	<input form="editModule" type="text" name="OModuleTitle" id="OModuleTitle" style="visibility: hidden">
	<input form="editModule" type="text" name="ORoomNumber" id="ORoomNumber" style="visibility: hidden">
	<input form="editModule" type="text" name="OStartTime" id="OStartTime" style="visibility: hidden">
	<input form="editModule" type="text" name="OEndTime" id="OEndTime" style="visibility: hidden">
	<input form="editModule" type="text" name="ODay" id="ODay" style="visibility: hidden">
</BODY>
</html>