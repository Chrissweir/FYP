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
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>ToDo List</title>
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

	<!--Create ToDo List -->

	<form action="ToDoListServlet" method="post">

		Add Title : <input type="text" id="title" name="title" /> 
		
		
		Description: <input type="text" id="description" name="description" /> <input type="submit" value="Save" />
		
		

	</form>
	<br>
	<!--Output tasks-->
	<hr>

	<b>To Do List</b>
	<br />
	<!--Add item to ToDo List-->
	<%
		//Gets todo list
		List<String> task = (List<String>) session.getAttribute("myToDoList");

		//Creates new todo list
		if(task == null){
			task = new ArrayList<String>();
			session.setAttribute("myToDoList", task);
		}
		
		//Checks to see if form needs to be added
		String title = request.getParameter("title");
		if(title != null){
			task.add(title);
		}
		
	%>

</body>
</html>