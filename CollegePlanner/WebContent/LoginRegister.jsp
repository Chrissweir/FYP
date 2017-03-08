<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="css/LoginRegister.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      <script type="text/javascript" src="js/LoginRegister.js"></script>
      <script type="text/javascript" src="js/RegisterError.js"></script>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Login/Register</title>
   </head>
   <body>
      <div class="background"></div>
      <div class="container">
         <div class="row">
            <div class="col-md-6 col-md-offset-3">
               <div class="title">
                  <p>College Planner</p>
               </div>
               <div class="panel panel-login">
                  <div class="panel-heading">
                     <div class="row">
                        <div class="col-xs-6">
                           <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                           <a href="#" id="register-form-link">Register</a>
                        </div>
                     </div>
                     <hr>
                  </div>
                  <div class="panel-body">
                     <div class="row">
                        <div class="col-lg-12">
                           <form id="login-form" action="Login" method="post" style="display: block;">
                              <div class="form-group">
                                 <input type="text" name="username" id="username" tabindex="1"
                                    	class="form-control" placeholder="Username">
                              </div>
                              <div class="form-group">
                                 <input type="password" name="password" id="password"
                                    	tabindex="2" class="form-control" placeholder="Password">
                              </div>
                              <!-- Java code to apply the Login Error message when necessary -->
                              <%
                                 String login_msg = (String) request.getAttribute("error");
                                 if (login_msg != null)
                                 	out.println("<font color=red size=4px>" + login_msg + "</font>");
                                 %>
                              <div class="form-group text-center">
                                 <input type="checkbox" tabindex="3" class="" name="remember"
                                    	id="remember">
                                    	<label for="remember">Remember Me</label>
                              </div>
                              <div class="form-group">
                                 <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                       <input type="submit" name="login-submit" id="login-submit"
                                        	  tabindex="4" class="form-control btn btn-login"
                                        	  value="Log In">
                                    </div>
                                 </div>
                              </div>
                              <div class="form-group">
                                 <div class="row">
                                    <div class="col-lg-12">
                                       <div class="text-center">
                                          <a href="http://phpoll.com/recover" tabindex="5"
                                             class="forgot-password">Forgot Password?</a>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </form>
                           <form id="register-form" name="register-form"
                             	 onsubmit="return validateForm();" action="Register"
                             	 method="post" style="display: none">
                              <div class="form-group">
                                 <input id="signupUserName" name="username" type="text"
	                                    maxlength="50" class="form-control" placeholder="Username"
	                                    required>
                                 <!-- Java code to apply the User Error message -->
                                 <%
                                    String user_msg = (String) request.getAttribute("userError");
                                    if (user_msg != null)
                                    	out.println("<font color=red size=2px>" + user_msg + "</font>");
                                    %>
                              </div>
                              <div class="form-group">
                                 <input id="signupFirstName" name="firstname" type="text"
                                  	  	maxlength="50" class="form-control" placeholder="First Name"
                                    	required>
                              </div>
                              <div class="form-group">
                                 <input id="signupLastName" name="lastname" type="text"
                                    	maxlength="50" class="form-control" placeholder="Last Name"
                                   	 	required>
                              </div>
                              <div class="form-group">
                                 <input id="signupEmail" name="email" type="email"
	                                    maxlength="50" class="form-control" placeholder="Email"
	                                    required>
                                 <!-- Java code to apply the Email Error message -->
                                 <%
                                    String email_msg = (String) request.getAttribute("emailError");
                                    if (email_msg != null)
                                    	out.println("<font color=red size=4px>" + email_msg + "</font>");
                                    %>
                              </div>
                              <div class="form-group">
                                 <input id="signupPassword" name="password" type="password"
	                                    maxlength="25" class="form-control" placeholder="Password"
	                                    length="40" required>
	                                    <label id="passLabel" style="color: red; display: none">Passwords must Match!</label>
                              </div>
                              <div class="form-group">
                                 <input id="signupPasswordagain" type="password" maxlength="25"
                               		     class="form-control" placeholder="Confirm Password" required>
                                 <label id="confpassLabel" style="color: red; display: none">Passwords
                                 must Match!</label>
                              </div>
                              <div class="form-group">
                                 <input id="signupCollege" name="college" type="text"
                                    maxlength="25" class="form-control"
                                    placeholder="Full name of College/University" required>
                              </div>
                              <div class="form-group">
                                 <button id="signupSubmit" type="submit" form="register-form"
                                    class="btn btn-info btn-block">Register</button>
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