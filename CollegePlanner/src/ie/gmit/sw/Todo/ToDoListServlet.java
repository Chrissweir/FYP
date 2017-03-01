package ie.gmit.sw.Todo;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Calendar.CalendarValues;
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


		response.sendRedirect("ToDoList");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> l = new ArrayList<String>();
		ArrayList<String[]> list = new ArrayList<String[]>();
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		list = (ArrayList<String[]>) mongo.getTodoList(code);
		int i =0;
		int j = 1;
		for(String[] r : list){
			System.out.println(r[0] + " " + r[1]);
			l.add(r[0]);
			l.add(r[1]);
		}
		request.getSession().setAttribute("todolist", l);
		response.sendRedirect("todoList.jsp");
	}
}