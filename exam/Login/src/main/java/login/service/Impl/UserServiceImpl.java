package login.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import login.entity.User;
import login.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		
		if(user==null) {
			
			throw new UsernameNotFoundException("User Not found with email" +username);
		}
		
	
		
		
		 List<GrantedAuthority> authorities = new ArrayList<>(); 
	        return new org.springframework.security.core.userdetails.User( 
	                user.getUsername(), 
	                user.getPassword(), 
	                authorities); 
	        
	        
	}
	
}
