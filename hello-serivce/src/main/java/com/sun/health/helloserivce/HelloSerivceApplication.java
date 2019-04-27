package com.sun.health.helloserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloSerivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSerivceApplication.class, args);
	}

}
