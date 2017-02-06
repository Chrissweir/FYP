package ie.gmit.sw.Timetable;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimetableServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.doPost(request, response);
		
		String title = request.getParameter("title");
		String[] days = request.getParameterValues("day");
		int starttime = Integer.parseInt(request.getParameter("starttime"));
		int endtime = Integer.parseInt(request.getParameter("endtime"));
		
		Timetable schedule = (Timetable)request.getSession(true).getAttribute("schoolschedule");
		if(schedule == null)
		{
			schedule = new Timetable();
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
		
				Module module = new Module(title, starttime, endtime, day);
				schedule.addClass(module);
			}
		}
		
		request.getSession().setAttribute("schoolschedule", schedule);
		getServletContext().getRequestDispatcher("/Schedule.jsp").forward(request, response);
		
	}
	
	

}
