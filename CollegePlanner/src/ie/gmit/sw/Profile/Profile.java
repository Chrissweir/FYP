package ie.gmit.sw.Profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Connections.SQLConnection;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDetails userDetails = new UserDetails();
	private MongoConnection mongo = new MongoConnection();
	private SQLConnection sqlConn =new SQLConnection();
	private String image;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null){
			RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
			rd.forward(request, response);	
		}
		try{
			userDetails.setCode((String)session.getAttribute("code"));
			image = mongo.getUserImage(userDetails.getCode());
			session.removeAttribute("image");
			session.setAttribute("image", image);
			RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
			rd.forward(request, response);	
		}catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonPressed = request.getParameter("btn");
		if (buttonPressed != null && buttonPressed.equals("delete")) {
			removeAccount(request, response);
		} else if (buttonPressed.equals("update")) {
			userDetails.setPath(request.getParameter("imgPath"));
			userDetails.setFirstName(request.getParameter("firstname"));
			userDetails.setLastName(request.getParameter("lastname"));
			userDetails.setEmail(request.getParameter("email"));
			userDetails.setCollege(request.getParameter("college"));
			HttpSession session = request.getSession();

			userDetails.setCode((String)session.getAttribute("code"));
			
			try{
				sqlConn.updateUserDetails(userDetails);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			mongo.setUserData(userDetails.getCode(), userDetails.getPath());
			session.setAttribute("firstname", userDetails.getFirstName());
			session.setAttribute("lastname", userDetails.getLastName());
			session.setAttribute("email", userDetails.getEmail());
			session.setAttribute("college", userDetails.getCollege());
			session.removeAttribute("image");
			session.setAttribute("image", userDetails.getPath());
			response.sendRedirect("Profile");
		}
	}

	private void removeAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session = request.getSession();
			userDetails.setCode((String)session.getAttribute("code"));
			userDetails.setPassword(request.getParameter("confirmPassword"));

			if(sqlConn.removeUser(userDetails) == true){
				session.removeAttribute("firstname");
				session.removeAttribute("lastname");
				session.removeAttribute("email");
				session.removeAttribute("college");
				session.removeAttribute("image");
				session.removeAttribute("code");
				session.invalidate();
				response.sendRedirect("Logout");
			}
			//If t he passwords do not match then send an error back to the LoginRegister.jsp page
			else{
				request.setAttribute("error","Invalid Password");
				RequestDispatcher rd=request.getRequestDispatcher("Profile.jsp");            
				rd.include(request, response);
			}
		}
		catch (Exception e)
		{
			//error page
		}
	}
}//219