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
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		mongo.setTodoList(code, title, description);
		
		request.getSession().setAttribute("title", title);
		request.getSession().setAttribute("desc", description);


		response.sendRedirect("todoList.jsp");
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String[] task = request.getParameterValues("box");
		//PrintWriter out = response.getWriter();
		//response.setContentType("todoList");
		 //ask database
		 //result = mongo.getTodoList():
		
	//	for(String t : task){
		//	String title = ("title");
			//out.write("<p>"+title+"</p>");
			//String desc = ("description");
			//out.write("<p>"+desc+"</p>");
			//out.write(t);
		//}
	     //out.write("<p>"+ task +"</p>");
	     //out.write("</body></html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}



}
