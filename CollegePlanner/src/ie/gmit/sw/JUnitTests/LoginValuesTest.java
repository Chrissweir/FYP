package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Login.Login;
import ie.gmit.sw.Login.LoginValues;

public class LoginValuesTest {
	LoginValues lv = new LoginValues();
	Login login = new Login();
	
	@Test
	public void test() {
		
		//Setters
		lv.setUsername("TestUser");
		lv.setPassword("password");
		lv.setBio("Bio");
		lv.setCode("Code");
		lv.setCollege("College");
		lv.setCourse("Course");
		lv.setEmail("Email");
		lv.setFirstname("Firstname");
		lv.setLastname("LastName");
		lv.setPass("Pass");
		lv.setUser("User");
		
		//Getters
		lv.getUsername();
		lv.getPassword();
		lv.getBio();
		lv.getCode();
		lv.getCollege();
		lv.getCourse();
		lv.getEmail();
		lv.getFirstname();
		lv.getLastname();
		lv.getPass();
		lv.getUser();
		
		assertEquals("Email", lv.getEmail());
		assertEquals("Firstname", lv.getFirstname());
		assertEquals("Pass", lv.getPass());
		assertEquals("TestUser", lv.getUsername());
	}
}