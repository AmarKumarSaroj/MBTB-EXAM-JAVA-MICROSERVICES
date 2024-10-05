/**
 * 
 */
package utils;

/**
 * 
 */

public class UserInfo {
	
	private String username;
	private String coursecode;
	private String questId;
	private String optionId;
	
	
	
	
	/**
	 * @return the questId
	 */
	public String getQuestId() {
		return questId;
	}
	/**
	 * @param questId the questId to set
	 */
	public void setQuestId(String questId) {
		this.questId = questId;
	}
	/**
	 * @return the optionId
	 */
	public String getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(String optionId) {
		this.optionId = optionId;
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
	/**
	 * @return the coursecode
	 */
	public String getCoursecode() {
		return coursecode;
	}
	/**
	 * @param coursecode the coursecode to set
	 */
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", coursecode=" + coursecode + ", questId=" + questId + ", optionId="
				+ optionId + "]";
	}
	
	

}
