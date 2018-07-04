package com.thoughtworks.repos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thoughtworks.repos.util.CoverageIgnore;

/**
 * Main class to start the application 
 * @author douglas.mateus
 * 
 */
@SpringBootApplication
public class Application {

	/**
	 * This method must be used by the line command: mvn spring-boot:run
	 * @see README.md for more information
	 * @param args
	 */
	@CoverageIgnore
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
