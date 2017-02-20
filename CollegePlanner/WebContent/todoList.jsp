<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ToDo List</title>
</head>
<body>

	<!--Create ToDo List -->

	<form action="ToDoListServlet" method="post">

		Add Task : <input type="text" id="title" name="title" /> Add
		Description : <input type="text" id="description" name="description" />

		<input type="submit" value="Save" />


	</form>
	

	<br>

	<!--Add item to ToDo List-->

	<%
		//Gets todo list
		List<String> task = (List<String>) session.getAttribute("myToDoList");
		
		//Creates new todo list
		if(task == null){
			task = new ArrayList<String>();
			session.setAttribute("myToDoList", task);
		}
		
		//Checks to see if form needs to be added
		String title = request.getParameter("title");
		if(title != null){
			task.add(title);
		}
	%>

	<!--Output tasks-->
	<hr>

	<b>To Do List</b>
	<br />
	
	<form action="ToDoListServlet" method="post" >
	    <input type="checkbox" name="box" value=done>
	</form>
	
	
	<ol>
		<%
	//Loops through list
		for(String temp : task){
			
			//Builds list
			out.println("<li>"+temp+"</li>");
		}
		
		
	%>
	

	
	

	</ol>

</body>
</html>