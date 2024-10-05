/**
 * 
 */
package questions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "tblmbtbquestionoptions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class QuestionsOptions {
	
	
	@Id
	@Column(name = "chroptionuniqueid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String chroptionuniqueid;
	
	private String questid;
	
	private String varOptionValue;
	private int chrOptionOrder;
    private char IsCorrectOption;
    private char chrActive;


	public String getChroptionuniqueid() {
		return chroptionuniqueid;
	}
	/**
	 * @param chroptionuniqueid the chroptionuniqueid to set
	 */
	public void setChroptionuniqueid(String chroptionuniqueid) {
		this.chroptionuniqueid = chroptionuniqueid;
	}

	public String getVarOptionValue() {
		return varOptionValue;
	}
	/**
	 * @param varOptionValue the varOptionValue to set
	 */
	public void setVarOptionValue(String varOptionValue) {
		this.varOptionValue = varOptionValue;
	}
	/**
	 * @return the chrOptionOrder
	 */
	public int getChrOptionOrder() {
		return chrOptionOrder;
	}
	/**
	 * @param chrOptionOrder the chrOptionOrder to set
	 */
	public void setChrOptionOrder(int chrOptionOrder) {
		this.chrOptionOrder = chrOptionOrder;
	}
	/**
	 * @return the isCorrectOption
	 */
	public char getIsCorrectOption() {
		return IsCorrectOption;
	}
	/**
	 * @param isCorrectOption the isCorrectOption to set
	 */
	public void setIsCorrectOption(char isCorrectOption) {
		IsCorrectOption = isCorrectOption;
	}
	/**
	 * @return the chrActive
	 */
	public char getChrActive() {
		return chrActive;
	}
	/**
	 * @param chrActive the chrActive to set
	 */
	public void setChrActive(char chrActive) {
		this.chrActive = chrActive;
	}
	@Override
	public String toString() {
		return "QuestionsOptions [chroptionuniqueid=" + chroptionuniqueid + ", varOptionValue=" + varOptionValue
				+ ", chrOptionOrder=" + chrOptionOrder + ", IsCorrectOption=" + IsCorrectOption + ", chrActive="
				+ chrActive + "]";
	}



	
	
    

}
