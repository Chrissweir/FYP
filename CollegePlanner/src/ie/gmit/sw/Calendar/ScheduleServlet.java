package ie.gmit.sw.Calendar;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet implements Servlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	// take the input from the form and perform your actions
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);

		// read in the information submitted by the form
		String title = req.getParameter("title");
		int startTime = Integer.parseInt(req.getParameter("startTime"));
		int endTime = Integer.parseInt(req.getParameter("endTime"));
		String[] days = req.getParameterValues("day");

		// obtain the existing CalanderSchedule, if there is one, or to create a
		// new one if one hasn't been created yet
		CalanderSchedule schedule = (CalanderSchedule) req.getSession(true).getAttribute("CalanderSchedule");
		if (schedule == null) {
			schedule = new CalanderSchedule();
		}

		if (days != null) {
			for (int i = 0; i < days.length; i++) {
				String dayString = days[i];
				int day;
				if (dayString.equalsIgnoreCase("SUN"))
					day = 0;
				else if (dayString.equalsIgnoreCase("MON"))
					day = 1;
				else if (dayString.equalsIgnoreCase("TUE"))
					day = 2;
				else if (dayString.equalsIgnoreCase("WED"))
					day = 3;
				else if (dayString.equalsIgnoreCase("THU"))
					day = 4;
				else if (dayString.equalsIgnoreCase("FRI"))
					day = 5;
				else
					day = 6;

				CalanderSchedule clazz = new CalanderSchedule(title, startTime, endTime, day);
				schedule.addClass(clazz);
				
				req.getSession().setAttribute("CalanderSchedule", schedule);
				getServletContext().getRequestDispatcher("/Calendar.jsp").forward(req, resp);
			}
		}
	}

}
