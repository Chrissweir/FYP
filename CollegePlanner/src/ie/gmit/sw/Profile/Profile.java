package ie.gmit.sw.Profile;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null){
			RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MongoClientURI uri  = new MongoClientURI("mongodb://Chris:G00309429@ds055945.mlab.com:55945/heroku_nhl6qjlh"); 
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection user = db.getCollection("User");
		
		String newFileName = "test";
		File imageFile = new File("c:\\JavaWebHosting.png");
		GridFS gfsPhoto = new GridFS(db, "photo");
		GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
		gfsFile.setFilename(newFileName);
		gfsFile.save();
		doGet(request, response);
	}

}
