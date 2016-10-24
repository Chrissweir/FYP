package com.Register;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	  
		 username = request.getParameter("username");
		 password = request.getParameter("password");
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// get info from jsp file
		

		// Connect to MongoDB on localhost
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		// Set the database as the mongoDB College Database
		DB database = mongoClient.getDB("College");

		Map<String, Object> commandArguments = new BasicDBObject();
		commandArguments.put("createUser", username);
		commandArguments.put("pwd", password);
		String[] roles = { "readWrite" };
		commandArguments.put("roles", roles);

		// Select the collection "User" from the College database
		// DBCollection collection = database.getCollection("User");
		// Create a new Object called query
		BasicDBObject command = new BasicDBObject(commandArguments);
		database.command(command);
		// Set the key and value of the object to email: email

		// Search the User collection for the key and value in query
		// DBCursor cursor = collection.find(query);

		// Forward data from the query to "Welcome.jsp"

	}

}
