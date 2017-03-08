package ie.gmit.sw.Calendar;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;

import ie.gmit.sw.Calendar.CalendarValues;
import ie.gmit.sw.Connections.MongoConnection;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private CalendarValues cal = new CalendarValues();
	private MongoConnection mongo = new MongoConnection();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		// Get a handle on the session
		HttpSession session = req.getSession();
		// Check if the user is logged in, if not then redirect them to the
		// login page
		/*
		 * if(session.getAttribute("code") == null){ RequestDispatcher rd =
		 * req.getRequestDispatcher("LoginRegister.jsp"); rd.forward(req,
		 * response); }
		 */
		List l = new ArrayList();
		String code = (String) session.getAttribute("code");
		ArrayList<String[]> list = (ArrayList<String[]>) mongo.getCalender(code);
		int i = 0;
		for (String[] r : list) {
			CalendarValues c = new CalendarValues();
			c.setId(i);
			c.setTitle(r[0]);
			//This allows user to create all day event and timed events
			if (r[3].equals("") || r[4].equals("")) {
				c.setStart(r[1]);
				c.setEnd(r[2]);
			} else {
				c.setStart(r[1] + "T" + r[3]);
				c.setEnd(r[2] + "T" + r[4]);
			}
			// c.setStartTime(r[3]);
			// c.setEndTime(r[4]);
			l.add(c);
		}

		/*
		 * google-gson. Gson is a Java library that can be used to convert Java
		 * Objects into their JSON representation
		 */

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		RequestDispatcher rg = req.getRequestDispatcher("Calendar.jsp");
		out.write(new Gson().toJson(l));
		// rg.include(req, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");

		// Detect which button was pressed
		String buttonPressed = request.getParameter("btn");

		// If delete was pressed then proceed to remove the account
		if (buttonPressed != null && buttonPressed.equals("edit")) {
			editEvent(request, response);
		} else if (buttonPressed != null && buttonPressed.equals("delete")) {
			deleteEvent(request, response);
		}
		// Else if the update button was pressed update the account
		else if (buttonPressed.equals("save")) {
			cal.setTitle(request.getParameter("Title"));
			System.out.println("hello " + cal.getTitle());

			cal.setStart(request.getParameter("startDate"));
			System.out.println("Start " + cal.getStart());

			cal.setEnd(request.getParameter("endDate"));
			System.out.println("End " + cal.getEnd());
			
			cal.setStartTime(request.getParameter("startTime"));
			System.out.println("Start Time: " + cal.getStartTime());

			cal.setEndTime(request.getParameter("endTime"));
			System.out.println("End Time: " + cal.getEndTime());
			// mongo.setCalender(cal);
			mongo.setCalendar(code, cal);
			
			//If/boolean for allday event is true 

			response.sendRedirect("Calendar.jsp");

		}
	}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");

		cal.setTitle(request.getParameter("Otitle"));
		cal.setStart(request.getParameter("Ostart"));
		cal.setEnd(request.getParameter("Oend"));
		
		cal.setStartTime(request.getParameter("OstartTime"));
		cal.setEndTime(request.getParameter("OendTime"));

		mongo.deleteCalendar(code, cal);

		response.sendRedirect("Calendar.jsp");
	}

	private void editEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");

		cal.setTitle(request.getParameter("Otitle"));
		cal.setStart(request.getParameter("Ostart"));
		cal.setEnd(request.getParameter("Oend"));
		cal.setStartTime(request.getParameter("OstartTime"));
		cal.setEndTime(request.getParameter("OendTime"));

		mongo.deleteCalendar(code, cal);

		cal.setTitle(request.getParameter("editTitle"));
		System.out.println("hello " + cal.getTitle());
		
		//Date-------------------------------------
		cal.setStart(request.getParameter("editStartDate"));
		System.out.println("Start " + cal.getStart());

		cal.setEnd(request.getParameter("editEndDate"));
		System.out.println("End " + cal.getEnd());
		
		//Time--------------------------------------
		cal.setStartTime(request.getParameter("editStartTime"));
		System.out.println("Time end " + cal.getStartTime());
		
		cal.setEndTime(request.getParameter("editStartTime"));
		System.out.println("Time end " + cal.getEndTime());

		// mongo.setCalender(cal);
		mongo.setCalendar(code, cal);

		response.sendRedirect("Calendar.jsp");

	}

}
