package ie.gmit.sw.Timetable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;

public class TimetableServlet extends HttpServlet implements Servlet {

	private MongoConnection mongo = new MongoConnection();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get a handle on the session
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("code");
		String title = request.getParameter("title");
		int timeStarting = Integer.parseInt(request.getParameter("starttime"));
		int timeEnding = Integer.parseInt(request.getParameter("endtime"));
		String[] days = request.getParameterValues("day");
		String roomNumber = request.getParameter("room");

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

				Module module = new Module(title, timeStarting, timeEnding, day, roomNumber);
				mongo.setTimetable(code, module);
				//timetable.addClass(module);
			}

		}
		//System.out.println(timetable.getClasses().toString().replace("[", "").replace("]", ""));
		//getServletContext().getRequestDispatcher("/Timetable.jsp").forward(request, response);
		response.sendRedirect("Timetable");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get a handle on the session
		HttpSession session = request.getSession();
		//Check if the user is logged in, if not then redirect them to the login page
		if(session.getAttribute("code") == null){
			RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
			rd.forward(request, response);	
		}
		try{
			String code = (String)session.getAttribute("code");
			Timetable timetable = new Timetable();

			ArrayList<String[]> list = new ArrayList<String[]>();
			list = (ArrayList<String[]>) mongo.getTimetable(code);
			for(String[] s : list){
				Module module = new Module(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4]);
				timetable.addClass(module);
			}
			request.getSession().setAttribute("timetable", timetable);
			RequestDispatcher rd = request.getRequestDispatcher("Timetable.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}
}