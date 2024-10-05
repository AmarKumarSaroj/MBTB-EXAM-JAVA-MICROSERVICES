package questions.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblmbtbquestions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Questions {
	
	@Id
	@Column(name = "questid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String questid;
	
	private String question;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "chrcoursecode")
	 */
	
	private String chrCourseCode;
	
    @OneToMany(mappedBy = "questid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionsOptions> options;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "questid")
    private StudentExamResponse responseOptions; 
	 
	/**
	 * @param responseOptions the responseOptions to set
	 */
	public void setResponseOptions(StudentExamResponse responseOptions) {
		this.responseOptions = responseOptions;
	}
 

	/**
	 * @return the responseOptions
	 */
	public StudentExamResponse getResponseOptions() {
		return responseOptions;
	}


	private String chrquestiontype;
	private String varimagepath;

	@Column(columnDefinition = "chrActive in ('Y','N')")
	private char chractive;
	
	/**
	 * @return the options
	 */
	public List<QuestionsOptions> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<QuestionsOptions> options) {
		this.options = options;
	}

	public String getQuestid() {
		return questid;
	}

	public void setQuestid(String questid) {
		this.questid = questid;
	}

	public String getChrquestiontype() {
		return chrquestiontype;
	}

	public void setChrquestiontype(String chrquestiontype) {
		this.chrquestiontype = chrquestiontype;
	}

	public String getVarimagepath() {
		return varimagepath;
	}

	public void setVarimagepath(String varimagepath) {
		this.varimagepath = varimagepath;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}


	public char getChractive() {
		return chractive;
	}

	public void setChractive(char chractive) {
		this.chractive = chractive;
	}


	@Override
	public String toString() {
		return "Questions [questid=" + questid + ", question=" + question + ", chrCourseCode=" + chrCourseCode
				+ ", options=" + options + ", responseOptions=" + responseOptions + ", chrquestiontype="
				+ chrquestiontype + ", varimagepath=" + varimagepath + ", chractive=" + chractive + "]";
	}



}
