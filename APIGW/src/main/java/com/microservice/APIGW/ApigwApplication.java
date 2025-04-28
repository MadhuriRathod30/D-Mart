package com.microservice.APIGW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.microservice.APIGW", "com/microservice/APIGW/controller"})
public class ApigwApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApigwApplication.class, args);
	}

}
