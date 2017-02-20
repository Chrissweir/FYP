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
		// mongo.getCalender();
		CalendarValues c = new CalendarValues();
		c.setId(1);
		c.setStart("2017-02-11");
		c.setEnd("2017-02-1");
		c.setTitle("Task in Progress");

		CalendarValues d = new CalendarValues();
		d.setId(2);
		d.setStart("2017-02-26");
		d.setEnd("2017-02-28");
		d.setTitle("Task in Progress");

		l.add(c);
		l.add(d);
		l.add(cal);
		
		/*for (int i = 0; i < 5; i++) {
			cal.setTitle("Title");
		}*/
		
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
