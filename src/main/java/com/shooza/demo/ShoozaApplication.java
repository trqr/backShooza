package com.shooza.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ShoozaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoozaApplication.class, args);
	}

}
