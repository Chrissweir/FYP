package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Security.AccountRecoveryDetails;

public class AccountRecoveryTest {
	AccountRecoveryDetails ard = new AccountRecoveryDetails();
	
	@Test
	public void test() {
		ard.setEmail("Email");
		ard.setUsername("Username");
		
		ard.getEmail();
		ard.getUsername();
		
		assertEquals("Email", ard.getEmail());
		assertEquals("Username", ard.getUsername());
	}

}
