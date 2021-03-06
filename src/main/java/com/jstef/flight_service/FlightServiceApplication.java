package com.jstef.flight_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.jstef.flight_service.Entity")
public class FlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightServiceApplication.class, args);
	}

}
