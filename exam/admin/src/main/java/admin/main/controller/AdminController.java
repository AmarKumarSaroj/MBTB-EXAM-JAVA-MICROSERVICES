package admin.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import admin.main.model.Student;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/")
public class AdminController {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@DeleteMapping("/deleteResponse/{username}")
	public Map<String, Object> deleteResponse(@PathVariable String username) {
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 System.out.println("deleteResponse running ");
		  restTemplate.delete("https://sdvtii.in/api-question/api/delete-response/"+username);
		  map.put("status", "Successfully Deleted !");
		return map;
	}
	
	
	
	@GetMapping("/centrelist")
	public Map<String, Object> centreList() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String query = "select distinct info.* from tblCentreInfo info inner join tblstudent st on info.chrCentrecode = st.chrCentreCode ";
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(query);
		
		List<Object> emptylist = new ArrayList<Object>();
		
		for(int i=0; i<list.size(); i++) {
		
		Map<String,Object> details = new HashMap<String, Object>();
		
		details.put("centreName ", list.get(i).get("varCentreName").toString().trim());
		details.put("ShortName ", list.get(i).get("varCentreshrtName").toString().trim());
		details.put("centreCode", list.get(i).get("chrCentrecode").toString().trim());
		
		
		emptylist.add(details);
		
	}

		
		map.put("centrelist", emptylist);
		
		System.out.println("Running ....");
		return map;
	}
	
	
	@PostMapping("/studentlist")
	public Map<String, Object> studentlist(@RequestBody Student student) {
				
		Map<String, Object> map = new HashMap<String, Object>();
		

		// System query list for 
		
		System.out.println("Startiiiiiing " + System.currentTimeMillis());

		String query = "SELECT distinct s.varFirstName, s.varMiddleName, s.varLastName, s.chrStudentRoll, CASE   WHEN ea.dttSubmitDate IS NOT NULL THEN 'Submitted'  \r\n"
				+ "			 	                 WHEN ea.dttSubmitDate IS NULL THEN 'Ongoing' \r\n"
				+ "				 	                 ELSE 'Not Attempted'  \r\n"
				+ "			                END AS AttemptStatus, ( info.chrCourseId ) as 'Course', \r\n"
				+ "				 				   concat((select count(*) from rstilrac_admin.tblmbtbquestionoptions options inner join tblstudentexamresponse res  on options.chroptionuniqueId = res.chroptionuniqueId left join tblexamattendance att on att.chrExamAttendanceCode = res.chrExamAttendanceCode  where \r\n"
				+ "			 				   options.isCorrectOption = 'Y' and res.isSelected = 1 and att.username = s.chrstudentcode),'/',(select count( distinct res.questId) from tblexamattendance att LEFT JOIN tblstudentexamresponse res on att.chrExamAttendanceCode = res.chrExamAttendanceCode where att.username = s.chrstudentcode)) as 'RESULT'\r\n"
				+ "				 	               FROM tblstudent s inner join tblCourseInfo info on s.chrCourseCode = info.chrCourseCode  \r\n"
				+ "				 	               LEFT JOIN tblexamattendance ea ON s.chrStudentRoll = ea.username  inner join tblstudentexamresponse res on ea.username = res.chrExamAttendanceCode \r\n"
				+ "				 	               WHERE s.chrCentreCode = ?"
				+ "				 	               ORDER BY s.chrStudentRoll";

		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, student.getChrCentrecode());
		
		List<Object> emptyList = new ArrayList<>();

		for (Map<String, Object> row : list) {
			
		    Map<String, Object> details = new LinkedHashMap<>();
		    
		    details.put("RollNo", row.get("chrStudentRoll").toString().trim());
		    
		    details.put("Name", row.get("varFirstName") + " " + row.get("varMiddleName") + " " + row.get("varLastName"));
		    
		    details.put("ExamStatus", row.get("AttemptStatus"));
		    
		    details.put("Course", row.get("Course"));
			
			details.put("RESULT", row.get("RESULT"));
			
			
		    emptyList.add(details);
		    
		}
		
		System.out.println("Endiiiiiing " + System.currentTimeMillis());
		
		map.put("Details", emptyList);
		System.out.println("Running .... " +map);
		return map;
		
	}
	
	
	/*
	 * details.put("Course", row.get("Course"));
	 * 
	 * details.put("RESULT", row.get("RESULT"));
	 */
	
	
	
	
	
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "1234567890123456".getBytes(); // Example key (16 bytes for AES)

    @PostMapping("fileupload")
    public ResponseEntity<Map<String, Object>> fileupload(@RequestParam("file") MultipartFile file) throws IOException {
       
            byte[] fileBytes = file.getBytes();
            byte[] encryptedBytes = encrypt(fileBytes);

            // Encode the encrypted bytes to a Base64 string
            String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

            // Create the response body with status code and message
            String responseBody = "File Size: " + file.getSize() +
                                  ", Content Type: " + file.getContentType() +
                                  ", Encrypted Data (Base64): " + encryptedString;

            Map<String, Object> map =  new LinkedHashMap<>();
            map.put("message", "Successfully Uploaded ");
            map.put("status", "true");
            map.put("Body : "," Encrypted Data (Base64): "+ encryptedString.toString());
            
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(map);
       
    }


    private byte[] encrypt(byte[] data) throws IOException {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new IOException("Encryption error", e);
        }
    }
	
    
	
	
	
	
}
