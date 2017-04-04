package ie.gmit.sw.Assignments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Modules.Module;
import ie.gmit.sw.Modules.ModuleGroup;

/**
 * @author Paul Dolan - G00297086
 * 
 * Servlet responsible for retrieving the users modules, assignments, calculating the users GPA and passing
 * the data to the Assignments.jsp page.
 *
 */
public class AssignmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get a handle on the session
			HttpSession session = request.getSession();
			
			//Get the users authentication code
			String code = (String)session.getAttribute("code");
			
			//Create a new instance of AssignmentData() and ModuleGroup()
			AssignmentData moduleAssignment = new AssignmentData();
			ModuleGroup modules = new ModuleGroup();

			//Get the Users Modules from the database
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList<String[]> moduleAssignmentsList = (ArrayList<String[]>) mongo.getModuleAssignments(code);

			//Variable for loop
			int i = 0;
			
			//For every String Array m in moduleList
			for(String [] m : moduleList){
				//Dummy value for Module
				double average = 2.5;
				
				//Create a new instance of Module and add the array values
				Module module = new Module(m[0], m[1], average, i);
				//Add the Module to the ModuleGroup
				modules.addModule(module);
				//Increment i
				i++;
			}

			//For every String Array ma in moduleAssignmentList
			for(String [] ma : moduleAssignmentsList){
				//Create a new instance of AssignmentDetails and add the array values
				AssignmentDetails assignment = new AssignmentDetails(ma[0], ma[1], ma[2], Integer.parseInt(ma[3]));

				//Add the Assignment to the AssignmentData
				moduleAssignment.addAssignment(assignment);
			}

			//Add the modules to the session attribute "modules"
			request.getSession().setAttribute("modules", modules);
			
			//Add the moduleAssignment to the session attribute "assignmentData"
			request.getSession().setAttribute("assignmentData", moduleAssignment);
			
			//Forward the Assignment.jsp page to the client
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

		//Get a handle on the session
		HttpSession session = request.getSession();
		
		//Get the users authentication code
		String code = (String)session.getAttribute("code");

		//Check which button was pressed
		String buttonPressed = request.getParameter("submitBtn");

		//If the Create Assignment button was pressed do the following
		if(buttonPressed.equals("CreateAssignment")){
			createAssignment(code, request, response);
		}
		//Else the Delete button was pressed
		else{
			deleteAssignment(code,request,response);
		}
		response.sendRedirect("Assignments");
	}

	/**
	 * deleteAssignment() retrieves the assignment details from the jsp and passes them
	 * to MongoConnection to be deleted.
	 * 
	 * @param code
	 * @param request
	 * @param response
	 */
	private void deleteAssignment(String code, HttpServletRequest request, HttpServletResponse response) {
		String moduleTitle = request.getParameter("deleteAssignmentModule");
		String title = request.getParameter("deleteAssignmentTitle");
		String date = request.getParameter("deleteAssignmentDate");
		String value = request.getParameter("deleteAssignmentValue");
		mongo.deleteAssignment(code, moduleTitle, title, date, value);
	}

	/**
	 * createAssignment() retrieves the assignment details from the jsp and passes them
	 * to MongoConnection to be added.
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void createAssignment(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String moduleTitle = request.getParameter("moduleAssignmentTitle");
		String title = request.getParameter("assignmentTitle");
		String date = request.getParameter("assignmentDate");
		String value = request.getParameter("assignmentValue");
		mongo.setModuleAssignment(code, moduleTitle, title, date, value);
	}
}