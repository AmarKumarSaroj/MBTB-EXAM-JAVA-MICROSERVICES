
package questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * @SpringBootApplication
 *
 * @ComponentScan
 *
 */
@ComponentScan
@CrossOrigin("*")
@SpringBootApplication
public class QuestionsMain extends SpringBootServletInitializer {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(QuestionsMain.class, args);

    }


    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(QuestionsMain.class);
    }

}
