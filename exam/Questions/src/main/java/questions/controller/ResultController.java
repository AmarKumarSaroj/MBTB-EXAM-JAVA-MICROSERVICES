package questions.controller;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.websocket.server.PathParam;
import questions.model.ExamAttendance;
import questions.model.StudentExamResponse;
import questions.model.UserInfo;
import questions.repository.ExamAttendanceRepo;
import questions.repository.StudentExamResponseRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/")
public class ResultController {
	
	
	@Autowired
	StudentExamResponseRepo examResponseRepo;
	

	@Autowired
	ExamAttendanceRepo attendanceRepo;
	
	
	@PostMapping("/result")
	public Map<String, Object> getResult(@RequestBody UserInfo user) {
		
		String  username = user.getUsername();
		String coursecode = user.getCoursecode();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
        int totalQuestions = examResponseRepo.getTotalQuestionsCount(username,coursecode);
        int attemptQuestions = examResponseRepo.getAttemptQuestionsCount(username,coursecode);
        int skipped = (totalQuestions - attemptQuestions);
        int correctQuestions = examResponseRepo.currectQuestion(username,coursecode);
        System.out.println(correctQuestions + " correctQuestions");
        int percentage = ((correctQuestions * 100 / totalQuestions));
		
		map.put("TotalQuestions", totalQuestions);
		map.put("Attempted", attemptQuestions);
		map.put("Skipped", skipped);
		map.put("CorrectQuestions", correctQuestions);
		map.put("Percentage", percentage+"%");
		
        attendanceRepo.updateDttExamSubmitted(username);
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
		return map;
		
	}
	
	@GetMapping("/exam-status/{id}")
	public Map<String, Object> examStatus(@PathVariable String id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int examvalue = examResponseRepo.examStatus(id);
		
		map.put("examStatus", examvalue);
		
		return map;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
