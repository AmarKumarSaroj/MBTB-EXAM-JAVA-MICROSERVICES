/**
 * 
 */
package admin.main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.Entity;

/**
 * 
 */
public class Student {


	private String chrCentrecode ;

	/**
	 * @return the chrCentrecode
	 */
	public String getChrCentrecode() {
		return chrCentrecode;
	}

	/**
	 * @param chrCentrecode the chrCentrecode to set
	 */
	public void setChrCentrecode(String chrCentrecode) {
		this.chrCentrecode = chrCentrecode;
	}

	@Override
	public String toString() {
		return "Student [chrCentrecode=" + chrCentrecode + "]";
	}
	
	
	
	
	
	
	
	
}
