package com.example.cartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAsync
@ServletComponentScan
@SpringBootApplication
@EnableCaching
public class CartServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServerApplication.class, args);
	}

}
