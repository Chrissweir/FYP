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
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");

		String buttonPressed = request.getParameter("btn");

		if(buttonPressed.equals("Save"))
		{
			String title = request.getParameter("title");
			String description = request.getParameter("description");

			mongo.setTodoList(code, title, description);

			request.getSession().setAttribute("title", title);
			request.getSession().setAttribute("desc", description);
			response.sendRedirect("ToDoList");
		}else if(buttonPressed.equals("Delete")){
			String title = request.getParameter("deleteTaskTitle");
			String description = request.getParameter("deleteTaskDescription");
			
			mongo.deleteCompletedTask(code, title, description);
			response.sendRedirect("ToDoList");
			
		}else if(buttonPressed.equals("Transfer")){
			String title = request.getParameter("moveTaskTitle");
			String description = request.getParameter("moveTaskDescription");
			System.out.println(title);
			mongo.deleteCompletedTask(code, title, description);
			mongo.setTodoList(code, title, description);
			response.sendRedirect("ToDoList");
		}
		else{
			String title = request.getParameter("taskTitle");
			String desc = request.getParameter("taskDescription");

			deleteTask(code, title, desc);
			mongo.setTaskCompleted(code, title, desc);
			response.sendRedirect("ToDoList");
		}
	}

	private void deleteTask(String code, String title, String desc) {
		mongo.taskCompleted(code, title, desc);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get a handle on the session
		HttpSession session = request.getSession();

		ArrayList<String[]> list = new ArrayList<String[]>();
		String code = (String) session.getAttribute("code");
		list = (ArrayList<String[]>) mongo.getTodoList(code);

		ToDo todo = new ToDo();
		for(String[] r : list){
			Task task = new Task(r[0], r[1]);
			todo.addTask(task);
		}

		ArrayList<String[]> listCompleted = new ArrayList<String[]>();
		listCompleted = (ArrayList<String[]>) mongo.getTaskCompleted(code);

		ToDo todoCompleted = new ToDo();
		for(String[] s : listCompleted){
			Task taskCompleted = new Task(s[0], s[1]);
			todoCompleted.addTask(taskCompleted);
		}
		request.getSession().setAttribute("todolist", todo);
		request.getSession().setAttribute("todolistCompleted", todoCompleted);
		response.sendRedirect("todoList.jsp");
	}
}