package ie.gmit.sw.Register;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mongodb.*;

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


	public BasicDBObject[] createUserData(){

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

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	private Connection getConnection() throws URISyntaxException, SQLException {
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		return DriverManager.getConnection(ConnectionString);
	}
	// IF USERNAME EMAIL OR CODE ALREADY IN DATABASE THEN ERROR--CREATE IF STATEMENT
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		try {
			firstname = request.getParameter("firstname");
			lastname = request.getParameter("lastname");
			email = request.getParameter("email");
			college = request.getParameter("college");
			username = request.getParameter("username");
			password = request.getParameter("password");
			code =  nextSessionId();

			Class.forName("org.postgresql.Driver");
			System.out.println("Connecting");
			Connection connection = getConnection();
			System.out.println("Connected");
			Statement stmt = connection.createStatement();
			Statement stmt1 = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			//stmt.executeUpdate("DROP TABLE Users");
			//stmt.executeUpdate("CREATE TABLE Users (first_name VARCHAR(255),last_name VARCHAR(255),email VARCHAR(255) UNIQUE,college VARCHAR(255),username VARCHAR(255) UNIQUE,password VARCHAR(255),confirmation_code VARCHAR(255) UNIQUE)");
			String checkUser = "SELECT * FROM Users WHERE username='"+username+"'";
			String checkEmail = "SELECT * FROM Users WHERE email='"+email+"'";
			ResultSet ru = stmt1.executeQuery("SELECT * FROM Users WHERE username='"+username+"';");
			ResultSet re = stmt2.executeQuery("SELECT * FROM Users WHERE email='"+email+"';");
			stmt1.clearBatch();
			stmt2.clearBatch();
			System.out.println("CHECKED");
			userAvailable=true;
			emailAvailable = true;
			if(ru.isBeforeFirst()){
				userAvailable = false;
			}
			System.out.println("Checked User");
			if(re.isBeforeFirst()){
				emailAvailable = false;
			}
			
			if(userAvailable == true && emailAvailable == true){
				System.out.println("PASSED");
				String sql = "INSERT INTO Users (first_name, last_name, email, college, username, password, confirmation_code) "
						+"VALUES ('"+firstname+"', '"+lastname+"', '"+email+"', '"+college+"', '"+username+"', '"+password+"', '"+code+"')";
				stmt.executeQuery(sql);
				System.out.println("Inserted");
				response.sendRedirect("/LoginRegister.jsp");
			}
			if(userAvailable == false){
				System.out.println("Username error");
				request.setAttribute("userError","Username Already Registered!");
			}
			if(emailAvailable == false){
				System.out.println("Email error");
				request.setAttribute("emailError","Email Already Registered!");
			}
            // get back to order.jsp page using forward
            request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
			/*ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
			while ( rs.next() ) {
				System.out.println("Getting data");
				int id = rs.getInt("id");
				String name = rs.getString("email");
				System.out.println( "ID = " + id );
				System.out.println( "NAME = " + name );
				System.out.println();
			}*/

		}
		catch (Exception e) {
			// TODO: handle exception
		}

		/*final BasicDBObject[] data = createUserData();
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		user.createIndex(data.toString(), {unique:true});
		user.insert(data);*/
	}
}