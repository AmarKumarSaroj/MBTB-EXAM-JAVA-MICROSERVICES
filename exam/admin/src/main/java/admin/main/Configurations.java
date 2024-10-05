/**
 * 
 */
package admin.main;

import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 */
@Configuration
public class Configurations {

	@Value("${spring.datasource.url}")
	private String springDataSourceUrl;
	
	@Value("${spring.datasource.username}")
	private String springDataSourceUsername;
	
	@Value("${spring.datasource.password}")
	private String springDatasourcePassword;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setUrl(springDataSourceUrl);
        dataSource.setUsername(springDataSourceUsername);
        dataSource.setPassword(springDatasourcePassword);
        return dataSource;
    }
    
    
    

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
