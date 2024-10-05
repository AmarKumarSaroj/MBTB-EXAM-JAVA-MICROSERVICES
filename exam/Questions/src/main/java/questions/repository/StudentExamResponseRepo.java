
package questions.repository;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import questions.model.StudentExamResponse;
  
  
 

  @Repository 
  public interface StudentExamResponseRepo extends  JpaRepository<StudentExamResponse, String>{
  
 
	 	 @Query(value ="select  * from tblstudentexamresponse where chrExamAttendanceCode =?1 and questId = ?2 and isSelected = 1",nativeQuery = true) 
	 	 List<StudentExamResponse> getDetailsBySessionId(String sessionId, String questid);

		/**
		 * @param string
		 * @return
		 */
	 	 
	 	 @Query(value ="select case when count( distinct chrQuestionOrder) = NULL then 0 else count( distinct chrQuestionOrder) end from tblstudentexamresponse where chrExamAttendanceCode =  ?1",nativeQuery = true) 
	 	 int getQuestionsById(String chrExamAttendanceCode);

		/**
		 * @param i
		 * @param string
		 * @param questId
		 */
	 	 
	 	@Modifying
		@Transactional
		@Query(value = "\r\n"
				+ "WITH IndexedQuestids AS (\r\n"
				+ "    SELECT questid, ROW_NUMBER() OVER (ORDER BY questid) AS currentindex_value FROM tblstudentexamresponse WHERE chrExamAttendanceCode = ?1 GROUP BY questid \r\n"
				+ ")\r\n"
				+ "UPDATE tblstudentexamresponse\r\n"
				+ "SET chrQuestionOrder = IndexedQuestids.currentindex_value, limitQuestCount = IndexedQuestids.currentindex_value\r\n"
				+ "FROM IndexedQuestids\r\n"
				+ "WHERE tblstudentexamresponse.questid = IndexedQuestids.questid and chrExamAttendanceCode = ?1", nativeQuery = true)
		void updateLimitQuest(String username);

		/**
		 * @param chrOptionUniqueId 
		 * @return
		 */
	 	@Query(value = "select * from tblstudentexamresponse res where isSelected = 1 and chrOptionUniqueId = ?1",nativeQuery = true)
		List<StudentExamResponse> getOptionSelected(String chrOptionUniqueId);
		  
	 	 
		@Query(value = "select count(distinct res.questId)  from tblstudentexamresponse res inner join rstilrac_admin.tblmbtbquestions quest on quest.questid = res.questId where chrExamAttendanceCode = ?1 and quest.chrCourseCode = ?2", nativeQuery = true)
		int getTotalQuestionsCount(String chrExamAttendanceCode, String chrCourseCode);

		/**
		 */
		
		@Query(value = " select count(distinct res.questId)  from tblstudentexamresponse res inner join rstilrac_admin.tblmbtbquestions quest on quest.questid = res.questId  where chrExamAttendanceCode = ?1 and quest.chrCourseCode = ?2 and isSelected = 1", nativeQuery = true)
		int getAttemptQuestionsCount(String chrExamAttendanceCode, String chrCourseCode);

		@Query(value = "select count(distinct tres.questid) FROM   tblstudentexamresponse tres   INNER JOIN  rstilrac_admin.tblmbtbquestionoptions tqo ON   tres.chrOptionUniqueId = tqo.chroptionuniqueId inner join rstilrac_admin.tblmbtbquestions quest on  tres.questId = tqo.questId WHERE  tres.chrExamAttendanceCode = ?1 and quest.chrCourseCode =?2 AND isCorrectOption = 'Y' AND isSelected = 1  and tres.questid not in (SELECT  tres.questid FROM   tblstudentexamresponse tres   INNER JOIN  rstilrac_admin.tblmbtbquestionoptions tqo ON   tres.questId = tqo.questid   where  tres.chrExamAttendanceCode = ?1 AND  isCorrectOption = 'N' AND isSelected = 1 and  tres.chrOptionUniqueId = tqo.chroptionuniqueId)", nativeQuery = true)
		int currectQuestion(String chrExamAttendanceCode, String chrCourseCode);

		/**
		 * @param username
		 * @param coursecode
		 */
		@Query(value = "SELECT DATEDIFF(MILLISECOND, min(attemptdate), max(modifydate)) FROM tblstudentexamresponse res inner join rstilrac_admin.tblmbtbquestions quest on res.questId = quest.questid where chrExamAttendanceCode = ?1 and quest.chrCourseCode = ?2",nativeQuery = true)
		String getDoneExamTime(String username, String coursecode);
		
		@Query(value = "select case when count(*) > 0 then 1 else 0 end from tblstudentexamresponse res inner join tblexamattendance att on res.chrExamAttendanceCode = att.username where res.chrExamAttendanceCode = ?1 and att.dttSubmitDate is not null",nativeQuery = true)
		int examStatus (String username);
		
	 	@Modifying
		@Transactional
		@Query(value = "delete tblstudentexamresponse  where chrExamAttendanceCode = ?1",nativeQuery = true)
		void deleteResponse(String username);

		/**
		 * 
		 */
	 	@Modifying
	 	@Query(value = "INSERT INTO tblstudentexamresponse (chrExamAttendanceCode, questid, chrOptionUniqueID, isSelected, attemptCount, limitQuestCount, chrQuestionOrder) " +
	 	               "SELECT :username, o.questid, o.chrOptionUniqueID, 0, 0, 0, 0 " +
	 	               "FROM rstilrac_admin.tblmbtbquestions q " +
	 	               "INNER JOIN rstilrac_admin.tblmbtbquestionoptions o ON q.questid = o.questid " +
	 	               "WHERE q.questid IN (SELECT questid FROM (SELECT TOP 25 questid FROM rstilrac_admin.tblmbtbquestions WHERE chrCourseCode = :courseCode ORDER BY NEWID()) AS subquery)", 
	 	       nativeQuery = true)
	 	void setQuestionInResponse(@Param("username") String username, @Param("courseCode") String courseCode);

		/**
		 * @param username
		 */
	 	//
	 	@Modifying
	 	@Query(value = "UPDATE tblstudentexamresponse SET attemptDate = case when attemptDate is null then COALESCE(attemptDate, CURRENT_TIMESTAMP) else attemptDate end, modifyDate = CURRENT_TIMESTAMP WHERE chrExamAttendanceCode = ?1 AND questid in (select distinct questId  from tblstudentexamresponse where chrExamAttendanceCode = ?1 and chrQuestionOrder = 1 )",nativeQuery = true)
	 	void updateQuestFirst(String username);



	 	
	 	
		/*
		 * @Modifying
		 * 
		 * @Transactional
		 * 
		 * @Query(value = "",nativeQuery = true) void submittedExam(String username);
		 */
	 	
	 	
	 	
	 	
	 	
	 	
	 	 
 }
		 
