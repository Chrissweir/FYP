package ie.gmit.sw.JUnitTests;

import org.junit.Test;
import static org.junit.Assert.*;

import ie.gmit.sw.Register.RegisterUserDetails;

public class RegisterUserDetailsTest {
	RegisterUserDetails rud = new RegisterUserDetails();
	
	@Test
	public void testGetUsername() {
		rud.getUsername();
	}

	@Test
	public void testSetUsername() {
		rud.setUsername("Username");
	}

	@Test
	public void testGetPassword() {
		rud.getPassword();
	}

	@Test
	public void testSetPassword() {
		rud.setPassword("password");
	}

	@Test
	public void testGetFirstname() {
		rud.getFirstname();
	}

	@Test
	public void testSetFirstname() {
		rud.setFirstname("Firstname");
	}

	@Test
	public void testGetLastname() {
		rud.getLastname();
	}

	@Test
	public void testSetLastname() {
		rud.setLastname("Lastname");
	}

	@Test
	public void testGetEmail() {
		rud.getEmail();
	}

	@Test
	public void testSetEmail() {
		rud.setEmail("Email");
	}

	@Test
	public void testGetCollege() {
		rud.getCollege();
	}

	@Test
	public void testSetCollege() {
		rud.setCollege("College");
	}

	@Test
	public void testGetCode() {
		rud.getCode();
	}

	@Test
	public void testSetCode() {
		rud.setCode("Code");
	}

	@Test
	public void testIsUserAvailable() {
		rud.isUserAvailable();
	}

	@Test
	public void testSetUserAvailable() {
		rud.setUserAvailable(false);
	}

	@Test
	public void testIsEmailAvailable() {
		rud.isEmailAvailable();
	}

	@Test
	public void testSetEmailAvailable() {
		rud.setEmailAvailable(true);
	}

	@Test
	public void testSetCourse() {
		rud.setCourse("Course");
	}

	@Test
	public void testGetCourse() {
		rud.getCourse();
	}

	@Test
	public void testSetBio() {
		rud.setBio("Bio");	
	}

	@Test
	public void testGetBio() {
		rud.getBio();
	}
	
	@Test
	public void test(){
		rud.setUserAvailable(true);
		rud.setFirstname("FirstName");
		assertEquals(true, rud.isUserAvailable());
		assertEquals("FirstName", rud.getFirstname());
	}

}
