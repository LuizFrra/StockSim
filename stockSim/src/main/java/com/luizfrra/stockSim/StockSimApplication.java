package com.luizfrra.stockSim;

import com.luizfrra.stockSim.HGBrasil.HGAPIConsumer;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockSimApplication {

	private final Logger logger = LoggerFactory.getLogger(StockSimApplication.class);

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

	@Bean public HGAPIConsumer hgapiConsumer(RestTemplate restTemplate) {
		HGAPIConsumer hgapiConsumer = new HGAPIConsumer();
		hgapiConsumer.restTemplate = restTemplate;
		return hgapiConsumer;
	}
}
