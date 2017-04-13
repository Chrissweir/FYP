package ie.gmit.sw.Todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;

/**
 * Servlet implementation class ToDoListServlet
 * 
 */
public class ToDoListServlet extends HttpServlet {
	private MongoConnection mongo = new MongoConnection();//Creating the mongo connection
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//Returns the current session associated with this request, or if the request does not have a session, creates one
		String code = (String) session.getAttribute("code");

		String buttonPressed = request.getParameter("btn");

		//If save button is pressed
		if(buttonPressed.equals("Save"))
		{
			String title = request.getParameter("title");//Returns title
			String description = request.getParameter("description");//Returns description

			mongo.setTodoList(code, title, description);//Retrieves code, title and description from mongo

			request.getSession().setAttribute("title", title);
			request.getSession().setAttribute("desc", description);
			doGet(request, response);
			
		}else if(buttonPressed.equals("Delete")){//If delete button is pressed
			String title = request.getParameter("deleteTaskTitle");//Delete title
			String description = request.getParameter("deleteTaskDescription");//Delete description
			
			mongo.deleteCompletedTask(code, title, description);//Telling mongo to delete from database
			doGet(request, response);
			
		}else if(buttonPressed.equals("Transfer")){//If transfer button is pressed
			String title = request.getParameter("moveTaskTitle");//Move title
			String description = request.getParameter("moveTaskDescription");//Move description
			System.out.println(title);//Output title
			mongo.deleteCompletedTask(code, title, description);//Deletes completed task completed tasks in mongo
			mongo.setTodoList(code, title, description);//Moves back to to do list
			doGet(request, response);
		}
		else{
			String title = request.getParameter("taskTitle");//Return title 
			String desc = request.getParameter("taskDescription");//Return description

			deleteTask(code, title, desc);//Deletes from task completed
			mongo.setTaskCompleted(code, title, desc); //Deletes from task completed database 
			doGet(request, response);
		}
	}

	/**
	 * @param code
	 * @param title
	 * @param desc
	 * 
	 */
	private void deleteTask(String code, String title, String desc) {
		mongo.taskCompleted(code, title, desc);

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();//Gets a handle on the session

		ArrayList<String[]> list = new ArrayList<String[]>();
		String code = (String) session.getAttribute("code");
		list = (ArrayList<String[]>) mongo.getTodoList(code);

		ToDo todo = new ToDo();//Creating a new instance of Todo
		for(String[] r : list){
			Task task = new Task(r[0], r[1]);
			todo.addTask(task);
		}

		//Creates empty array list called listCompleted
		ArrayList<String[]> listCompleted = new ArrayList<String[]>();
		//Passes data from mongo to listCompleted
		listCompleted = (ArrayList<String[]>) mongo.getTaskCompleted(code);

		ToDo todoCompleted = new ToDo();//Creating a new instance of todoCompleted
		
		for(String[] s : listCompleted){
			Task taskCompleted = new Task(s[0], s[1]);
			todoCompleted.addTask(taskCompleted);
		}
		request.getSession().setAttribute("todolist", todo);
		request.getSession().setAttribute("todolistCompleted", todoCompleted);
		RequestDispatcher rd = request.getRequestDispatcher("TodoList.jsp");
		rd.forward(request, response);
	}
}