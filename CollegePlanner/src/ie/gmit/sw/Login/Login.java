package ie.gmit.sw.Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.SQLConnection;

/**
 * @author Christopher Weir - G00309429
 * 
 * This class is responsible for allowing the user to login to the website by 
 * first retrieving the username and password that was entered. The class then establishes 
 * a connection with the postgres SQL database hosted on Heroku. A database query is made to 
 * check if the user exists and if it does then check if the password matches the users password.
 * Once confirmed, the users data is passed into the request object and forwarded to the Profile.jsp
 * page.
 */
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String pass;
	private String code;
	private String user;
	private String firstname;
	private String lastname;
	private String email;
	private String college;
	private String username;
	private String password;
	/**
	 * getConnection() establishes a connection to the database
	 * @return
	 * @throws URISyntaxException
	 * @throws SQLException
	 */


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
			// Retrieve the username and password that was submitted
			username = request.getParameter("username");
			password = request.getParameter("password");
			SQLConnection c = new SQLConnection();

			//Establish a connection with the database
			Class.forName("org.postgresql.Driver");
			Connection connection = c.getConnection();

			//Create a new statement
			Statement stmt = connection.createStatement();

			//Execute a query on the statement and assign the results to the ResultSet rs
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE username='"+username+"';" );

			//Using a while loop, for every entry in the ResultSet retrieve the specified data
			while ( rs.next() ) {
				firstname = rs.getString("first_name");
				lastname = rs.getString("last_name");
				email = rs.getString("email");
				college = rs.getString("college");
				user = rs.getString("username");
				pass = rs.getString("password");
				code = rs.getString("confirmation_code");
			}

			//User validation, check if the password that was submitted is the same and the password
			//retrieved from the database. If it is then pass the specified data to the request object
			//and forward the request to the Profile.jsp page
			if(pass.equals(password)){
				HttpSession session = request.getSession();
				session.setAttribute("firstname", firstname);
				session.setAttribute("lastname", lastname);
				session.setAttribute("email", email);
				session.setAttribute("college", college);
				session.setAttribute("username", user);
				session.setAttribute("code", code);
				response.sendRedirect("Profile");
			}

			//If the passwords do not match then send an error back to the LoginRegister.jsp page
			else{
				request.setAttribute("error","Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("LoginRegister.jsp");            
				rd.include(request, response);
			}
		}catch (Exception e) {
		}
	}
}