
package questions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import questions.ConstantsUtils;
import questions.model.*;
import questions.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QuestionsController {

    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private StudentExamResponseRepo examResponseRepo;

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private ExamAttendanceRepo attendanceRepo;
    

    @Transactional
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> questionsShow(@PathVariable("id") int id, @RequestBody UserInfo user) {
       
    	Map<String, Object> map = new HashMap<>();
        String username = user.getUsername();
        String coursecode = user.getCoursecode();
        
        int questStatus = examResponseRepo.getQuestionsById(username);
        
        if (questStatus == 0) {
            
            ExamAttendance attendance = new ExamAttendance.Builder().setUsername(username).setChrAttendance("P").setDttAttemptDate(new Date()).build();
            	attendanceRepo.save(attendance);
        	
            	examResponseRepo.setQuestionInResponse(username.trim(), coursecode.trim());
            	
                if (id == 1) {
                	examResponseRepo.updateQuestFirst(username);                    
                }
                
                examResponseRepo.updateLimitQuest(username);
               
        		List<Map<String, Object>> questionDetails = questionsRepo.getQuestionByParams(username, id, coursecode);
        		Map<String, Object> emptymap = new HashMap<>();

        		emptymap.put("question", questionDetails.get(0).get("Question"));
        		emptymap.put("questid", questionDetails.get(0).get("questid"));  
        		emptymap.put("options", questionDetails.stream().map(option -> {
        		    	Map<String, Object> newOption = new HashMap<>(option);
        		    	newOption.remove("questid");
            		    newOption.remove("Question");
            		    return newOption;
            		    
            		        }).collect(Collectors.toList()));
        		 
              //map.put("executeExamTime", examResponseRepo.getDoneExamTime(username, coursecode));
    		    map.put("Questions", emptymap);
                map.put("totalquest", new ConstantsUtils().MAX_QUEST);
                questionDetails.clear();
            
        } else {
        	
        	if (questStatus >= id && id > 0) {
        		
        		List<Map<String, Object>> questionDetails = questionsRepo.getQuestionByParams(username, id, coursecode);

        		Map<String, Object> emptymap = new HashMap<>();
        			emptymap.put("question", questionDetails.get(0).get("Question"));
        		    emptymap.put("questid", questionDetails.get(0).get("questid"));
        		    
        		    emptymap.put("options", questionDetails.stream().map(option -> {Map<String, Object> newOption = new HashMap<>(option);newOption.remove("questid");
            		            newOption.remove("Question");
            		            return newOption;
            		        }).collect(Collectors.toList()));
        		 
              //map.put("executeExamTime", examResponseRepo.getDoneExamTime(username, coursecode));
                
        		map.put("Questions", emptymap);
                map.put("totalquest", questStatus);
                questionDetails.clear();
                
            } else {
            	
                map.put("message", "Questions id should be Between 1 to " + questStatus);
            
            }
        }

        return ResponseEntity.ok(map);
    }

}