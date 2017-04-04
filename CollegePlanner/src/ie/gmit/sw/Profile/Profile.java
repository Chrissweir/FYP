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
import ie.gmit.sw.Security.Encryption;

/**
 * @author Christopher Weir - G00309429
 * This class is the Servlet for the Profile.jsp page. It is responsible for getting
 * the users profile from the databases, handling profile updates and the removal of profiles. 
 *
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDetails userDetails = new UserDetails();
	private MongoConnection mongo = new MongoConnection();
	private SQLConnection sqlConn =new SQLConnection();
	private Encryption encrypt = new Encryption();
	private String image;	

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get a handle on the session
		HttpSession session = request.getSession();
		//Before loading the Profile page, try to get the users Profile picture from the database
	
		userDetails.setCode((String)session.getAttribute("code"));
		image = mongo.getUserImage(userDetails.getCode());
		//Remove the previous image if there happens to be one 
		//(This line can be removed once the logout bug has been fixed)
		session.removeAttribute("image");
		session.setAttribute("image", image);
		RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
		rd.forward(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Detect which button was pressed
		String buttonPressed = request.getParameter("btn");

		//If delete was pressed then proceed to remove the account
		if (buttonPressed != null && buttonPressed.equals("delete")) {
			removeAccount(request, response);
		} 
		//Else if the update button was pressed update the account
		else if (buttonPressed.equals("update")) {
			HttpSession session = request.getSession();
			try{	
				userDetails.setPath(request.getParameter("imgPath"));
				userDetails.setFirstName(request.getParameter("firstname"));
				userDetails.setLastName(request.getParameter("lastname"));
				userDetails.setEmail(request.getParameter("email"));
				userDetails.setCollege(request.getParameter("college"));
				userDetails.setCourse(request.getParameter("course"));
				userDetails.setBio(request.getParameter("bio"));
				userDetails.setCode((String)session.getAttribute("code"));

				//Send the new details to the database
				sqlConn.updateUserDetails(userDetails);

				//Update the users Profile picture
				mongo.setUserData(userDetails.getCode(), userDetails.getPath());

				//Update the session attributes
				session.setAttribute("firstname", userDetails.getFirstName());
				session.setAttribute("lastname", userDetails.getLastName());
				session.setAttribute("email", userDetails.getEmail());
				session.setAttribute("college", userDetails.getCollege());
				session.setAttribute("course", userDetails.getCourse());
				session.setAttribute("bio", userDetails.getBio());
				session.removeAttribute("image");
				session.setAttribute("image", userDetails.getPath());

				//Redirect the user back to the Profile page
				response.sendRedirect("Profile");
			}
			catch (Exception e) {
				response.sendRedirect("ErrorHandler");
			}
		}
	}

	/**
	 * removeAccount() handles the user account removal.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void removeAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			//Get a handle on the session
			HttpSession session = request.getSession();
			String code = session.getAttribute("code").toString();
			userDetails.setCode(code);
			userDetails.setPassword(encrypt.encrypt((request.getParameter("confirmPassword"))));

			//Check if the password entered matches the users password
			//If true, then redirect the user
			if(sqlConn.removeUser(userDetails) == true){
				mongo.removeUserData(code);
				response.sendRedirect("Logout");
			}
			//If the passwords do not match then send an error back to the Prfole page
			else{
				request.setAttribute("error","Invalid Password");
				RequestDispatcher rd=request.getRequestDispatcher("Profile.jsp");            
				rd.include(request, response);
			}
		}
		catch (Exception e)
		{
			response.sendRedirect("ErrorHandler");
		}
	}
}