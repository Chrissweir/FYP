<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/Profile.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/Profile.js"></script>
<script type="text/javascript">
	$('document').ready(
			function() {
				document.getElementById("imgPath").value = document.getElementById("image").src;
				updateData();
				function updateData() {
					document.getElementById("firstname").value = document.getElementById("fname").value;
					document.getElementById("lastname").value = document.getElementById("lname").value;
					document.getElementById("email").value = document.getElementById("em").value;
					document.getElementById("college").value = document.getElementById("coll").value;
					document.getElementById("course").value = document.getElementById("cour").value;
					document.getElementById("bio").value = document.getElementById("biog").value;
				}
				$(document).on("change, keyup", "#fname, #lname, #em, #coll, #cour, #biog", updateData);
			});
</script>
<title>My Profile</title>

</head>
<body class="background">
	<div id="nav-container">
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
	</div>

	<div id="profile-container">
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			<form id="userDetails" action="Profile" method="post">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Profile - ${username}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3" align="center">
								<img id="image" alt="User Pic" src="<%=session.getAttribute("image")%>" class="img-circle img-responsive">
								<div id="imageEdit" class="image-upload" style="visibility: hidden">
									<label class="btn btn-default btn-file"> Edit 
										<input id="imgFile" type="file" accept="image/gif, image/jpeg, image/png" onchange="path()" style="display: none;">
									</label>
								</div>
								<input type="text" name="imgPath" id="imgPath" style="visibility: hidden" />
							</div>
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>First Name:</td>
											<td><input style="border: none; width: 100%;" maxlength="100" placeholder="First Name" type="text" id="fname" width="60" value="${firstname}" readonly required></input></td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td><input style="border: none; width: 100%;" maxlength="100" placeholder="Last Name" type="text" id="lname" width="60" value="${lastname}" readonly required></input></td>
										</tr>
										<tr>
											<td>Email:</td>
											<td><input style="border: none; width: 100%;" maxlength="100" placeholder="Email" type="email" id="em" value="${email}" readonly required></input></td>
										</tr>
										<tr>
											<td>College:</td>
											<td><input style="border: none; width: 100%;" maxlength="100" placeholder="College Name" type="text" id="coll" width="60" value="${college}" readonly required></input></td>
										</tr>
										<tr>
											<td>Course:</td>
											<td><input style="border: none; width: 100%;" maxlength="100" placeholder="Course Title" type="text" id="cour" width="60" value="${course}" readonly></input></td>
										</tr>
										<tr>
											<td>Bio:</td>
											<td><textarea style="border: none; min-width: 370px; max-width: 370px;" maxlength="300" placeholder="300 Characters" type="text" id="biog" cols="20" rows="7" readonly>${bio}</textarea></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<button onclick="edit()" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning">
							<i class="glyphicon glyphicon-edit"></i>
						</button>
						<button name="btn" value="update" id="saveBtn" style="visibility: hidden" onclick="save()" data-original-title="Save details" data-toggle="tooltip" type="submit" class="btn btn-sm btn-success">
							<i class="glyphicon glyphicon-floppy-disk"></i>
						</button>
						<span class="pull-right">
							<button data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger" onclick="document.getElementById('id01').style.display='block'">
								<i class="glyphicon glyphicon-remove"></i>
							</button>
						</span>
					</div>
			</form>
		</div>

		<input form="userDetails" type="text" name="firstname" id="firstname" style="visibility: hidden" required> 
		<input form="userDetails" type="text" name="lastname" id="lastname" style="visibility: hidden" required>
		<input form="userDetails" type="email" name="email" id="email" style="visibility: hidden" required>
		<input form="userDetails" type="text" name="college" id="college" style="visibility: hidden" required>
		<input form="userDetails" type="text" name="course" id="course" style="visibility: hidden">
		<input form="userDetails" type="text" name="bio" id="bio" style="visibility: hidden">
	</div>

	<div id="modal-container">
		<div id="id01" class="w3-modal">
			<div class="w3-modal-content w3-animate-top w3-card-8">
				<header class="w3-container w3-red">
					<span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn">&times;</span>
					<h2>Remove your Account?</h2>
				</header>
				<div class="w3-container">
					<p>
						<b>Doing so will permanently delete your account and all of your data!</b>
					</p>
					<p>Please enter your password to confirm:</p>
					<input form="userDetails" name="confirmPassword" id="confirmPassword" type="password" autocomplete="new-password">
					<%
						String pass = (String) request.getAttribute("error");
						if (pass != null)
							out.println("<font color=red size=4px>" + pass + "</font>");
					%>
				</div>
				<br>
				<footer class="w3-container w3-red">
					<p></p>
					<button type="button" class="btn btn-warning" onclick="document.getElementById('id01').style.display='none'">Cancel</button>
					<span class="pull-right">
						<button form="userDetails" type="submit" value="delete" name="btn" class="btn btn-danger">Delete</button>
					</span>
					<p></p>
				</footer>
			</div>
		</div>
	</div>
</body>
</html>