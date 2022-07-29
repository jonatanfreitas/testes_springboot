package com.financeiroteste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.financeiroteste.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class FinanceirotesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceirotesteApplication.class, args);
	}

}
