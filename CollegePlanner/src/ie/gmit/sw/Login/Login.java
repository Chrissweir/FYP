package ie.gmit.sw.Login;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * @author Christopher Weir - G00309429
 * 
 * This class is responsible for allowing the user to login to the website by 
 * first retrieving the username and password that was entered. The class then establishes 
 * a connection with the postgres SQL database hosted on Heroku. A database query is made to 
 * check if the user exists and if it does then check if the password matches the users password.
 * Once confirmed, the users data is passed into the request object and forwarded to the Welcome.jsp
 * page.
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
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
	private Connection getConnection() throws URISyntaxException, SQLException {
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(ConnectionString);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/**
	 * doGet() handles the request from the LoginRegister.jsp page by retrieving 
	 * the username and password that was submitted and using them to query the database
	 * and pass the data to the Welcome.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			// Retrieve the username and password that was submitted
			username = request.getParameter("username");
			password = request.getParameter("password");	

			//Establish a connection with the database
			Class.forName("org.postgresql.Driver");
			Connection connection = getConnection();

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
			//and forward the request to the Welcome.jsp page
			if(pass.equals(password)){
				request.setAttribute("firstname", firstname);
				request.setAttribute("lastname", lastname);
				request.setAttribute("email", email);
				request.setAttribute("college	", college);
				request.setAttribute("username", user);
				request.setAttribute("data", code);
				request.getRequestDispatcher("Welcome.jsp").forward(request, response);
			}
			
			//If t he passwords do not match then send an error back to the LoginRegister.jsp page
			else{
				request.setAttribute("error","Invalid Username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("LoginRegister.jsp");            
				rd.include(request, response);
			}
		}catch (Exception e) {
		}
	}
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
				RequestDispatcher rd=request.getRequestDispatcher("LoginRegister.jsp");            
				rd.include(request, response);
			}*/




















/*//Take in email value from LoginRegister.jsp
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
			response.sendRedirect("LoginRegister.jsp");
		}*/