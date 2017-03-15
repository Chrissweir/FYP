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
<script type="text/javascript" src="js/Module.js"></script>
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
					<li><a href="Calendar.jsp">Calendar</a></li>
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
					<form id="createModule" action="Grades" method="post">
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
		            		<h3>Modules:</h3>
		            		    <c:forEach var="module" items="${modules.moduleList}">
			            			<div class="accordion-heading country">
			            			<hr>
			            				<a class="accordion-toggle" data-toggle="collapse" href="#${module.title}">${module.title}</a>
			            				
			            			</div>
			            			
			            			<div id="${module.title}" class="accordion-body collapse">
			            				<div class="accordion-inner">
			            					<table class="table table-striped table-condensed">
			            						<thead>
			            							<tr>
			            								<th>Title</th>
			            								<th></th>
			            								<th>Date</th>
			            								<th>Value</th>
			            								<th>Result</th>
			            								<th>Percentage</th>
			            								<th>
				            								<button id="newGrade" name="newGrade" data-original-title="Add"
																data-toggle="modal" type="button" class="btn btn-sm btn-info pull-right"
																data-target="#gradeModal" value="${module.title}" onClick="getModuleTitle(this);"><span class="glyphicon glyphicon-plus"></span>
															</button>
														</th>
			            							</tr>
			            						</thead>
			            						<c:forEach var="moduleGrade" items="${moduleData.moduleGrades}">
				            						<c:if test="${module.title == moduleGrade.title}">
					            						<tbody>
					            							<tr>
					            								<td>${moduleGrade.gradeTitle}</td>
					            								<td> </td>
					            								<td>${moduleGrade.date}</td>
					            								<td>${moduleGrade.value}</td>
					            								<td>${moduleGrade.result}</td>
					            								<td>
						            								<div class="progress">
	  																	<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:${moduleGrade.result}%">
	    																	${moduleGrade.result}%
																	  	</div>
																	</div>
																</td>
																<td> </td>
					            							</tr>
					            						</tbody>
				            						</c:if>
			            						</c:forEach>
			            					</table>
			            				</div>
			            			</div>
		            			</c:forEach>
		            			<hr>
		            		</div>
		            	</div>
		            </div>
		        </div>
			</div>
		</div>
<!-- Grade Modal -->
	<div class="modal fade" id="gradeModal" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content ">
				<div class="modal-header">
					<h4 class="modal-title">Add</h4>
				</div>
				<div class="modal-body">
					<form id="createGrade" action="Grades" method="post">
						<div class="form-group">
							<label>Title:</label> <input class="form-control" type="text" id="gradeTitle" name="gradeTitle" placeholder="Title max 30 characters" maxlength="30">
						</div>
						<div class="form-group">
							<label>Date:</label> <input class="form-control" type="text" id="gradeDate" name="gradeDate" placeholder="Title max 15 characters" maxlength="15">
						</div>
						<div class="form-group">
							<label>Value:</label> <input class="form-control" type="text" id="gradeValue" name="gradeValue" placeholder="Title max 15 characters" maxlength="15">
						</div>
						<div class="form-group">
							<label>Result:</label> <input class="form-control" type="text" id="gradeResult" name="gradeResult" placeholder="Title max 15 characters" maxlength="15">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button form="createGrade" name="submitBtn" value="CreateGrade" type="submit" class="btn btn-default">Create Grade</button>
				</div>
			</div>
		</div>
	</div>
	<input form="createGrade"type="text" id="gradeModuleTitle" name="gradeModuleTitle" style="visibility: hidden;">
</body>
</html>