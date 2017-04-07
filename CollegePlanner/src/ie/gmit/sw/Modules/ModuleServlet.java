package ie.gmit.sw.Modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;


/**
 * @author Christopher Weir - G00309429, Gareth Lynskey - G00312651, Patrick Griffin - G00314635
 *
 */
public class ModuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();
	private double totalAverage = 0;
	private double avg = 0;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * doGet() retrieves all module data from the database, calculates the users GPA, and passed the data to the Modules.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get a handle on the session
			HttpSession session = request.getSession();

			//Get the users authentication code
			String code = (String)session.getAttribute("code");

			//Create a new instance of ModuleData and ModuleGroup
			ModuleData moduleData = new ModuleData();
			ModuleGroup modules = new ModuleGroup();

			//Get the Users Modules from the database
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList<String[]> moduleGradesList = (ArrayList<String[]>) mongo.getModuleGrades(code);
			
			//Create a new HashMap to store the module title and module average
			HashMap<String, Double> hm = getAverage(moduleList, moduleGradesList);
			
			//Reset average variable
			avg = 0;
			
			//For every String s in the HashMap
			for(String s: hm.keySet()){
				avg += hm.get(s);
			}
			//totalaverage is the average divided by the number of modules in the HashMap
			totalAverage = avg/hm.size();
			//Variable for loop
			int i = 0;

			//For every String m in moduleList
			for(String [] m : moduleList){
				//If the Module is in the HashMap
				if(hm.containsKey(m[0])){
					//Set the average
					double average = hm.get(m[0]);
					i++;

					//Create a new instance of Module and add the values from the array
					Module module = new Module(m[0], m[1], average, i);

					//Add the Module to the Module Group
					modules.addModule(module);
				}
			}

			//For every String g in the moduleGradesList
			for(String [] g : moduleGradesList){
				//Set the grade
				double grade =  Integer.parseInt(g[3])/100.00d*Integer.parseInt(g[4]);

				//Create a new instance of ModuleDetails and add the values from the array
				ModuleDetails mod = new ModuleDetails(g[0], g[1], g[2], Integer.parseInt(g[3]), Integer.parseInt(g[4]), grade);

				//Add the module to the Module Data list
				moduleData.addModule(mod);
			}

			//Set the session attributes
			request.getSession().setAttribute("average", totalAverage);
			request.getSession().setAttribute("modules", modules);
			request.getSession().setAttribute("moduleData", moduleData);

			//Forward the Modules.jsp page to the client
			RequestDispatcher rd = request.getRequestDispatcher("Modules.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	/**
	 * getAverage() populates the HashMap with the module title and the average.
	 * 
	 * @param moduleList
	 * @param moduleGradesList
	 * @return HashMap
	 */
	private HashMap<String, Double> getAverage(ArrayList<String[]> moduleList, ArrayList<String[]> moduleGradesList) {
		HashMap<String, Double> hm= new HashMap<String, Double>();
		for(String [] m : moduleList){
			hm.put(m[0],0.0);
		}

		for(String [] g : moduleGradesList){
			double grade =  Integer.parseInt(g[3])/100.00d*Integer.parseInt(g[4]);
			if(hm.containsKey(g[0])){
				hm.put(g[0], hm.get(g[0]) + grade);
			}
		}
		return hm;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * doPost() checks which button has been pressed by the user and calls the corresponding method for each.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get a handle on the session
			HttpSession session = request.getSession();

			//Get the users authentication code
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
			//If the Delete Module button was pressed do the following
			else if(buttonPressed.equals("DeleteModule")){
				deleteModule(code, request, response);
			}
			//Else the Delete Grade button was pressed do the following
			else{
				deleteGrade(code,request,response);
			}
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	/**
	 * deleteGrade() retrieves the grade data from the jsp and passes the data to MongoConnection to be removed.
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void deleteGrade(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String moduleTitle = request.getParameter("deleteGradeModule");
		String gradeTitle = request.getParameter("deleteGradeTitle");
		String gradeDate = request.getParameter("deleteGradeDate");
		String gradeValue = request.getParameter("deleteGradeValue");
		String gradeResult = request.getParameter("deleteGradeResult");

		mongo.deleteGrade(code, moduleTitle, gradeTitle, gradeDate, gradeValue, gradeResult);

		//Call the Modules class to reload the jsp page
		response.sendRedirect("Modules");
	}

	/**
	 * deleteModule() retrieves the module data from the jsp and passes the data to MongoConnection to be removed.
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void deleteModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Module deleteModule = new Module(request.getParameter("deleteModuleTitle"), request.getParameter("deleteModuleLecturer"), 0, 0);
		mongo.deleteModule(code, deleteModule);

		//Call the Grades class to reload the jsp page
		response.sendRedirect("Modules");
	}

	/**
	 * createGrade() retrieves the grade data from the jsp and passes the data to MongoConnection to be added.
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void createGrade(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String moduleTitle = request.getParameter("gradeModuleTitle");
		String title = request.getParameter("gradeTitle");
		String date = request.getParameter("gradeDate");
		String value = request.getParameter("gradeValue");
		String result = request.getParameter("gradeResult");
		mongo.setModuleGrades(code, moduleTitle, title, date, value, result);

		//Call the Grades class to reload the jsp page
		response.sendRedirect("Modules");
	}

	/**
	 * createModule() retrieves the module data from the jsp and passes the data to MongoConnection to be added 
	 * if the module does not already exist. 
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void createModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			ArrayList<String[]> moduleList = (ArrayList<String[]>) mongo.getModules(code);
			ArrayList ifExists = new ArrayList<>();
			for(String [] m : moduleList){
				ifExists.add(m[0]);
			}

			//Get the module title and lecturer
			String title = request.getParameter("moduleTitle");
			String lecturer = request.getParameter("lecturer");

			if(ifExists.contains(title)){
				request.setAttribute("error","Module Already Exists!");
				RequestDispatcher rd=request.getRequestDispatcher("Modules.jsp");            
				rd.include(request, response);
			}else{
				//Add the new module to the database
				mongo.setModule(code, title, lecturer);
				response.sendRedirect("Modules");
			}
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}
}