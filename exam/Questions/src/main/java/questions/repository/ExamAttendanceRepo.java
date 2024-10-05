/**
 *
 */
package questions.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import questions.model.ExamAttendance;

/**
 *
 */
@Repository
public interface ExamAttendanceRepo extends JpaRepository<ExamAttendance, String> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE tblexamattendance SET dttSubmitDate = GETDATE() WHERE username = ?1 AND dttSubmitDate IS NULL", nativeQuery = true)
    public void updateDttExamSubmitted(String username);

}
