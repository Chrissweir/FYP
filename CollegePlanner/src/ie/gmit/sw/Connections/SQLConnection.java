package ie.gmit.sw.Connections;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ie.gmit.sw.Login.LoginValues;

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
}