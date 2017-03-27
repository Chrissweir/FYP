<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/LoginRegister.css">
<title>Account Recovery</title>
</head>
<body>
<div class="background"></div>
<div class="form-gap"></div>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-login" style="margin-top:40px">
              <div class="panel-body">
                <div class="text-center">
                  <h3><i class="fa fa-lock fa-4x"></i></h3>
                  <h2 class="text-center">Forgot Password?</h2>
                  <p>You can reset your password here.</p>
                  <div class="panel-body">
    
                    <form id="register-form" role="form" autocomplete="off" class="form" action="AccountRecovery" method="post">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user color-blue"></i></span>
							<input id="username" name="username" placeholder="username" class="form-control" type="text">
						</div>
                      	<div class="input-group">
                      		<span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                       		<input id="email" name="email" placeholder="email address" class="form-control"  type="email">
                        </div>
                    </div>
                      <div class="form-group">
                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                      </div>
                    </form>
    
                  </div>
                  <div class="form-group">
                                 <div class="row">
                                    <div class="col-lg-12">
                                       <div class="text-center">
                                          <a href="Login" tabindex="5"
                                             class="forgot-password">Log In</a>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                </div>
              </div>
            </div>
          </div>
	</div>
</div>
</body>
</html>