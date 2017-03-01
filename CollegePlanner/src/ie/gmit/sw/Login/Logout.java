package ie.gmit.sw.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Christopher Weir - G00309429
 * This class handles the logging out of the user by removing the users session
 * and redirecting them to the Login page. 
 *
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Get a handle on the session
		HttpSession session = request.getSession();
		//Invalidate the session
		session.invalidate();
		//Redirect the user
		response.sendRedirect("LoginRegister.jsp");
		return;
	}
}