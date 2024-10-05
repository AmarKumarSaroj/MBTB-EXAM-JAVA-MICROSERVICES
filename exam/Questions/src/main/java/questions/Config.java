package questions;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class Config {
	
	private CorsConfigurationSource corsConfigurationSource() { 
        
		return new CorsConfigurationSource() {
        	
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) { 
            	
            	CorsConfiguration ccfg = new CorsConfiguration(); 
            	ccfg.setAllowedOrigins(Arrays.asList("http://localhost:3000","https://sdvtii.in/")); 
                ccfg.setAllowedMethods(Collections.singletonList("*")); 
                ccfg.setAllowCredentials(true); 
                ccfg.setAllowedHeaders(Collections.singletonList("*")); 
                ccfg.setExposedHeaders(Arrays.asList("Authorization")); 
                ccfg.setMaxAge(3600L); 
                return ccfg;
 
            } 
            
        }; 
        
    }
	
}
