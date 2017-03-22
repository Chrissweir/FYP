package ie.gmit.sw.Assignments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Modules.Module;
import ie.gmit.sw.Modules.ModuleData;
import ie.gmit.sw.Modules.ModuleDetails;
import ie.gmit.sw.Modules.ModuleGroup;

/**
 * Servlet implementation class AssignmentsServlet
 */
public class AssignmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("code");
			AssignmentData moduleAssignment = new AssignmentData();
			ModuleGroup modules = new ModuleGroup();

			//Get the Users Modules and add each to the session attributes
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList<String[]> moduleAssignmentsList = (ArrayList<String[]>) mongo.getModuleAssignments(code);

			for(String [] m : moduleList){
				double average = 2.5;
				Module module = new Module(m[0], m[1], average);
				modules.addModule(module);
			}

			for(String [] g : moduleAssignmentsList){
				AssignmentDetails assignment = new AssignmentDetails(g[0], g[1], g[2], Integer.parseInt(g[3]));

				moduleAssignment.addAssignment(assignment);
			}

			request.getSession().setAttribute("modules", modules);
			request.getSession().setAttribute("assignmentData", moduleAssignment);
			RequestDispatcher rd = request.getRequestDispatcher("Assignments.jsp");
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

			//If the Create Assignment button was pressed do the following
			if(buttonPressed.equals("CreateAssignment")){
				createAssignment(code, request, response);
			}
			else{
				deleteAssignment(code,request,response);
			}
			response.sendRedirect("Assignments");
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	private void deleteAssignment(String code, HttpServletRequest request, HttpServletResponse response) {
		String moduleTitle = request.getParameter("deleteAssignmentModule");
		String title = request.getParameter("deleteAssignmentTitle");
		String date = request.getParameter("deleteAssignmentDate");
		String value = request.getParameter("deleteAssignmentValue");
		mongo.deleteAssignment(code, moduleTitle, title, date, value);
	}

	private void createAssignment(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			String moduleTitle = request.getParameter("moduleAssignmentTitle");
			String title = request.getParameter("assignmentTitle");
			String date = request.getParameter("assignmentDate");
			String value = request.getParameter("assignmentValue");
			mongo.setModuleAssignment(code, moduleTitle, title, date, value);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}
}