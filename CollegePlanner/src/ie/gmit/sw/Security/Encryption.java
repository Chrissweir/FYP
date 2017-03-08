package ie.gmit.sw.Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public String encrypt(String args) throws NoSuchAlgorithmException{
		String passwordToHash = args;
		String salt = "kg45236khkb";

		String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
		return securePassword;
	}

	private static String get_SHA_512_SecurePassword(String passwordToHash, String salt)
	{
		//Use MessageDigest md = MessageDigest.getInstance("SHA-512");
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest((passwordToHash.concat(salt)).getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}
}