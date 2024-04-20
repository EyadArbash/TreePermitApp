package treePermit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"treePermit"})
@SpringBootApplication
public class Application {

	  public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	    }
}
