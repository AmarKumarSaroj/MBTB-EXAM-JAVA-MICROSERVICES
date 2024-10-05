package login.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse { 
    private String jwt; 
    private String message; 
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String courseid;
    private String Authorisation;
    
    
    

    
    
    /**
	 * @return the authorisation
	 */
	public String getAuthorisation() {
		return Authorisation;
	}

	/**
	 * @param authorisation the authorisation to set
	 */
	public void setAuthorisation(String authorisation) {
		Authorisation = authorisation;
	}

	/**
	 * @return the courseid
	 */
	public String getCourseid() {
		return courseid;
	}

	/**
	 * @param courseid the courseid to set
	 */
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AuthResponse [jwt=" + jwt + ", message=" + message + ", firstname=" + firstname + ", middlename="
				+ middlename + ", lastname=" + lastname + ", username=" + username + ", courseid=" + courseid
				+ ", Authorisation=" + Authorisation + ", status=" + status + "]";
	}

	private Boolean status; 
  
    public String getJwt() { 
        return jwt; 
    } 
  
    public void setJwt(String jwt) { 
        this.jwt = jwt; 
    } 
  
    public String getMessage() { 
        return message; 
    } 
  
    public void setMessage(String message) { 
        this.message = message; 
    } 
  
    public Boolean getStatus() { 
        return status; 
    } 
  
    public void setStatus(Boolean status) { 
        this.status = status; 
    }

	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	} 
    
    
    
} 