<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
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
	
<!-- Table Header -->
<div>
	<button id="newModule" name="newModule" data-original-title="Edit"
			data-toggle="modal" type="button" class="btn btn-primary btn-info"
			data-target="#moduleModal">
			<span class="glyphicon glyphicon-plus"></span>Create Module
	</button>
	<div class="container">
		<div class="row">
	        <div class="span12">
				<div style="float:left;"><h3>Modules:</h3></div>
				<div class="progress pull-right" style="width: 40%;">
					<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:${average}%">
					${average}%
			  		</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
<!-- Module Table -->
<div>
	<div class="container">
		<div class="row">
	        <div class="span12">
	    		<div class="menu">
	                <div class="accordion">
	            		<div class="accordion-group">
	            		    <c:forEach var="module" items="${modules.moduleList}">
		            			<div class="accordion-heading country">
			            			<hr>
			            			<table style="text-align:center;">
			            				<thead>
			            					<tr>
			            						<th align="left">
			            							<a class="accordion-toggle" data-toggle="collapse" href="#${module.title}">${module.title}</a>
		            							</th>
					            				<th style="text-align:center;">
					            					<div class="progress" style="width: 40%;">
														<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:${module.average}%">
														${module.average}%
									  					</div>
													</div>
												</th>
					            				<th align="right">
					            					<button id="deleteModule" name="deleteModule" data-original-title="Delete"
															data-toggle="modal" type="button" class="remove-item btn btn-default btn-xs pull-right"
															data-target="#deleteModuleModal" value="${module.title}|${module.lecturer}" onClick="deleteModule(this);">
															<span class="glyphicon glyphicon-remove"></span>
													</button>
												</th>
											</tr>
										</thead>
									</table>
		            			</div>
		            			<div id="${module.title}" class="accordion-body collapse">
		            				<div class="accordion-inner">
		            					<table class="table table-striped table-condensed">
		            						<thead>
		            							<tr>
		            								<th>Title</th>
		            								<th></th>
		            								<th>Date</th>
		            								<th>Value (%)</th>
		            								<th>Result (%)</th>
		            								<th>Percentage</th>
		            								<th>
			            								<button id="newGrade" name="newGrade" data-original-title="Add"
																data-toggle="modal" type="button" class="btn btn-sm btn-info pull-right"
																data-target="#gradeModal" value="${module.title}" onClick="getModuleTitle(this);">
																<span class="glyphicon glyphicon-plus"></span>
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
				            								<td>${moduleGrade.value}%</td>
				            								<td>${moduleGrade.result}%</td>
				            								<td>
					            								<div class="progress">
  																	<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:${moduleGrade.grade}%">
    																	${moduleGrade.grade}%
																  	</div>
																</div>
															</td>
															<td>
																<button type="submit" form="deleteGrades" id="submitBtn" name="submitBtn" data-original-title="DeleteGrade"
																		class="remove-item btn btn-default btn-xs pull-right"
																		value="${module.title}|${moduleGrade.gradeTitle}|${moduleGrade.date}|${moduleGrade.value}|${moduleGrade.result}" onClick="removeGrade(this);">
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

	
<!-- Modals -->
<!-- Module Modal -->
<div class="modal fade" id="moduleModal" role="dialog">
	<div class="modal-dialog">
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
						<label>Date:</label> <input class="form-control" type="date" id="gradeDate" name="gradeDate" placeholder="Title max 15 characters" maxlength="15">
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

<!-- Delete Module Modal -->
<div class="modal fade" id="deleteModuleModal" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Delete Module</h4>
			</div>
			<div class="modal-body">
				<form id="deleteModule" action="Grades" method="post">
					<div class="form-group">
						<h4>Are you sure you wish to delete:</h4>
						<label>Module Title:</label> <input class="form-control" type="text" id="deleteModuleTitle" name="deleteModuleTitle"  maxlength="40" readonly>
					</div>
					<div class="form-group">
						<label>Lecturer:</label> <input class="form-control" type="text" id="deleteModuleLecturer" name="deleteModuleLecturer" maxlength="40" readonly>
					</div>
					<h4>Doing so will remove all data belonging to this module!</h4>
				</form>
			</div>
			<div class="modal-footer">
				<button form="deleteModule" name="submitBtn" value="DeleteModule" type="submit" class="btn btn-default">Delete Module</button>
			</div>
		</div>
	</div>
</div>

<input form="createGrade"type="text" id="gradeModuleTitle" name="gradeModuleTitle" style="visibility: hidden;">
<form id="deleteGrades" name="deleteGrades" action="Grades" method="post">
<input type="text" id="deleteGradeModule" name="deleteGradeModule" style="visibility: hidden;">
<input type="text" id="deleteGradeTitle" name="deleteGradeTitle" style="visibility: hidden;">
<input type="text" id="deleteGradeDate" name="deleteGradeDate" style="visibility: hidden;">
<input type="text" id="deleteGradeValue" name="deleteGradeValue" style="visibility: hidden;">
<input type="text" id="deleteGradeResult" name="deleteGradeResult" style="visibility: hidden;">
</form>
</body>
</html>