<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/Assignment.js"></script>
<title>Assignments</title>
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

<!-- Assignment Table -->
<div>
	<div class="container">
		<div class="row">
	        <div class="span12">
	        <h2 style="text-align:center;">Assignments</h2>
	    		<div class="menu">
	                <div class="accordion">
	            		<div class="accordion-group">
	            		    <c:forEach var="module" items="${modules.moduleList}">
		            			<div class="accordion-header country">
			            			<hr>
			            			<table style="width: 100%;">
			            				<thead>
			            					<tr>
			            						<th>
			            							<a class="accordion-toggle" data-toggle="collapse" href="#${module.title}">${module.title}</a>
		            							</th>
					            				<th></th>
					            				<th></th>
											</tr>
										</thead>
									</table>
		            			</div>
		            			<div id="${module.title}" class="accordion-body collapse">
		            				<div class="accordion-inner">
		            					<table class="table table-hover">
		            						<thead>
		            							<tr>
		            								<th style="width: 40%">Assignment Title</th>
		            								<th style="width: 30%">Date Due</th>
		            								<th style="width: 20%">Worth (%)</th>
		            								<th style="width: 10%">
			            								<button id="newAssignment" name="newAssignment" data-original-title="Add"
																data-toggle="modal" type="button" class="btn btn-sm btn-info pull-right"
																data-target="#assignmentModal" value="${module.title}" onClick="getModuleTitle(this);">
																<span class="glyphicon glyphicon-plus"></span>
														</button>
													</th>
		            							</tr>
		            						</thead>
		            						<c:forEach var="moduleAssignment" items="${assignmentData.moduleAssignments}">
			            						<c:if test="${module.title == moduleAssignment.title}">
				            						<tbody>
				            							<tr>
				            								<td style="width: 40%">${moduleAssignment.assignmentTitle}</td>
				            								<td style="width: 30%">${moduleAssignment.date}</td>
				            								<td style="width: 20%">${moduleAssignment.value}%</td>
				            								<td style="width: 10%">
																<button type="submit" form="deleteAssignment" id="submitBtn" name="submitBtn" data-original-title="DeleteAssignment"
																		class="remove-item btn btn-default btn-xs pull-right"
																		value="${module.title}|${moduleAssignment.assignmentTitle}|${moduleAssignment.date}|${moduleAssignment.value}" onClick="removeAssignment(this);">
																<span class="glyphicon glyphicon-remove"></span>
																</button>
															</td>
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
</div>

<!-- Assignment Modal -->
<div class="modal fade" id="assignmentModal" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content ">
			<div class="modal-header">
				<h4 class="modal-title">Add</h4>
			</div>
			<div class="modal-body">
				<form id="createAssignment" action="Assignments" method="post">
					<div class="form-group">
						<label>Title:</label> <input class="form-control" type="text" id="assignmentTitle" name="assignmentTitle" placeholder="Title max 30 characters" maxlength="30">
					</div>
					<div class="form-group">
						<label>Date:</label> <input class="form-control" type="date" id="assignmentDate" name="assignmentDate" placeholder="Title max 15 characters" maxlength="15">
					</div>
					<div class="form-group">
						<label>Value:</label> <input class="form-control" type="text" id="assignmentValue" name="assignmentValue" placeholder="Title max 15 characters" maxlength="15">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button form="createAssignment" name="submitBtn" value="CreateAssignment" type="submit" class="btn btn-default">Create Assignment</button>
			</div>
		</div>
	</div>
</div>

<input form="createAssignment"type="text" id="moduleAssignmentTitle" name="moduleAssignmentTitle" style="visibility: hidden;">
<form id="deleteAssignment" name="deleteAssignment" action="Assignments" method="post">
<input type="text" id="deleteAssignmentModule" name="deleteAssignmentModule" style="visibility: hidden;">
<input type="text" id="deleteAssignmentTitle" name="deleteAssignmentTitle" style="visibility: hidden;">
<input type="text" id="deleteAssignmentDate" name="deleteAssignmentDate" style="visibility: hidden;">
<input type="text" id="deleteAssignmentValue" name="deleteAssignmentValue" style="visibility: hidden;">
</form>
</body>
</html>