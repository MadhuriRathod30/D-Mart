package com.example.PickingService;

import com.example.PickingService.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PickingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickingServiceApplication.class, args);
	}

}
