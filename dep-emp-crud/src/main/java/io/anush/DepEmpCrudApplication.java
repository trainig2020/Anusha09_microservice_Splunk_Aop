package io.anush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import io.anush.aoplogging.LoggingAspect;

@SpringBootApplication
@EnableEurekaClient
@Import(LoggingAspect.class)

public class DepEmpCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepEmpCrudApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
