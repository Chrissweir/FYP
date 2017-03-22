package ie.gmit.sw.Timetable;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Modules.ModuleDetails;

public class TimetableServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private MongoConnection mongo = new MongoConnection();

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

			//Create an ArrayList of type String[] called list and give it the return value of 
			//mongo.getTimetable and cast it to an ArrayList of type String[]
			ArrayList<String[]> list = (ArrayList<String[]>) mongo.getTimetable(code);
			//for each String[] in the list
			for(String[] s : list){
				///create an instance of module with the given parameters
				TimetableModule module = new TimetableModule(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4]);
				timetable.addClass(module);//add a module to the timetable
			}
			request.getSession().setAttribute("timetable", timetable);
			RequestDispatcher rd = request.getRequestDispatcher("Timetable.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

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
			//Else if the Save button was pressed do the following
			else if(buttonPressed.equals("SaveModule")){
				editModule(code, request, response);
			}
			//Else if the Remove button was pressed do the following
			else if(buttonPressed.equals("RemoveModule")){
				removeModule(code, request, response);
			}
			//Else if the Add button was pressed do the following
			else if(buttonPressed.equals("AddModule")){
				//Get the Module Details
				String title = request.getParameter("selectedModule");
				int timeStarting = Integer.parseInt(request.getParameter("starttime"));
				int timeEnding = Integer.parseInt(request.getParameter("endtime"));
				String[] days = request.getParameterValues("day");
				String roomNumber = request.getParameter("room");

				//Check what day was selected
				if(days != null)
				{
					for(int i = 0; i < days.length; i++)
					{
						String dayString = days[i];
						int day;
						if(dayString.equalsIgnoreCase("SUN")) day = 0;
						else if(dayString.equalsIgnoreCase("MON")) day = 1;
						else if(dayString.equalsIgnoreCase("TUE")) day = 2;
						else if(dayString.equalsIgnoreCase("WED")) day = 3;
						else if(dayString.equalsIgnoreCase("THU")) day = 4;
						else if(dayString.equalsIgnoreCase("FRI")) day = 5;
						else day = 6;

						//Add the module to the timetable
						TimetableModule module = new TimetableModule(title, timeStarting, timeEnding, day, roomNumber);
						mongo.setTimetable(code, module);
					}
				}
			}
			//Call the Timetable class to reload the jsp page
			response.sendRedirect("Timetable");
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	//removeModule handle the deletion of the module from the timetable
	private void removeModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			//Get the modules original details
			String Otitle = request.getParameter("OModuleTitle");
			int OtimeStart = Integer.parseInt(request.getParameter("OStartTime"));
			int OtimeEnd = Integer.parseInt(request.getParameter("OEndTime"));
			int Oday = Integer.parseInt(request.getParameter("ODay"));
			String Oroom = request.getParameter("ORoomNumber");

			//Remove the module from the timetable
			TimetableModule removeModule = new TimetableModule(Otitle, OtimeStart, OtimeEnd, Oday, Oroom);
			mongo.removeModule(code, removeModule);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	//editModule handle the editing of modules currently on the timetable
	private void editModule(String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			//Remove the previous details from the timetable by calling removeModule()
			removeModule(code, request, response);

			//Get the edited module details
			String title = request.getParameter("editModuleTitle");
			int timeStart = Integer.parseInt(request.getParameter("editStartTime"));
			int timeEnd = Integer.parseInt(request.getParameter("editEndTime"));
			String room = request.getParameter("editRoomNumber");
			String[] days = request.getParameterValues("editDay");

			//Check what day was selected
			for(int i = 0; i < days.length; i++)
			{
				String dayString = days[i];
				int day;
				if(dayString.equalsIgnoreCase("SUN")) day = 0;
				else if(dayString.equalsIgnoreCase("MON")) day = 1;
				else if(dayString.equalsIgnoreCase("TUE")) day = 2;
				else if(dayString.equalsIgnoreCase("WED")) day = 3;
				else if(dayString.equalsIgnoreCase("THU")) day = 4;
				else if(dayString.equalsIgnoreCase("FRI")) day = 5;
				else day = 6;

				//Add the new details to the timetable
				TimetableModule module = new TimetableModule(title, timeStart, timeEnd, day, room);
				mongo.setTimetable(code, module);
			}
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	//createModule handle the creation of a new Module
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