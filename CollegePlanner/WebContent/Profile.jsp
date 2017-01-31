<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/Profile.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/Profile.js"></script>
<title>My Profile</title>

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
				<li><a href="#">Calender</a></li>
				<li><a href="#">Timetable</a></li>
				<li><a href="#">To do</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">My Profile<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="Profile.jsp">Account Details <span
								class="glyphicon glyphicon-cog" aria-hidden="true"></span></a></li>
						<li role="separator" class="divider"></li>
						<li><a href="Logout">Logout</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	Welcome....
	<br>
	<!-- JSTL -->
	<p>The data from servlet: ${code}</p>
	<div
		class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
		<form id="userDetails" action="Profile" method="post"
			enctype="multipart/form-data">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">${username}</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-lg-3" align="center">
							<img id="image" alt="User Pic"
								src="data:image/png;base64,<%=session.getAttribute("image")%>"
								class="img-circle img-responsive">
							<div id="imageEdit" class="image-upload"
								style="visibility: hidden">
								<label class="btn btn-default btn-file"> Edit <input
									id="imgFile" type="file" accept="image/gif, image/jpeg, image/png" onchange="path()"
									style="display: none;">
								</label> <input type="text" id="imagePath" style="visibility: hidden">
							</div>
						</div>
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>First Name:</td>
										<td><textfield type="text" id="firstname">${firstname}</textfield></td>
									</tr>
									<tr>
										<td>Last Name:</td>
										<td><textfield type="text" id="lastname">${lastname}</textfield></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><textfield type="text" id="email">${email}</textfield></td>
									</tr>
									<tr>
										<td>College</td>
										<td><textfield type="text" id="college">${college}</textfield></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<button onclick="edit()" data-original-title="Edit this user"
						data-toggle="tooltip" type="button" class="btn btn-sm btn-warning">
						<i class="glyphicon glyphicon-edit"></i>
					</button>
					<button for="userDetails" id="saveBtn" style="visibility: hidden"
						onclick="save()" data-original-title="Save details"
						data-toggle="tooltip" type="submit" class="btn btn-sm btn-success">
						<i class="glyphicon glyphicon-floppy-disk"></i>
					</button>
					<span class="pull-right"> <a
						data-original-title="Remove this user" data-toggle="tooltip"
						type="button" class="btn btn-sm btn-danger"><i
							class="glyphicon glyphicon-remove"></i></a>
					</span>
				</div>
		</form>
	</div>
	</div>
</body>
</html>