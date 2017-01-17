<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="Login.css">
<title>Login</title>
</head>
<body>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="text-center">Welcome</h1>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<form action="Login">
						<input type="text" name="username" class="form-control input-lg"
							placeholder="Username" /> <br> <input type="password" name="password"
							class="form-control input-lg" placeholder="Password" /> <br> <input
							type="submit" class="btn btn-block btn-lg btn-primary"
							value="Login" /><br>
					</form>
					<%
					String login_msg=(String)request.getAttribute("error");  
					if(login_msg!=null)
					out.println("<font color=red size=4px>"+login_msg+"</font>");
					%>
					<form action="Registration">
						<span class="pull-right"><a href="Registration.jsp">Register</a></span></span>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>