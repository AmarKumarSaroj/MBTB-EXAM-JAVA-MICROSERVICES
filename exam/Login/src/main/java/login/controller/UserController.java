package login.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import login.config.JwtProvider;
import login.entity.AuthResponse;
import login.entity.User;
import login.repository.UserRepository;
import login.service.Impl.UserServiceImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceImpl customUserDetails;

	@Autowired
	private UserRepository repository;
	
	
	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@GetMapping("/")
	public String getMethodName() {

		return "Welcome";
	}

	@Async
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody User loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		logger.info("username : " + username + " " + "password : " + password + " ");

		System.out.println("username " + username + "-------" + "password" + password);

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = JwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		logger.info("username : " + username + " " + "password : " + password + " " + "Error Type :" + authResponse);

		// Fetch user details from the database
		User authenticatedUser = userRepository.findByUsername(username);
		if (authenticatedUser == null) {

			logger.info("user not present !");

			authResponse.setMessage("User not define");
			authResponse.setStatus(false);

			return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);

		}

		User user = repository.findByUsername(username);
		authResponse.setMessage("Login success");
		authResponse.setFirstname(authenticatedUser.getFirstname());
		authResponse.setMiddlename(authenticatedUser.getMiddlename());
		authResponse.setLastname(authenticatedUser.getLastname());
		authResponse.setUsername(username.trim());
		authResponse.setCourseid(user.getCourseid().trim());
		authResponse.setJwt(token);
		authResponse.setStatus(true);

		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("password")) {

			authResponse.setAuthorisation("admin");

		} else {
			
			RestTemplate restTemplate = new RestTemplate();

		        // Define the URL
		    String url = "https://sdvtii.in/api-question/api/exam-status/"+username;
			
			Object obj =  restTemplate.exchange(url, HttpMethod.GET,null,Object.class).getBody();
			
			// Cast Object to Map
	        Map<String, Object> responseMap = (Map<String, Object>) obj;
	        
	        // Extract the examStatus value
	        Integer examStatus = (Integer) responseMap.get("examStatus");
	        
	        // Print the examStatus
	        System.out.println(examStatus);
		
			authResponse.setAuthorisation(examStatus.toString());
			
		}
		 // Create a RestTemplate instance
		
		logger.info("AuthResponse : " + authResponse);





		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}

	@Async
	private Authentication authenticate(String username, String password) {

		System.out.println(username + "---++----" + password);

		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("Sig in in user details" + userDetails);
		logger.info("Authentication 1/3: " + userDetails);

		if (userDetails == null) {

			System.out.println("Sign in details - null " + userDetails);
			logger.info("Authentication 2/3: " + "Sign in details - null");

			throw new BadCredentialsException("Invalid username and password");

		}

		if (!userDetails.getPassword().trim().equalsIgnoreCase(password.trim())) {

			System.out.println("Sign in userDetails - password mismatch" + userDetails);
			logger.info("Authentication 3/3: " + "Sign in userDetails - password mismatch");

			throw new BadCredentialsException("Invalid password");

		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	}





	/*
	
	
	*/

}