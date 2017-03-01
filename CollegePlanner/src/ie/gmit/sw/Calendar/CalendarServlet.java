package ie.gmit.sw.Calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
public class CalendarServlet extends HttpServlet  {
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
		// TODO Auto-generated method stub
		List l = new ArrayList();
		ArrayList<String[]> list = new ArrayList<String[]>();
		HttpSession session = req.getSession();
		String code = (String) session.getAttribute("code");
		list = (ArrayList<String[]>) mongo.getCalender(code);
		int i =0;
		for(String[] r : list){
			CalendarValues c = new CalendarValues();
			c.setId(i);
			c.setTitle(r[0]);
			c.setStart(r[1]);
			c.setEnd(r[2]);
			l.add(c);
		}

		/*
		 * google-gson. Gson is a Java library that can be used to convert Java Objects into their JSON representation
		 * */

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(l));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		
		//Detect which button was pressed
				String buttonPressed = request.getParameter("btn");
				
				//If delete was pressed then proceed to remove the account
				if (buttonPressed != null && buttonPressed.equals("edit")) {
					editEvent(request, response);
				} 
				//Else if the update button was pressed update the account
				else if (buttonPressed.equals("save")) {
		cal.setTitle(request.getParameter("Title"));
		System.out.println("hello " + cal.getTitle());

		cal.setStart(request.getParameter("startDate"));
		System.out.println("Start " + cal.getStart());

		cal.setEnd(request.getParameter("endDate"));
		System.out.println("End " + cal.getEnd());
		//mongo.setCalender(cal);
		mongo.setCalendar(code, cal);

		response.sendRedirect("Calendar.jsp");

	}
	}

	private void editEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		
		cal.setTitle(request.getParameter("Otitle"));		
		cal.setStart(request.getParameter("Ostart"));
		cal.setEnd(request.getParameter("Oend"));
		
		mongo.deleteCalendar(code, cal);
		
		
		cal.setTitle(request.getParameter("editTitle"));
		System.out.println("hello " + cal.getTitle());

		cal.setStart(request.getParameter("editStartDate"));
		System.out.println("Start " + cal.getStart());

		cal.setEnd(request.getParameter("editEndDate"));
		System.out.println("End " + cal.getEnd());
		
		
		//mongo.setCalender(cal);
		mongo.setCalendar(code, cal);

		
		response.sendRedirect("Calendar.jsp");
		
	}

}
