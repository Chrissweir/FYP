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

/**
 * @author Christopher Weir - G00309429
 * 
 * This class is responsible for allowing the user to register for access to the website by 
 * first retrieving the details that were submitted by the user. The class then establishes 
 * a connection with the postgres SQL database hosted on Heroku. A database query is made to 
 * check if the username or the email already exists. If either exist then an error is returned
 * to the user informing them of this. If neither exist the the users details are added to the 
 * database and the user is redirected to the LoginRegister.jsp page to login.
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String r;
	private MongoConnection mongo = new MongoConnection();
	private SQLConnection sqlConn = new SQLConnection();
	private RegisterUserDetails userDetails = new RegisterUserDetails();
	private SecureRandom random = new SecureRandom();


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

			//Retrieve the details that were submitted
			userDetails.setFirstname(request.getParameter("firstname"));
			userDetails.setLastname(request.getParameter("lastname"));
			userDetails.setEmail(request.getParameter("email"));
			userDetails.setCollege(request.getParameter("college"));
			userDetails.setUsername(request.getParameter("username"));
			userDetails.setPassword(request.getParameter("password"));

			//Create a new Session Id
			userDetails.setCode(nextSessionId());
			r = sqlConn.userRegistration(userDetails).toString();
			
			if(r.equals("Profile")){
				HttpSession session = request.getSession();
				mongo.setNewUser(userDetails.getCode());
				session.setAttribute("firstname", userDetails.getFirstname());
				session.setAttribute("lastname",  userDetails.getLastname());
				session.setAttribute("email",  userDetails.getEmail());
				session.setAttribute("college",  userDetails.getCollege());
				session.setAttribute("code",  userDetails.getCode());
				response.sendRedirect("Profile");
			}else if(r.equals("userError"))
			{
				request.setAttribute("userError","Username Already Registered!");
			}else if(r.equals("emailError")){
				request.setAttribute("emailError","Email Already Registered!");
			}
			//Forward the error messages to the LoginRegister.jsp for the user to see.
	        request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
		}
		catch (Exception e) {
		}
	}
}