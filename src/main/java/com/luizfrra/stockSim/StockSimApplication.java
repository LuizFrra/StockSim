package com.luizfrra.stockSim;

import com.luizfrra.stockSim.HGBrasil.HGAPIConsumer;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
public class StockSimApplication {

	private final Logger logger = LoggerFactory.getLogger(StockSimApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockSimApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return  builder.build();
	}

	@Bean
	public HGAPIConsumer hgapiConsumer(RestTemplate restTemplate) {
		HGAPIConsumer hgapiConsumer = new HGAPIConsumer();
		hgapiConsumer.restTemplate = restTemplate;
		return hgapiConsumer;
	}

}
