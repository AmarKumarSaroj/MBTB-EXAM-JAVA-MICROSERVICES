/**
 *
 */
package questions.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questions.model.UserInfo;
import questions.repository.StudentExamResponseRepo;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/")
public class ResponseSaveController {


    @Autowired
    StudentExamResponseRepo examResponseRepo;

    @Autowired
    EntityManagerFactory entityManagerFactory;


    @ResponseBody
    @PostMapping("/saveResponse")
    public ResponseEntity<Map<String, Object>> saveResponse(@RequestBody UserInfo user) {

        // TODO: process POST request

        String questId = user.getQuestId();
        String optionId = user.getOptionId();
        String username = user.getUsername();


        if (optionId != null && questId != null) {

            String query = "UPDATE tblstudentexamresponse SET isSelected = CASE WHEN chrOptionUniqueId IN ('" + optionId + "') THEN 1 ELSE 0 END, "
                    + "attemptCount = CASE WHEN chrOptionUniqueId IN ('" + optionId + "') THEN attemptCount + 1 ELSE attemptCount END, "
                    + "attemptDate = CASE WHEN attemptDate IS NULL THEN GETDATE() ELSE attemptDate END, "
                    + "modifyDate = CASE WHEN modifyDate is null or chrOptionUniqueId IN ('" + optionId + "') THEN GETDATE() ELSE GETDATE() END "
                    + "WHERE chrExamAttendanceCode = '" + username + "' AND questId = '" + questId + "'";

            //System.out.println("built query updated : " + query);

            EntityManager entityManagers = entityManagerFactory.createEntityManager();
            entityManagers.getTransaction().begin();
            entityManagers.createNativeQuery(query).executeUpdate();
            entityManagers.getTransaction().commit();
            entityManagers.close();
        } else {

            String query = "update tblstudentexamresponse set isSelected = 0, "
                    + "attemptDate = CASE WHEN attemptDate IS NULL THEN GETDATE() ELSE attemptDate END, "
                    + "modifyDate = GETDATE()  "
                    + "where  chrExamAttendanceCode ='" + username + "' and questId = '" + questId + "'";

            //		System.out.println("built query updated : " + query);

            EntityManager entityManagers = entityManagerFactory.createEntityManager();
            entityManagers.getTransaction().begin();
            entityManagers.createNativeQuery(query).executeUpdate();
            entityManagers.getTransaction().commit();
            entityManagers.close();
        }


        //Return Selected Options
        /*

         * List<StudentExamResponse> listRes =
         * examResponseRepo.getDetailsBySessionId(username,checkQuestStatus);
         */

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("SelectedQuest", "");

        return ResponseEntity.status(HttpStatus.OK).body(map);

    }


    @DeleteMapping("/delete-response/{username}")
    public void deleteResponse(@PathVariable String username) {
        System.out.println("Question api calling !!");
        examResponseRepo.deleteResponse(username);

    }


}
