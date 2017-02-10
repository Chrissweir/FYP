package ie.gmit.sw.Calendar;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleCalendar extends HttpServlet implements Servlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp); // specified that your form would submit data
									// using the POST method

		String task = req.getParameter("task");
		int startTask = Integer.parseInt(req.getParameter("startTask"));
		int endTask = Integer.parseInt(req.getParameter("endTask"));
		String[] days = req.getParameterValues("day");

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

				CalanderSchedule tasks = new CalanderSchedule(task, startTask, endTask, day);
				//schedule.addClass(tasks);
				schedule.addClass(tasks);//7
			}
		}
	}

}
