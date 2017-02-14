package ie.gmit.sw.Register;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * getConnection() establishes a connection to the database
	 * @return
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	private Connection getConnection() throws URISyntaxException, SQLException {
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(ConnectionString);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	/**
	 * doGet() handles the request from the LoginRegister.jsp page by retrieving 
	 * the details the user submitted on the register form to add them to the database.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

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

			//Establish a connection with the database
			Class.forName("org.postgresql.Driver");
			Connection connection = getConnection();
			
			//Create three new statements
			Statement stmt = connection.createStatement();
			Statement stmt1 = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			
			//Execute a query on the stmt1 statement and assign the results to the ResultSet ru
			ResultSet ru = stmt1.executeQuery("SELECT * FROM Users WHERE username='"+userDetails.getUsername()+"';");
			//Execute a query on the stmt2 statement and assign the results to the ResultSet re
			ResultSet re = stmt2.executeQuery("SELECT * FROM Users WHERE email='"+userDetails.getEmail()+"';");
			
			//Clear the queries in the statements
			stmt1.clearBatch();
			stmt2.clearBatch();
			
			//Reset userAvailable and emailAvailable to true
			userDetails.setUserAvailable(true);
			userDetails.setEmailAvailable(true);
			
			//Check if the ResultSet ru contains results
			//If it does then set userAvailable to false
			if(ru.isBeforeFirst()){
				userDetails.setUserAvailable(false);
			}
			
			//Check if the ResultSet re contains results
			//If it does then set emailAvailable to false
			if(re.isBeforeFirst()){
				userDetails.setEmailAvailable(false);
			}
			
			//If both userAvailable and emailAvailable are true, then add the users details to the database
			//and redirect the user to the LoginRegister.jsp page.
			if(userDetails.isUserAvailable() == true && userDetails.isEmailAvailable() == true){
				String sql = "INSERT INTO Users (first_name, last_name, email, college, username, password, confirmation_code) "
						+"VALUES ('"+userDetails.getFirstname()+"', '"+userDetails.getLastname()+"', '"+userDetails.getEmail()+"', '"+userDetails.getCollege()+"', '"+userDetails.getUsername()+"', '"+userDetails.getPassword()+"', '"+userDetails.getCode()+"')";
				stmt.executeQuery(sql);
				connection.close();
				response.sendRedirect("Profile");
			}
			
			//If userAvailable is false then set an error in the request to inform the user
			if(userDetails.isUserAvailable() == false){
				request.setAttribute("userError","Username Already Registered!");
			}
			
			//If emailAvailable is false then set an error in the request to inform the user
			if(userDetails.isEmailAvailable() == false){
				request.setAttribute("emailError","Email Already Registered!");
			}
			
			//Forward the error messages to the LoginRegister.jsp for the user to see.
            request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
		}
		catch (Exception e) {
		}
	}
}