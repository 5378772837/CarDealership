package com.carDealerProject.carDealerProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Lets your project know its a spring boot application
@SpringBootApplication

// YOU MUST COME AND PUT THIS LINE OF CODE IN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// Lets your project know where all your Spring Beans are
// Spring beans are your annotated classes, like controllers, services, entities, repos
@ComponentScan(basePackages = "com.carDealerProject")

public class CarDealerProjectdApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDealerProjectdApplication.class, args);
	}

}
