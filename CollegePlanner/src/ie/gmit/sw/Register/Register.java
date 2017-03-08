package ie.gmit.sw.Register;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Connections.SQLConnection;
import ie.gmit.sw.Security.Encryption;

/**
 * @author Christopher Weir - G00309429
 * 
 * This class is responsible for allowing the user to register for access to the website by 
 * first retrieving the details that were submitted by the user. The class invokes a method 
 * in the SQLConnection class which handles the SQL Database connection and queries. A database
 * query is made to check if the username or the email already exists. If either exist then an 
 * error is returned to the user informing them of this. If neither exist then the users details
 * are added to the database and the users MongoDB entry will be created. The user is then 
 * redirected to the Profile page.
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String returnString;
	private MongoConnection mongo = new MongoConnection();
	private SQLConnection sqlConn = new SQLConnection();
	private RegisterUserDetails userDetails = new RegisterUserDetails();
	private SecureRandom random = new SecureRandom();
	private Encryption encrypt = new Encryption();

	/**
	 * nextSessionId() is responsible for creating a randomly generated String to be added to
	 * each users account. This string will be used as the users session id and to connect
	 * to their data on MongoDB.
	 * @return
	 */
	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	/**
	 * doGet() handles the request from the LoginRegister.jsp page by retrieving 
	 * the details the user submitted on the register form to add them to the database.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		try {
			//Get a handle on the current session
			HttpSession session = request.getSession();
			
			//Retrieve the details that were submitted
			userDetails.setFirstname(request.getParameter("firstname"));
			userDetails.setLastname(request.getParameter("lastname"));
			userDetails.setEmail(request.getParameter("email"));
			userDetails.setCollege(request.getParameter("college"));
			userDetails.setUsername(request.getParameter("username"));
			userDetails.setPassword(encrypt.encrypt((request.getParameter("password"))));
			userDetails.setCourse("");
			userDetails.setBio("");
			
			//Create a new Session Id
			userDetails.setCode(nextSessionId());
			//Get the returnString from SQLConnection
			returnString = sqlConn.userRegistration(userDetails).toString();
			//Check the value of the returString
			//If returnString is equal to "Profile" then user can be registered
			//else output appropriate error 
			if(returnString.equals("Profile")){
				//Create the MongoDB entry for the user
				mongo.setNewUser(userDetails.getCode());
				
				//Assign the userDetails to the session
				session.setAttribute("firstname", userDetails.getFirstname());
				session.setAttribute("lastname",  userDetails.getLastname());
				session.setAttribute("email",  userDetails.getEmail());
				session.setAttribute("college",  userDetails.getCollege());
				session.setAttribute("code",  userDetails.getCode());
				
				//Redirect the user to the Profile page
				response.sendRedirect("Profile");
			}
			//Else if returnString equals "userError", assign error message
			else if(returnString.equals("userError"))
			{
				request.setAttribute("userError","Username Already Registered!");
			}
			//Else if returnString equals "emailError", assign error message
			else if(returnString.equals("emailError")){
				request.setAttribute("emailError","Email Already Registered!");
			}
			//Forward the error messages to the LoginRegister.jsp for the user to see.
	        request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
		}
		//If something goes wrong the redirect the user to the ErrorHandler page
		catch (Exception e) {
		 //response.sendRedirect("ErrorHandler");
		}
	}
}