package ie.gmit.sw.Modules;

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
import ie.gmit.sw.Timetable.Timetable;
import ie.gmit.sw.Timetable.TimetableModule;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/GradeServlet")
public class ModuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String code = (String)session.getAttribute("code");
			ModuleData moduleData = new ModuleData();
			ModuleGroup modules = new ModuleGroup();
			
			//Get the Users Modules and add each to the session attributes
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList<String[]> moduleGradesList = (ArrayList<String[]>) mongo.getModuleGrades(code);
			ArrayList<String> mlist = new ArrayList<String>();

			for(String [] m : moduleList){
				Module module = new Module(m[0], m[1]);
				modules.addModule(module);
			}
			
			/*for(String [] m : moduleList){
				ArrayList<String[]> moduleGradesList = (ArrayList<String[]>) mongo.getModuleGrades(code,m[0]);
				for(String [] g : moduleGradesList){
					ModuleDetails md = new ModuleDetails(m[0], m[1], g[0], g[1], Integer.parseInt(g[2]), Integer.parseInt(g[3]));
					module.addModule(md);
				}
			}*/

			for(String [] g : moduleGradesList){
				ModuleDetails mod = new ModuleDetails(g[0], g[1], g[2], Integer.parseInt(g[3]), Integer.parseInt(g[4]));
				moduleData.addModule(mod);
			}

			
			request.getSession().setAttribute("modules", modules);
			request.getSession().setAttribute("moduleData", moduleData);
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

			//If the Create Module button was pressed do the following
			if(buttonPressed.equals("CreateModule")){
				createModule(code, request, response);
			}
			//If the Create Grade button was pressed do the following
			else if(buttonPressed.equals("CreateGrade")){
					createGrade(code, request, response);
				}
			//Call the Grades class to reload the jsp page
			response.sendRedirect("Grades");
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	private void createGrade(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			String moduleTitle = request.getParameter("gradeModuleTitle");
			String title = request.getParameter("gradeTitle");
			String date = request.getParameter("gradeDate");
			String value = request.getParameter("gradeValue");
			String result = request.getParameter("gradeResult");
			mongo.setModuleGrades(code, moduleTitle, title, date, value, result);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	private void createModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			//Get the module title and lecturer
			String title = request.getParameter("moduleTitle");
			String lecturer = request.getParameter("lecturer");

			//Add the new module to the database
			mongo.setModule(code, title, lecturer);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

}
