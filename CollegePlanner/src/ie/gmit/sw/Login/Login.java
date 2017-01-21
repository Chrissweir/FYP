package ie.gmit.sw.Login;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.mongodb.*;
import com.mongodb.connection.QueryResult;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String queryResult;
	private String pass;
	private String code;
	
	private Connection getConnection() throws URISyntaxException, SQLException {
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		return DriverManager.getConnection(ConnectionString);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");	
			
			Class.forName("org.postgresql.Driver");
			System.out.println("Connecting");
			Connection connection = getConnection();
			System.out.println("Connected");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE username='"+username+"';" );
			while ( rs.next() ) {
				System.out.println("Getting data");
				pass = rs.getString("password");
				code = rs.getString("confirmation_code");
			}
			if(pass.equals(password)){
				request.setAttribute("data", code);
				request.getRequestDispatcher("Welcome.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error","Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("LoginRegister.jsp");            
				rd.include(request, response);
			}
			
			
			
			
			
			
			
			
			
			
			
			/*MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
			MongoClient client = new MongoClient(uri);
			DB db = client.getDB(uri.getDatabase());
			DBCollection user = db.getCollection("User");
			//Create a new Object called query
			BasicDBObject query = new BasicDBObject();
			//Set the key and value of the object to email: email
			query.put(username, null);
			//Search the User collection for the key and value in query
			DBCursor cursor = user.find(query);
			while(cursor.hasNext()) 
			{
				//Convert query data to  a String
				queryResult =cursor.next().toString();
				//Set the attribute "data" of the request with the value of the query String
				request.setAttribute("data", queryResult);
			}
			if (queryResult.contains(password)){
				//Forward data from the query to "Welcome.jsp"
				request.getRequestDispatcher("Welcome.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error","Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");            
				rd.include(request, response);
			}*/
		}
		catch(Exception e){

		}
	}
}




















/*//Take in email value from Login.jsp
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		//Connect to MongoDB on localhost
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		//Set the database as the mongoDB College Database
		DB database = mongoClient.getDB("College");
		//Select the collection "User" from the College database
		DBCollection collection = database.getCollection("User");
		//Create a new Object called query
		BasicDBObject query = new BasicDBObject();
		//Set the key and value of the object to email: email
		query.put("email", email);
		//Search the User collection for the key and value in query
	    DBCursor cursor = collection.find(query);
	    while(cursor.hasNext()) 
	    {
	    	//Convert query data to  a String
	    	String queryResult =cursor.next().toString();
	    	//Set the attribute "data" of the request with the value of the query String
	    	request.setAttribute("data", queryResult);
	    }
	    //Forward data from the query to "Welcome.jsp"
    	request.getRequestDispatcher("Welcome.jsp").forward(request, response);
	if(email.equals("Chris"))
		{
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("Welcome.jsp");
		}
		else
		{
			response.sendRedirect("Login.jsp");
		}*/