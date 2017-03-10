<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script type="text/javascript" src="js/RecoveryError.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Recovery</title>
</head>
<body>
<div class="form-gap"></div>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                  <h3><i class="fa fa-lock fa-4x"></i></h3>
                  <h2 class="text-center">Forgot Password?</h2>
                  <p>You can reset your password here.</p>
                  <div class="panel-body">
    
                    <form id="register-form" role="form" autocomplete="off" class="form" onsubmit="return validateForm();" action="AccountRecovery" method="post">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
							<input id="password" name="password" placeholder="New Password" class="form-control" type="password">
						</div>
                      	<div class="input-group">
                      		<span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                       		<input id="confpassword" name="confpassword" placeholder="Confirm Password" class="form-control" type="password">
                        </div>
                        <label id="passLabel" style="color: red; display: none">Passwords must Match!</label>
                    </div>
                      <div class="form-group">
                        <button name="recover-submit" class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
	</div>
</div>
</body>
</html>