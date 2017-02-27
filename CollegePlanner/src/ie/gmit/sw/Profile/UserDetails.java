package ie.gmit.sw.Profile;

/**
 * @author Christopher Weir - G00309429
 * This class handles the getters and setters for the users details for the users Profile.
 *
 */
public class UserDetails {
	
	private String path;
	private String firstName;
	private String lastName;
	private String email;
	private String college;
	private String pass;
	private String password;
	private String code;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode(){
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}