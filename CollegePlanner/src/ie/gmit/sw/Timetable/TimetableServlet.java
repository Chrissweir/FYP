package ie.gmit.sw.Timetable;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TimetableServlet")
public class TimetableServlet extends HttpServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		int timeStarting = Integer.parseInt(request.getParameter("starttime"));
		int timeEnding = Integer.parseInt(request.getParameter("endtime"));
		String[] days = request.getParameterValues("day");
		
		Timetable timetable = (Timetable)request.getSession(true).getAttribute("timetable");
		if(timetable == null)
		{
			timetable = new Timetable();
		}
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
		
				Module module = new Module(title, timeStarting, timeEnding, day);
				timetable.addClass(module);
			}
			
		}
		request.getSession().setAttribute("timetable", timetable);
		getServletContext().getRequestDispatcher("/Timetable.jsp").forward(request, response);
	}
	
	

}
