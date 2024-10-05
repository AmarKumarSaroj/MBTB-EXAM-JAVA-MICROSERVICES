package login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import login.controller.UserController;


@ComponentScan
@CrossOrigin("*")
@SpringBootApplication
public class Login extends SpringBootServletInitializer{

	public static void main(String[] args) {
		
		SpringApplication.run(Login.class, args);
		
	}
	
    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Login.class);
    }


}
