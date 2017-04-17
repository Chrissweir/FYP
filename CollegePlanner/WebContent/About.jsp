<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>About Us</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/About.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body id="myPage" style="padding-top: 50px;">
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
					<li><a href="Calendar">Calendar</a></li>
					<li><a href="Timetable">Timetable</a></li>
					<li><a href="ToDoList">To do</a></li>
					<li><a href="Grades">Grades Tracker</a></li>
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

<div class="background"></div>
	<div class="container content-section text-center">
		<h3>College Planner</h3>
		  	<p><em>Christopher Weir - G00309429, Gareth Lynskey - G00312651, Paul Dolan - G00297086, Patrick Griffin - G00314635</em></p>
		  	<p>Throughout our three years of studying at Galway-Mayo Institute of Technology, we were embraced with the pressure of multiple Projects, Assessments, with struggled deadlines and trying to keep track of a list of different day to day tasks to be completed, whether college orientated or not. We have concluded that students need a more mobile and more accessible online platform for their academic organisation. Rather than having sheets of paper with lists or timetables we have created a system that incorporates numerous services for all students ranging from leaving cert level to third level students.</p>
			<p>In order to get a sense of what we should incorporate into our system, we looked at what websites and mobile applications are currently available that offer college organisational support for students to use. Based on our findings we created a plan for our system. We used what we found to be the most suitable features from these websites and mobiles applications, along with introducing our own features that we believe would enhance the user experience of our system.</p>
			<p>We wanted to create a way for students to track events, manage their college timetable, and keep track of assignments. We also understood that it is important for students to keep track of their college performance along with their overall Grade Point Average (GPA). Our plan was to have six key features which were a Calendar, Timetable, To-do List, Assignments Tracker, and a Modules Tracker to track your current progress within each Module.</p>
			<p>Upon planning what we each wished to achieve from the creation of this system, we settle on gaining greater knowledge of the Java programming language. After further deliberation from our meetings we then decided that this system should be created as a web application developed in J2EE which is Java 2 Platform, Enterprise Edition. J2EE provides the types of services that are necessary to build large scale, distributed, component based, multi-tier applications. Learning J2EE was new to us in the sense of using JSP, Servlets and incorporating other technologies to build this web application.</p>
	</div>
</body>
</html>