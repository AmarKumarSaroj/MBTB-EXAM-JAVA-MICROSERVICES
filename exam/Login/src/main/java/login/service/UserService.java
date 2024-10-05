package login.service;

import java.util.List;

import login.entity.User;

public interface UserService {
	
	public List<User> getAllUser()  ; 
    
    public User findUserProfileByJwt(String jwt); 
      
    public User findUserByUsername(String username) ; 
      
    public User findUserById(String userId) ; 
 
    public List<User> findAllUsers(); 
	
	

}
