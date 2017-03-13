<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Grades</title>
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
	

	<button id="newModule" name="newModule" data-original-title="Edit"
			data-toggle="modal" type="button" class="btn btn-primary btn-info"
			data-target="#moduleModal"><span class="glyphicon glyphicon-plus"></span>Create Module
	</button>
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
	
		<!-- Collapsable Module Table -->
		<div class="container">
			<div class="row">
		        <div class="span12">
		    		<div class="menu">
		                <div class="accordion">
		            		<div class="accordion-group">
		            		    <c:forEach var="module" items="${moduleList}">
			            			<div class="accordion-heading country">
			            				<a class="accordion-toggle" data-toggle="collapse" href="#${module}">${module}</a>
			            			</div>
			            			<div id="${module}" class="accordion-body collapse">
			            				<div class="accordion-inner">
			            					<table class="table table-striped table-condensed">
			            						<thead>
			            							<tr>
			            								<th>Site</th>
			            								<th>Clients</th>
			            								<th>Primary</th>
			            								<th>Secondary</th>
			            								<th>DP</th>
			            								<th>BDP</th>
			            								<th>Status</th>
			            							</tr>
			            						</thead>   
			            						<tbody>
			            							<tr>
			            								<td>Site-1</td>
			            								<td>123</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>OK</td>
			            							</tr>
			            							<tr>
			            								<td>Site-2</td>
			            								<td>321</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>-</td>
			            								<td>OK</td>
			            							</tr>
			            						</tbody>
			            					</table>
			            				</div>
			            			</div>
		            			</c:forEach>
		            		</div>
		            	</div>
		            </div>
		        </div>
			</div>
		</div>

</body>
</html>