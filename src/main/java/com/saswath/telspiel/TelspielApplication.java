package com.saswath.telspiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.saswath.telspiel" })
public class TelspielApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelspielApplication.class, args);
	}

}
