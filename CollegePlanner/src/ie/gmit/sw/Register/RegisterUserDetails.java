package ie.gmit.sw.Register;

/**
 * @author Christopher Weir - G00309429
 * This class handles the getters and setters for the users details when registering.
 *
 */
public class RegisterUserDetails {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String college;
	private String code;
	private boolean userAvailable = true;
	private boolean emailAvailable = true;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUserAvailable() {
		return userAvailable;
	}

	public void setUserAvailable(boolean userAvailable) {
		this.userAvailable = userAvailable;
	}
	
	public boolean isEmailAvailable() {
		return emailAvailable;
	}
	
	public void setEmailAvailable(boolean emailAvailable) {
		this.emailAvailable = emailAvailable;
	}
}