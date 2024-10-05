package login.entity;

import lombok.Data;

@Data
public class ApiResponse { 
    private String message; 
    private boolean status; 
    
    @Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + "]";
	}
	public ApiResponse(String string, boolean b) { 
        // TODO Auto-generated constructor stub 
    } 
    public String getMessage() { 
        return message; 
    } 
    public void setMessage(String message) { 
        this.message = message; 
    } 
    public boolean isStatus() { 
        return status; 
    } 
    public void setStatus(boolean status) { 
        this.status = status; 
    } 
    
    
  
} 
