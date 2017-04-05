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

/**
 * This class is for getting a handle on the session, connecting to mongo,
 * providing the data for the user
 */
@WebServlet("/CalendarEvents")
public class CalendarEvents extends HttpServlet {
	// create a new calendar value object
	private CalendarValues cal = new CalendarValues();
	// create a new mongo object
	private MongoConnection mongo = new MongoConnection();
	// a universal version identifier for a Serializable class
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get a handle on the session
			HttpSession session = request.getSession();
			//A new list where values will be added
			List l = new ArrayList();
			//User code to access user data
			String code = (String) session.getAttribute("code");
			ArrayList<String[]> list = (ArrayList<String[]>) mongo.getCalender(code);
			
			int i = 0;//for id
			//r =  an array for calendar attributes for creating events
			for (String[] r : list) {
				// create a new calendar value object
				CalendarValues c = new CalendarValues();
				//set the inputed values
				c.setId(i);
				c.setTitle(r[0]);

				// This allows user to create all day event and timed events and can only be all day or a timed
				if (r[3].equals("ALL DAY") && r[4].equals("ALL DAY")) {
					c.setStart(r[1]);
					c.setEnd(r[2]);
				} else {
					c.setStart(r[1] + "T" + r[3]);
					c.setEnd(r[2] + "T" + r[4]);
				}

				c.setColor(r[5]);
				l.add(c);
			}
			//setting a calendar event for an assignment 
			ArrayList<String[]> assignments = (ArrayList<String[]>) mongo.getModuleAssignments(code);
			for (String[] assignment : assignments) {
				CalendarValues c = new CalendarValues();
				c.setId(1);
				c.setTitle(assignment[1]);
				c.setStart(assignment[2]);
				c.setEnd(assignment[2]);
				c.setColor("#00ffff");
				l.add(c);
			}

			/*
			 * google-gson. Gson is a Java library that can be used to convert
			 * Java Objects into their JSON representation
			 */
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			RequestDispatcher rg = request.getRequestDispatcher("Calendar");
			out.write(new Gson().toJson(l));
			
		} catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}

	}



	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
				cal.setStart(request.getParameter("startDate"));
				cal.setEnd(request.getParameter("endDate"));
				cal.setColor(request.getParameter("color"));
				if (request.getParameter("startTime").equalsIgnoreCase("ALL DAY")
						&& request.getParameter("endTime").equalsIgnoreCase("ALL DAY")) {
					cal.setStartTime("ALL DAY");
					cal.setEndTime("ALL DAY");
				} else {
					cal.setStartTime(request.getParameter("startTime"));
					cal.setEndTime(request.getParameter("endTime"));
				}

				mongo.setCalendar(code, cal);

				response.sendRedirect("Calendar");

			}
		} catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * responsible for deleting events gets a handle on the original values and removes them 
	 */
	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");

			cal.setTitle(request.getParameter("Otitle"));
			cal.setStart(request.getParameter("Ostart"));
			cal.setEnd(request.getParameter("Oend"));
			if (request.getParameter("OstartTime").equalsIgnoreCase("ALL DAY")
					&& request.getParameter("OendTime").equalsIgnoreCase("ALL DAY")) {
				cal.setStartTime("ALL DAY");
				cal.setEndTime("ALL DAY");
			} else {
				cal.setStartTime(request.getParameter("OstartTime"));
				cal.setEndTime(request.getParameter("OendTime"));
			}

			mongo.deleteCalendar(code, cal);

			response.sendRedirect("Calendar.jsp");
		} catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * Responsible for editing events where it takes the original value and updates it 
	 */
	private void editEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");
			//getting original values 
			cal.setTitle(request.getParameter("Otitle"));
			cal.setStart(request.getParameter("Ostart"));
			cal.setEnd(request.getParameter("Oend"));
			cal.setColor(request.getParameter("Ocolor"));
			if (request.getParameter("OstartTime").equalsIgnoreCase("ALL DAY")
					&& request.getParameter("OendTime").equalsIgnoreCase("ALL DAY")) {
				cal.setStartTime("ALL DAY");
				cal.setEndTime("ALL DAY");
			} else {
				cal.setStartTime(request.getParameter("OstartTime"));
				cal.setEndTime(request.getParameter("OendTime"));
			}
			
			//deletes it firstly and the sets the new values below
			mongo.deleteCalendar(code, cal);

			cal.setTitle(request.getParameter("editTitle"));
			cal.setStart(request.getParameter("editStartDate"));
			cal.setEnd(request.getParameter("editEndDate"));
			cal.setColor(request.getParameter("editColor"));
			//both start and end must be the same value types or it throws invalid date
			if (request.getParameter("editStartTime").equalsIgnoreCase("ALL DAY")
					&& request.getParameter("editEndTime").equalsIgnoreCase("ALL DAY")) {
				cal.setStartTime("ALL DAY");
				cal.setEndTime("ALL DAY");
			} else {
				cal.setStartTime(request.getParameter("editStartTime"));
				cal.setEndTime(request.getParameter("editEndTime"));
			}

			//set the edited values
			mongo.setCalendar(code, cal);
			
			//redirect the page to calendar
			response.sendRedirect("Calendar.jsp");
		} catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}

	}

}
