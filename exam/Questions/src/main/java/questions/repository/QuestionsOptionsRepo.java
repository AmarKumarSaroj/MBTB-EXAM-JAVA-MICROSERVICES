/**
 * 
 */
package questions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import questions.model.Questions;
import questions.model.QuestionsOptions;

/**
 * 
 */
@Repository
public interface QuestionsOptionsRepo extends JpaRepository<QuestionsOptions, String>  {


}
