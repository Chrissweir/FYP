<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ToDo List</title>
</head>
<body>

	<!--Create ToDo List -->
	
	<form action="ToDoListServlet" method="post">
	
		Add Task : <input type="text" id="theItem" name="theItem" />
		
		<input type="submit" value="Submit" />
		
	
	</form>
	<br>

	<!--Add item to ToDo List-->
	
	<%
		//Gets todo list
		List<String> items = (List<String>) session.getAttribute("myToDoList");
		
		//Creates new todo list
		if(items == null){
			items = new ArrayList<String>();
			session.setAttribute("myToDoList", items);
		}
		
		//Checks to see if form needs to be added
		String theItem = request.getParameter("theItem");
		if(theItem != null){
			items.add(theItem);
		}
	%>

	<!--Output tasks-->
	<hr>
	
	<b>To Do List</b> <br/>
	
	<ol>
	<%
	//Loops through list
		for(String temp : items){
			
			//Builds list
			out.println("<li>"+temp+"</li>");
		}
	%>
	
	


</ol>

</body>
</html>