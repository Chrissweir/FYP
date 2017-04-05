package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ie.gmit.sw.Assignments.AssignmentsServlet;
import ie.gmit.sw.Connections.MongoConnection;

public class AssignmentTest extends Mockito{
	
	@Test
	public void test() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);   
        MongoConnection mongo = new MongoConnection();

        when((String)session.getAttribute("code")).thenReturn("authenticationCode");
        
		when((ArrayList<String[]>) mongo.getModules(code)).thenReturn(null);
		
		new AssignmentsServlet().doGet(request, response);
		
		verify(session, atLeast(1)).getAttribute("code");
	}

}
