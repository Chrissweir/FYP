<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/ToDoList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/ToDoList.js"></script>
<title>ToDo List</title>
</head>
<body style="padding-top: 70px">
<script type="text/javascript">$(function () {
    $('[data-toggle="popover"]').popover()
})</script>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand"> <span
				class="glyphicon glyphicon-education" aria-hidden="true"></span></a>
				<a class="navbar-brand" href="About.jsp">College Planner</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="Calendar">Calender</a></li>
				<li><a href="Timetable">Timetable</a></li>
				<li><a href="ToDoList">To Do</a></li>
				<li><a href="Modules">Modules</a></li>
				<li><a href="Assignments">Assignments</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${username}<span class="caret"></span></a>
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

	<!--Create ToDo List -->

<div class="addTask" >

	<form action="ToDoList" method="post">
			<b>Title:</b>
			<input type="text" id="title" name="title" required maxlength="10" placeholder="Max 10 Characters" />
			<b>Description:</b>
			<input type="text" id="description"name="description" required maxlength="300" placeholder="Max 300 Characters" />
			
			<input type="submit" name="btn" value="Save" />
		
	</form>
</div>
</div>
<!--Output tasks-->
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<div style="box-shadow: 0 0 10px #888888;" class="todolist not-done">
				<h1>Todo List</h1>
				<hr>
				<table class="todo" >
			 		<tr>
			  	  		<th width="20%" align="left">Title</th>
					    <th width="60%" style="text-align:center;">Description</th>
					    <th width="20%" align="right">Mark as Done</th>
				  	</tr>
					<c:forEach var="task" items="${todolist.tasks}" >
						<tr role="row">
							<td width="20%" align="left">${task.title}</td> 
							<td width="60%" align="middle"><a href="#" data-toggle="popover" data-trigger="focus" title="Description" data-content="${task.description}">Click to view Description</a></td>
							<td width="20%" align="right"><input form="markAsDone" name="btn" type="checkbox" value="${task.title}|${task.description}" onchange="move(this);"></td> 
						</tr>	
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="col-md-6">
			<div style="box-shadow: 0 0 10px #888888;" class="todolist">
				<h1>Tasks Complete</h1>
				<hr>
				<ul id="done-items" class="list-unstyled">
					<c:forEach var="taskCompleted" items="${todolistCompleted.tasks}">
						<li>${taskCompleted.title}
							<button class="remove-item btn btn-default btn-xs pull-right" 
									value="${taskCompleted.title}|${taskCompleted.description}"
									data-toggle="modal" data-target="#myModal" onClick="remove(this);">
									<span class="glyphicon glyphicon-remove"></span>
							</button>
							<button class="move-item btn btn-default btn-xs pull-right"
									value="${taskCompleted.title}|${taskCompleted.description}"
									data-toggle="modal" data-target="#moveModal" onClick="transfer(this);">
									<span class="glyphicon glyphicon-transfer"></span>
							</button>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>

<form name = "markAsDone" id = "markAsDone" action = "ToDoList" method = "post">
	<input form="markAsDone" type="text" name="taskTitle" id="taskTitle"style="visibility: hidden">
	<input form="markAsDone" type="text" name="taskDescription" id="taskDescription" style="visibility: hidden">
</form>

<form name = "delete" id = "delete" action = "ToDoList" method = "post">
	<input form="delete" type="text" name="deleteTaskTitle" id="deleteTaskTitle"style="visibility: hidden">
	<input form="delete" type="text" name="deleteTaskDescription" id="deleteTaskDescription" style="visibility: hidden">
</form>

<form name = "moveBack" id = "moveBack" action = "ToDoList" method = "post">
	<input form="moveBack" type="text" name="moveTaskTitle" id="moveTaskTitle" style="visibility: hidden">
	<input form="moveBack" type="text" name="moveTaskDescription" id="moveTaskDescription" style="visibility: hidden">
</form>

<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Are you sure you want to delete?</h4>
        </div>
        <div class="modal-body">
        	<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
          <button form="delete" class="btn btn-danger pull-right"type="submit" name="btn" value="Delete">Delete</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Modal -->
  <div class="modal fade" id="moveModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Are you sure?</h4>
        </div>
        <div class="modal-body">
        	<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
          <button form="moveBack" class="btn btn-danger pull-right"type="submit" name="btn" value="Transfer">Transfer</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
