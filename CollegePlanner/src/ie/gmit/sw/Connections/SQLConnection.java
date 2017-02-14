package ie.gmit.sw.Connections;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ie.gmit.sw.Login.LoginValues;
import ie.gmit.sw.Register.RegisterUserDetails;

public class SQLConnection {
	public Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		//Establish a connection with the database
		Class.forName("org.postgresql.Driver");
		String ConnectionString ="jdbc:postgresql://ec2-54-75-239-190.eu-west-1.compute.amazonaws.com:5432/dc6f77btle9oe3?user=dmbleakzbhlbnl&password=b08ab093aa5b03c4047c541ceab2b23daa4fb5198e48d56f804319695455d754&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(ConnectionString);
	}

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
			login.setCode(rs.getString("confirmation_code"));
		}
		//Close the connection
		connection.close();
	}

	public String userRegistration(RegisterUserDetails userDetails) throws ClassNotFoundException, URISyntaxException, SQLException {
		//Establish a connection with the database
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
}