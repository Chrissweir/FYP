<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="Register.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="Register" role="form">
				<div>
					<h2>Create account</h2>
				</div>
				<div class="form-group">
					<label class="control-label" for="signupName">Username</label> <input
						id="signupUserName" name="username" type="text" maxlength="50"
						class="form-control">
					<%
					String user_msg=(String)request.getAttribute("userError");  
					if(user_msg!=null)
					out.println("<font color=red size=2px>"+user_msg+"</font>");
					%>
				</div>

				<div class="form-group">
					<label class="control-label" for="signupFirstName">First
						Name</label> <input id="signupFirstName" name="firstname" type="text"
						maxlength="50" class="form-control">
				</div>
				<div class="form-group">
					<label class="control-label" for="signupLastName">Last Name</label>
					<input id="signupLastName" name="lastname" type="text"
						maxlength="50" class="form-control">
				</div>
				<div class="form-group">
					<label class="control-label" for="signupEmail">Email</label> <input
						id="signupEmail" name="email" type="email" maxlength="50"
						class="form-control">
					<%
					String email_msg=(String)request.getAttribute("emailError");  
					if(email_msg!=null)
					out.println("<font color=red size=4px>"+email_msg+"</font>");
					%>
				</div>

				<div class="form-group">
					<label class="control-label" for="signupEmailagain">Email
						again</label> <input id="signupEmailagain" type="email" maxlength="50"
						class="form-control">
				</div>
				<div class="form-group">
					<label class="control-label" for="signupPassword">Password</label>
					<input id="signupPassword" name="password" type="password"
						maxlength="25" class="form-control"
						placeholder="at least 6 characters" length="40">
				</div>
				<div class="form-group">
					<label class="control-label" for="signupPasswordagain">Password
						again</label> <input id="signupPasswordagain" type="password"
						maxlength="25" class="form-control">
				</div>
				<div class="form-group">
					<label class="control-label" for="signupCollege">College</label> <input
						id="signupCollege" name="college" type="text" maxlength="25"
						class="form-control">
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3">Gender</label>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label class="radio-inline"> <input type="radio"
									id="femaleRadio" name="gender" value="Female">Female
								</label>
							</div>
							<div class="col-sm-4">
								<label class="radio-inline"> <input type="radio"
									id="maleRadio" name="gender" value="Male">Male
								</label>
							</div>
						</div>
					</div>
				</div>
				<!-- /.form-group -->
				<div class="form-group">
					<button id="signupSubmit" type="submit"
						class="btn btn-info btn-block">Create your account</button>
				</div>
				Already have an account? <a href="Login.jsp">Sign in</a>
				</p>
			</form>
		</div>
	</div>

</body>
</html>