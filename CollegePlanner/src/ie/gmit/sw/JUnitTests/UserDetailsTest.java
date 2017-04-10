package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Profile.UserDetails;

public class UserDetailsTest {
	UserDetails ud = new UserDetails();
	
	@Test
	public void test() {
		ud.setPassword("password");
		ud.setBio("Bio");
		ud.setCode("Code");
		ud.setCollege("College");
		ud.setCourse("Course");
		ud.setEmail("Email");
		ud.setFirstName("Firstname");
		ud.setLastName("LastName");
		ud.setPass("Pass");
		ud.setPath("Path");
		
		ud.getBio();
		ud.getCode();
		ud.getCollege();
		ud.getCourse();
		ud.getEmail();
		ud.getFirstName();
		ud.getLastName();
		ud.getPass();
		ud.getPassword();
		ud.getPath();
		
		assertEquals("Email", ud.getEmail());
		assertEquals("Firstname", ud.getFirstName());
		assertEquals("Pass", ud.getPass());
		assertEquals("Bio", ud.getBio());
	}
}