package com.abhiroop.kubetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KubetimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubetimeApplication.class, args);
		System.out.println("App starts");
	}

	
}
