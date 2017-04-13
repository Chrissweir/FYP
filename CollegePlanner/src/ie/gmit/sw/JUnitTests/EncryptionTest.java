package ie.gmit.sw.JUnitTests;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import ie.gmit.sw.Security.Encryption;

public class EncryptionTest {

	Encryption encrypt = new Encryption();
	
	@Test
	public void test() throws NoSuchAlgorithmException {
		
		assertTrue(encrypt.encrypt("Encryption String"), true);
		assertEquals("f4359729dee98f09c5e3e53095b7bba5b7b454f498e1ed40bf9fabe33408f007c83f375e2c52032697ec9629edbbd702d01a79632d0628361f23735ab91668d9", encrypt.encrypt("Encryption String"));
		assertNotNull(encrypt.encrypt("Encryption String"));
	}

}
