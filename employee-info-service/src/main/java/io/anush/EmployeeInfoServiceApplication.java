package io.anush;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import io.anush.aoplogging.LoggingAspect;



@SpringBootApplication

@EnableEurekaClient 
 @RefreshScope

@Import(LoggingAspect.class)
public class EmployeeInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeInfoServiceApplication.class, args);
		
	}

}
