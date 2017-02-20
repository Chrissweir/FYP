package ie.gmit.sw.Todo;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;

/**
 * Servlet implementation class ToDoListServlet
 */
@WebServlet("/ToDoListServlet")
public class ToDoListServlet extends HttpServlet {
	private MongoConnection mongo = new MongoConnection();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		System.out.println("Title: " + title);
		System.out.println("Description: " + description);
		
		
		
		
		response.sendRedirect("todoList.jsp");
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 //ask database
		 //result = mongo.getTodoList():
	  //    out.write("<p>"+ title +"</p>");
	      out.write("</body></html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}



}
