package ie.gmit.sw.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.SQLConnection;

/**
 * @author Christopher Weir - G00309429
 * 
 * This Servlet is responsible for allowing the user to login to the website by 
 * first retrieving the username and password that was entered. The class then establishes 
 * a connection with the postgres SQL database hosted on Heroku. A database query is made to 
 * check if the user exists and if it does then check if the password matches the users password.
 * Once confirmed, the users data is passed into the request object and forwarded to the Profile.jsp
 * page.
 */
@WebServlet("/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LoginValues login = new LoginValues();
	private SQLConnection sqlConn = new SQLConnection();

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/**
	 * doGet() handles the request from the LoginRegister.jsp page by retrieving 
	 * the username and password that was submitted and using them to query the database
	 * and pass the data to the Profile.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			//Retrieve the username and password that was submitted
			login.setUsername(request.getParameter("username"));
			login.setPassword(request.getParameter("password"));
			
			//Pass the login values to SQLConnection which will query the database
			sqlConn.userLogin(login);

			//User validation, check if the password that was submitted is the same and the password
			//retrieved from the database. If it is then pass the specified data to the request object
			//and forward the request to the Profile.jsp page
			if(login.getPass().equals(login.getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("firstname", login.getFirstname());
				session.setAttribute("lastname", login.getLastname());
				session.setAttribute("email", login.getEmail());
				session.setAttribute("college", login.getCollege());
				session.setAttribute("username", login.getUser());
				session.setAttribute("code", login.getCode());
				response.sendRedirect("Profile");
			}

			//If the passwords do not match then send an error back to the LoginRegister.jsp page
			else{
				request.setAttribute("error","Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("LoginRegister.jsp");            
				rd.include(request, response);
			}
		//If something goes rong the redirect the user to the ErrorHandler page
		}catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}
}