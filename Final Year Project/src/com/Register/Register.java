package com.Register;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try{
			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			MongoCredential mongoCredential = MongoCredential.createMongoCRCredential("Admin", "College", "password".toCharArray());
			
			MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));
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
