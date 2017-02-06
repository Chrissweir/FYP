package ie.gmit.sw.Profile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path;
	private String firstName;
	private String lastName;
	private String email;
	private String college;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null){
			RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
			rd.forward(request, response);	
		}
		try{
			String code = (String) session.getAttribute("code");
			System.out.println(code);
			MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
			MongoClient client = new MongoClient(uri);
			DB db = client.getDB(uri.getDatabase());
			DBCollection user = db.getCollection("User");
			BasicDBObject query = new BasicDBObject();
			//Set the key and value of the object to email: email
			query.put(code, null);
			//Search the User collection for the key and value in query
			DBCursor cursor = user.find(query);
			System.out.println("Getting image");
			//Convert query data to  a String
			while(cursor.hasNext()){
				String image =(String) cursor.next().get("Image");
				System.out.println("Image: " + image);
				session.removeAttribute("image");
				session.setAttribute("image", image);
			}
			RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
			rd.forward(request, response);	
			client.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = request.getParameter("imgPath");
		firstName = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		email = request.getParameter("email");
		college = request.getParameter("college");
		HttpSession session = request.getSession();

		String code = (String)session.getAttribute("code");
		try{
			
		//Establish a connection with the database
		Connection connection = getConnection();
		
		//Create three new statements
		PreparedStatement update = connection.prepareStatement
				("UPDATE Users SET first_name =?, last_name =?, email =?, college =? WHERE confirmation_code =?");
		update.setString(1, firstName);
		update.setString(2, lastName);
		update.setString(3, email);
		update.setString(4, college);
		update.setString(5, code);
		update.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		//System.out.println(code);
		final BasicDBObject[] data = createUserData(code, path);
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		BasicDBObject document = new BasicDBObject();
		document.put("Confirmation Code", code);
		user.remove(document);
		user.insert(data);
		client.close();
		session.setAttribute("firstname", firstName);
		session.setAttribute("lastname", lastName);
		session.setAttribute("email", email);
		session.setAttribute("college", college);
		session.removeAttribute("image");
		session.setAttribute("image", path);
		response.sendRedirect("Profile");
	}

	public BasicDBObject[] createUserData(String code, String encodstring){

		BasicDBObject ImageDetails = new BasicDBObject();

		ImageDetails.put("Confirmation Code", code);
		ImageDetails.put("Image", encodstring);

		final BasicDBObject[] data = {ImageDetails};

		return data;
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
}