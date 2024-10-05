/**
 *
 */
package questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import questions.model.Questions;

import java.util.List;
import java.util.Map;


@Repository
public interface QuestionsRepo extends JpaRepository<Questions, String> {


    @Query(value = "select  top 25 questid from rstilrac_admin.tblmbtbquestions WHERE chrCourseCode = ?1 order by newid()", nativeQuery = true)
    List<String> getQuestions(String courseCode);

    /**
     * @param sessionid
     * @param id
     * @return
     */

    @Query(value = "select distinct quest.questid as 'questid', quest.question as 'Question' , options.varOptionValue as 'varOptionValue' ,options.chroptionuniqueId  as 'chroptionuniqueid' from tblstudentexamresponse res inner join rstilrac_admin.tblmbtbquestions quest on quest.questid = res.questId join rstilrac_admin.tblmbtbquestionoptions options on options.questid = quest.questid where chrExamAttendanceCode = ?1 and chrQuestionOrder = ?2", nativeQuery = true)
    List<Map<String, Object>> getQuestionByParams(String chrExamAttendanceCode, int chrQuestionOrder, String chrCourseCode);


}
