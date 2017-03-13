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
				<li><a href="Calendar.jsp">Calender</a></li>
				<li><a href="Timetable">Timetable</a></li>
				<li><a href="ToDoList">To do</a></li>
				<li><a href="Grades">Grades Tracker</a></li>
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

<div class="addTask">
	<form action="ToDoListServlet" method="post" >

		<b> Add Title:</b> <input type="text" id="title" name="title" required />


		<b>Description:</b> <input type="text" id="description"name="description" required /> <input type="submit" value="Save" />

	</form>
	</div>
	<br>
	<!--Output tasks-->
	<hr>

	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="todolist not-done">
				
					<h1>Todo List</h1>
					<hr>
					<table class="todo">
						<c:forEach var="task" items="${todolist.tasks}" >
							<tr role="row">
								<td>${task.title}</td> 
								<td>${task.description}</td>
								<td><input type="checkbox" value="${task.title}" onchange="myFunction(this);"></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="col-md-6">
				<div class="todolist">
					<h1>Tasks Complete</h1>
					<hr>
					<ul id="done-items" class="list-unstyled">
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>