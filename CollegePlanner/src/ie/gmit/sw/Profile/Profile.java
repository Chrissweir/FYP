package ie.gmit.sw.Profile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Connections.SQLConnection;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDetails userDetails = new UserDetails();
	private MongoConnection mongo = new MongoConnection();
	private SQLConnection sqlConn =new SQLConnection();
	private String image;
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
			image = mongo.getUserImage(code);
			session.removeAttribute("image");
			session.setAttribute("image", image);
			RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
			rd.forward(request, response);	
		}catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonPressed = request.getParameter("btn");
		if (buttonPressed != null && buttonPressed.equals("delete")) {
			removeAccount(request, response);
		} else if (buttonPressed.equals("update")) {
			userDetails.setPath(request.getParameter("imgPath"));
			userDetails.setFirstName(request.getParameter("firstname"));
			userDetails.setLastName(request.getParameter("lastname"));
			userDetails.setEmail(request.getParameter("email"));
			userDetails.setCollege(request.getParameter("college"));
			HttpSession session = request.getSession();

			userDetails.setCode((String)session.getAttribute("code"));
			
			try{
				sqlConn.updateUserDetails(userDetails);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			mongo.setUserData(userDetails.getCode(), userDetails.getPath());
			session.setAttribute("firstname", userDetails.getFirstName());
			session.setAttribute("lastname", userDetails.getLastName());
			session.setAttribute("email", userDetails.getEmail());
			session.setAttribute("college", userDetails.getCollege());
			session.removeAttribute("image");
			session.setAttribute("image", userDetails.getPath());
			response.sendRedirect("Profile");
		}
	}

	private void removeAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("code");

			//Establish a connection with the database
			Connection connection = getConnection();
			//Create a new statement
			Statement stmt = connection.createStatement();

			//Execute a query on the statement and assign the results to the ResultSet rs
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE confirmation_code='"+code+"';" );

			//Using a while loop, for every entry in the ResultSet retrieve the specified data
			while ( rs.next() ) {
				userDetails.setPass(rs.getString("password"));
			}
			userDetails.setPassword(request.getParameter("confirmPassword"));
			//User validation, check if the password that was submitted is the same and the password
			//retrieved from the database. If it is then pass the specified data to the request object
			//and forward the request to the Profile.jsp page
			if(userDetails.getPass().equals(userDetails.getPassword())){
				String query = "Delete FROM Users WHERE confirmation_code = ?";
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, code);

				// execute the preparedstatement
				preparedStmt.execute();

				connection.close();

				mongo.removeUserData(code);

				session.removeAttribute("firstname");
				session.removeAttribute("lastname");
				session.removeAttribute("email");
				session.removeAttribute("college");
				session.removeAttribute("image");
				session.removeAttribute("code");
				session.invalidate();
				response.sendRedirect("Logout");
			}
			//If t he passwords do not match then send an error back to the LoginRegister.jsp page
			else{
				request.setAttribute("error","Invalid Password");
				RequestDispatcher rd=request.getRequestDispatcher("Profile.jsp");            
				rd.include(request, response);
			}
		}
		catch (Exception e)
		{
			//
		}
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
}//219