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
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String college;
	private String code;
	private SecureRandom random = new SecureRandom();
	private boolean userAvailable = true;
	private boolean emailAvailable = true;

	
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
			firstname = request.getParameter("firstname");
			lastname = request.getParameter("lastname");
			email = request.getParameter("email");
			college = request.getParameter("college");
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			//Create a new Session Id
			code =  nextSessionId();

			//Establish a connection with the database
			Class.forName("org.postgresql.Driver");
			Connection connection = getConnection();
			
			//Create three new statements
			Statement stmt = connection.createStatement();
			Statement stmt1 = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			
			//Execute a query on the stmt1 statement and assign the results to the ResultSet ru
			ResultSet ru = stmt1.executeQuery("SELECT * FROM Users WHERE username='"+username+"';");
			//Execute a query on the stmt2 statement and assign the results to the ResultSet re
			ResultSet re = stmt2.executeQuery("SELECT * FROM Users WHERE email='"+email+"';");
			
			//Clear the queries in the statements
			stmt1.clearBatch();
			stmt2.clearBatch();
			
			//Reset userAvailable and emailAvailable to true
			userAvailable=true;
			emailAvailable = true;
			
			//Check if the ResultSet ru contains results
			//If it does then set userAvailable to false
			if(ru.isBeforeFirst()){
				userAvailable = false;
			}
			
			//Check if the ResultSet re contains results
			//If it does then set emailAvailable to false
			if(re.isBeforeFirst()){
				emailAvailable = false;
			}
			
			//If both userAvailable and emailAvailable are true, then add the users details to the database
			//and redirect the user to the LoginRegister.jsp page.
			if(userAvailable == true && emailAvailable == true){
				String sql = "INSERT INTO Users (first_name, last_name, email, college, username, password, confirmation_code) "
						+"VALUES ('"+firstname+"', '"+lastname+"', '"+email+"', '"+college+"', '"+username+"', '"+password+"', '"+code+"')";
				stmt.executeQuery(sql);
				connection.close();
				response.sendRedirect("/LoginRegister.jsp");
			}
			
			//If userAvailable is false then set an error in the request to inform the user
			if(userAvailable == false){
				request.setAttribute("userError","Username Already Registered!");
			}
			
			//If emailAvailable is false then set an error in the request to inform the user
			if(emailAvailable == false){
				request.setAttribute("emailError","Email Already Registered!");
			}
			
			//Forward the error messages to the LoginRegister.jsp for the user to see.
            request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
		}
		catch (Exception e) {
		}
	}
}







		/*public BasicDBObject[] createUserData(){

			BasicDBObject userDetails = new BasicDBObject();

			userDetails.put("Username", username);
			userDetails.put("Password", password);
			userDetails.put("First Name", firstname);
			userDetails.put("Last Name", lastname);
			userDetails.put("Email", email);
			userDetails.put("College", college);
			userDetails.put("Confirmation Code", code);

			final BasicDBObject[] data = {userDetails};

			return data;
		}
		
		
		final BasicDBObject[] data = createUserData();
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		user.createIndex(data.toString(), {unique:true});
		user.insert(data);*/
