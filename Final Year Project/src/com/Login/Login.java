package com.Login;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.mongodb.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(username, "College", password.toCharArray());
			
			MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));
			
			DB mongoDB = mongoClient.getDB("College");
			
			DBCollection collection = mongoDB.getCollection("User");
			//Create a new Object called query
			BasicDBObject query = new BasicDBObject();
			//Set the key and value of the object to email: email
			query.put("email", "project@gmit.ie");
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