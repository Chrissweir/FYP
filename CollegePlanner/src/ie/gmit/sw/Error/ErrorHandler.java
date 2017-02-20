package ie.gmit.sw.Error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	      response.setContentType("text/html");
	 
	      PrintWriter out = response.getWriter();
	      out.write("<html><head><title>Error</title></head><body style='text-align: center'>");
	      out.write("<h1>Ooops! Something went wrong!</h1>");
	      out.write("Click here to return to the <a href=\"Profile\">Profile</a> Page");
	      out.write("</body></html>");
	}
}