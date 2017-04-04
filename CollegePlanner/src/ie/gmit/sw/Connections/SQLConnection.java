package ie.gmit.sw.Connections;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ie.gmit.sw.Login.LoginValues;
import ie.gmit.sw.Profile.UserDetails;
import ie.gmit.sw.Register.RegisterUserDetails;
import ie.gmit.sw.Security.AccountRecoveryDetails;

/**
 * @author Christopher Weir - G00309429
 * 
 * SQLConnection handles the connection to the postgres database hosted on Heroku. 
 *
 */
public class SQLConnection {
	/**
	 * getConnection() gets the connection to the database.
	 * 
	 * @return the connection
	 * @throws URISyntaxException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		//Establish a connection with the database
		Class.forName("org.postgresql.Driver");
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(ConnectionString);
	}

	/**
	 * userLogin() retrieves the user details if the exist.
	 * 
	 * @param login
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public void userLogin(LoginValues login) throws ClassNotFoundException, URISyntaxException, SQLException{
		//Establish a connection with the database
		Connection connection = getConnection();

		//Create a new statement
		Statement stmt = connection.createStatement();

		//Execute a query on the statement and assign the results to the ResultSet rs
		ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE username='"+login.getUsername()+"';" );

		//Using a while loop, for every entry in the ResultSet retrieve the specified data
		while ( rs.next() ) {
			login.setFirstname(rs.getString("first_name"));
			login.setLastname(rs.getString("last_name"));
			login.setEmail(rs.getString("email"));
			login.setCollege(rs.getString("college"));
			login.setUser(rs.getString("username"));
			login.setPass(rs.getString("password"));
			login.setCourse(rs.getString("course"));
			login.setBio(rs.getString("bio"));
			login.setCode(rs.getString("confirmation_code"));
		}
		//Close the connection
		connection.close();
	}

	/**
	 * userRegistration() add user details to the database.
	 * 
	 * @param userDetails
	 * @return
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
	public String userRegistration(RegisterUserDetails userDetails) throws ClassNotFoundException, URISyntaxException, SQLException {
		//Establish a connection with the database
		Connection connection = getConnection();

		//Create three new statements
		Statement stmt = connection.createStatement();
		Statement stmt1 = connection.createStatement();
		Statement stmt2 = connection.createStatement();
		//stmt.executeUpdate("CREATE TABLE Users (first_name VARCHAR(255),last_name VARCHAR(255),email VARCHAR(255) UNIQUE,college VARCHAR(255),username VARCHAR(255) UNIQUE,password VARCHAR(255), course VARCHAR(255), bio VARCHAR(255),confirmation_code VARCHAR(255) UNIQUE)");
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
			
			String sql = "INSERT INTO Users (first_name, last_name, email, college, username, password, course, bio, confirmation_code) "
					+"VALUES ('"+userDetails.getFirstname()+"', '"+userDetails.getLastname()+"', '"+userDetails.getEmail()+"', '"+userDetails.getCollege()+"', '"+userDetails.getUsername()+"', '"+userDetails.getPassword()+"', '"+userDetails.getCourse()+"', '"+userDetails.getBio()+"', '"+userDetails.getCode()+"')";
			stmt.executeUpdate(sql);
			connection.close();
			return "Profile";
		}
		
		//If userAvailable is false then set an error in the request to inform the user
		if(userDetails.isUserAvailable() == false){
			return "userError";
		}
		
		//If emailAvailable is false then set an error in the request to inform the user
		if(userDetails.isEmailAvailable() == false){
			return "emailError";
		}
		return null;
	}

	/**
	 * updateUserDetails() edits user details on the database.
	 * 
	 * @param userDetails
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public void updateUserDetails(UserDetails userDetails) throws SQLException, ClassNotFoundException, URISyntaxException{
		//Establish a connection with the database
		Connection connection = getConnection();

		//Create three new statements
		PreparedStatement update = connection.prepareStatement
				("UPDATE Users SET first_name =?, last_name =?, email =?, college =?, course =?, bio =? WHERE confirmation_code =?");
		update.setString(1, userDetails.getFirstName());
		update.setString(2, userDetails.getLastName());
		update.setString(3, userDetails.getEmail());
		update.setString(4, userDetails.getCollege());
		update.setString(5, userDetails.getCourse());
		update.setString(6, userDetails.getBio());
		update.setString(7, userDetails.getCode());
		update.executeUpdate();
		
		connection.close();
	}
	
	/**
	 * removeUser() removes user details from the database.
	 * 
	 * @param userDetails
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public boolean removeUser(UserDetails userDetails) throws SQLException, ClassNotFoundException, URISyntaxException{
		//Establish a connection with the database
		Connection connection = getConnection();
		//Create a new statement
		Statement stmt = connection.createStatement();

		//Execute a query on the statement and assign the results to the ResultSet rs
		ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE confirmation_code='"+userDetails.getCode()+"';" );

		//Using a while loop, for every entry in the ResultSet retrieve the specified data
		while ( rs.next() ) {
			userDetails.setPass(rs.getString("password"));
		}
		
		//User validation, check if the password that was submitted is the same and the password
		//retrieved from the database. If it is then pass the specified data to the request object
		//and forward the request to the Profile.jsp page
		if(userDetails.getPass().equals(userDetails.getPassword())){
			String query = "Delete FROM Users WHERE confirmation_code = ?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, userDetails.getCode());

			// execute the preparedstatement
			preparedStmt.execute();
			connection.close();
			return true;
		}
		return false;
	}

	/**
	 * checkUser() checks if a user exits on the database.
	 * 
	 * @param ar
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public boolean checkUser(AccountRecoveryDetails ar) throws SQLException, ClassNotFoundException, URISyntaxException {
		//Establish a connection with the database
		Connection connection = getConnection();

		//Create a new statement
		Statement stmt = connection.createStatement();

		//Execute a query on the statement and assign the results to the ResultSet rs
		ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE username='"+ar.getUsername()+"' AND email ='"+ar.getEmail()+"';" );

		//Using a while loop, for every entry in the ResultSet retrieve the specified data
		while ( rs.next() ) {
			return true;
		}
		//Close the connection
		connection.close();
		return false;
	}

	/**
	 * resetPassword() changes a users password on the database.
	 * 
	 * @param password
	 * @param ar
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public void resetPassword(String password, AccountRecoveryDetails ar) throws SQLException, ClassNotFoundException, URISyntaxException {
		//Establish a connection with the database
		Connection connection = getConnection();

		//Create three new statements
		PreparedStatement update = connection.prepareStatement
				("UPDATE Users SET password =? WHERE username =? AND email =?");
		update.setString(1, password);
		update.setString(2, ar.getUsername());
		update.setString(3, ar.getEmail());
		update.executeUpdate();
		
		//Close the connection
		connection.close();
	}
}