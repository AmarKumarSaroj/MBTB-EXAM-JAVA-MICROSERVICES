/**
 * 
 */
package utils;

import java.time.OffsetDateTime;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 */

public class CommonUtils {

    private String message;
    private JSONArray jsonArray;
    private String status;
    private OffsetDateTime timestamp;
    private int errorCode;
    
    public JSONObject  successReponse(Object obj,String successMessage) {
    	
    	JSONObject successObj = new JSONObject();
    	
    	successObj.put("message", successMessage != null ? successMessage : "");
    	successObj.put("status",true);
    	successObj.put("body",  obj != null  ? obj : null);
    	successObj.put("timestamp", timestamp.now());
    	successObj.put("StatusCode", 200);
    	
		return successObj;
    		
    }
    
    
    public JSONObject  failureReponse(Object obj,int errorCode, String errorMessage) {
    	
    	JSONObject failureObj = new JSONObject();
    	
    	failureObj.put("message", errorMessage != null ? errorMessage : "");
    	failureObj.put("body",  obj != null  ? obj : null);
    	failureObj.put("timestamp", timestamp.now());
    	failureObj.put("errorCode", errorCode);
    	
		return failureObj;
    		
    }
    
    }
