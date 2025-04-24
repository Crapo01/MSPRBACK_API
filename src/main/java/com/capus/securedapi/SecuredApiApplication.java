package com.capus.securedapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication

public class SecuredApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuredApiApplication.class, args);
	}

}
