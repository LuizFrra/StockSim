package com.luizfrra.stockSim;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockSimApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public ModelMapper modelMapper() { return new ModelMapper(); }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return  builder.build();
	}
}
