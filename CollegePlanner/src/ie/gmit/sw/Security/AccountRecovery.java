package ie.gmit.sw.Security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.Connections.MongoConnection;
import ie.gmit.sw.Connections.SQLConnection;

/**
 * Servlet implementation class PasswordRecovery
 */
@WebServlet("/AccountRecovery")
public class AccountRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountRecoveryDetails ar = new AccountRecoveryDetails();
	private SQLConnection sqlConn = new SQLConnection();
	private Encryption encrypt = new Encryption();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			RequestDispatcher rd = request.getRequestDispatcher("AccountRecovery.jsp");
			rd.forward(request, response);	
		}catch (Exception e) {
			response.sendRedirect("ErrorHandler");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("recover-submit").equals("Reset Password")){
			try{
				//Get the username and email entered
				//Set AccountRecoveryDetails to these values
				ar.setUsername(request.getParameter("username"));
				ar.setEmail(request.getParameter("email"));

				//Query SQL
				if(sqlConn.checkUser(ar)){
					RequestDispatcher rd = request.getRequestDispatcher("RecoverAccount.jsp");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request.getRequestDispatcher("AccountRecovery.jsp");
					rd.forward(request, response);
				}
			}
			catch(Exception e){
				response.sendRedirect("ErrorHandler");
			}
		}else if(!request.getParameter("recover-submit").equals("Reset Password")){
			try{
				//Get the password and confpassword entered
				//Set AccountRecoveryDetails to these values
				String password = request.getParameter("password");

				//Query SQL
				sqlConn.resetPassword(encrypt.encrypt(password), ar);

				RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
				rd.forward(request, response);
			}
			catch(Exception e){
				response.sendRedirect("ErrorHandler");
			}
		}
		else{
			response.sendRedirect("ErrorHandler");
		}
	}
}