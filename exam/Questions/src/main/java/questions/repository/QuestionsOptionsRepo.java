/**
 *
 */
package questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import questions.model.QuestionsOptions;

/**
 *
 */
@Repository
public interface QuestionsOptionsRepo extends JpaRepository<QuestionsOptions, String> {


}
