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
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try{
			// Connect to MongoDB on localhost
			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			DB database = mongoClient.getDB("College");
			Map<String, Object> commandArguments = new BasicDBObject();
			commandArguments.put("createUser", username);
			commandArguments.put("pwd", password);
			String[] roles = { "readWrite" };
			commandArguments.put("roles", roles);
			BasicDBObject command = new BasicDBObject(commandArguments);
			database.command(command);
		}
		catch(Exception e)
		{

		}
		
		response.sendRedirect("Login.jsp");
	}
}
