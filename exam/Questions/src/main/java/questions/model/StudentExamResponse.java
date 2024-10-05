/**
 *
 */
package questions.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "tblstudentexamresponse")
public class StudentExamResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String chrStudentExamResponseCode;

    String chrExamAttendanceCode;

    String questId;

    Integer chrQuestionOrder;

    String chrOptionUniqueId;
    boolean isSelected;
    Date attemptDate;
    Date modifyDate;
    int attemptCount;
    Boolean markfordelete;
    Integer limitQuestCount;

    @OneToMany(mappedBy = "questid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionsOptions> options;


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

    /**
     * @return the chrStudentExamResponseCode
     */
    public String getChrStudentExamResponseCode() {
        return chrStudentExamResponseCode;
    }

    /**
     * @param chrStudentExamResponseCode the chrStudentExamResponseCode to set
     */
    public void setChrStudentExamResponseCode(String chrStudentExamResponseCode) {
        this.chrStudentExamResponseCode = chrStudentExamResponseCode;
    }

    /**
     * @return the chrExamAttendanceCode
     */
    public String getChrExamAttendanceCode() {
        return chrExamAttendanceCode;
    }

    /**
     * @param chrExamAttendanceCode the chrExamAttendanceCode to set
     */
    public void setChrExamAttendanceCode(String chrExamAttendanceCode) {
        this.chrExamAttendanceCode = chrExamAttendanceCode;
    }
    /**
     * @return the questId
     */

    /**
     * @return the chrQuestionOrder
     */
    public int getChrQuestionOrder() {
        return chrQuestionOrder;
    }
    /**
     * @return the questId
     */

    /**
     * @param chrQuestionOrder the chrQuestionOrder to set
     */
    public void setChrQuestionOrder(Integer chrQuestionOrder) {
        this.chrQuestionOrder = chrQuestionOrder;
    }

    /**
     * @param chrQuestionOrder the chrQuestionOrder to set
     */
    public void setChrQuestionOrder(int chrQuestionOrder) {
        this.chrQuestionOrder = chrQuestionOrder;
    }

    /**
     * @return the markfordelete
     */
    public Boolean getMarkfordelete() {
        return markfordelete;
    }

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
     * @return the chrOptionUniqueId
     */
    public String getChrOptionUniqueId() {
        return chrOptionUniqueId;
    }

    /**
     * @param chrOptionUniqueId the chrOptionUniqueId to set
     */
    public void setChrOptionUniqueId(String chrOptionUniqueId) {
        this.chrOptionUniqueId = chrOptionUniqueId;
    }

    /**
     * @return the isSelected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @return the attemptDate
     */
    public Date getAttemptDate() {
        return attemptDate;
    }

    /**
     * @param attemptDate the attemptDate to set
     */
    public void setAttemptDate(Date attemptDate) {
        this.attemptDate = attemptDate;
    }

    /**
     * @return the modifyDate
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate the modifyDate to set
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return the attemptCount
     */
    public int getAttemptCount() {
        return attemptCount;
    }

    /**
     * @param attemptCount the attemptCount to set
     */
    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    /**
     * @return the markfordelete
     */
    public boolean isMarkfordelete() {
        return markfordelete;
    }

    /**
     * @param markfordelete the markfordelete to set
     */
    public void setMarkfordelete(Boolean markfordelete) {
        this.markfordelete = markfordelete;
    }

    /**
     * @param markfordelete the markfordelete to set
     */
    public void setMarkfordelete(boolean markfordelete) {
        this.markfordelete = markfordelete;
    }

    /**
     * @return the limitQuestCount
     */
    public int getLimitQuestCount() {
        return limitQuestCount;
    }

    /**
     * @param limitQuestCount the limitQuestCount to set
     */
    public void setLimitQuestCount(Integer limitQuestCount) {
        this.limitQuestCount = limitQuestCount;
    }

    /**
     * @param limitQuestCount the limitQuestCount to set
     */
    public void setLimitQuestCount(int limitQuestCount) {
        this.limitQuestCount = limitQuestCount;
    }

    @Override
    public String toString() {
        return "StudentExamResponse [chrStudentExamResponseCode=" + chrStudentExamResponseCode
                + ", chrExamAttendanceCode=" + chrExamAttendanceCode + ", questId=" + questId + ", chrQuestionOrder="
                + chrQuestionOrder + ", chrOptionUniqueId=" + chrOptionUniqueId + ", isSelected=" + isSelected
                + ", attemptDate=" + attemptDate + ", modifyDate=" + modifyDate + ", attemptCount=" + attemptCount
                + ", markfordelete=" + markfordelete + ", limitQuestCount=" + limitQuestCount + ", options=" + options
                + "]";
    }


}
