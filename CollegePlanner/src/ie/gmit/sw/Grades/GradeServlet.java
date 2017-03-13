package ie.gmit.sw.Grades;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Timetable.Module;
import ie.gmit.sw.Timetable.Timetable;
import ie.gmit.sw.Timetable.TimetableModule;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/GradeServlet")
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();
	private Module newClass = new Module();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("code");
			Timetable timetable = new Timetable();//create an instance of Timetable

			//Get the Users Modules and add each to the session attributes
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList<String> mlist = new ArrayList<String>();
			for(String [] m : moduleList){
				mlist.add(m[0]);
			}
			request.getSession().setAttribute("moduleList", mlist);
			RequestDispatcher rd = request.getRequestDispatcher("Grades.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//Get a handle on the session
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("code");

			//Check which button was pressed
			String buttonPressed = request.getParameter("submitBtn");

			//If the Create button was pressed do the following
			if(buttonPressed.equals("CreateModule")){
				createModule(code, request, response);
			}
			//Call the Grades class to reload the jsp page
			response.sendRedirect("Grades");
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	private void createModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {

		try{
			//Get the module title and lecturer
			newClass.setTitle(request.getParameter("moduleTitle"));
			newClass.setLecturer(request.getParameter("lecturer"));

			//Add the new module to the database
			mongo.setModule(code, newClass);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

}
