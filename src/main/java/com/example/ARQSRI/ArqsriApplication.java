package com.example.ARQSRI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ArqsriApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArqsriApplication.class, args);
	}

}
