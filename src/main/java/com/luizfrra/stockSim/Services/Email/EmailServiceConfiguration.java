package com.luizfrra.stockSim.Services.Email;

import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EmailServiceConfiguration {

    @Autowired
    public CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    public FallBackEmailService fallBackEmailService;

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(IEmailService.SERVICE_NAME);
        FeignDecorators feignDecorators = FeignDecorators.builder()
                                            .withCircuitBreaker(circuitBreaker)
                                                .withFallback(fallBackEmailService)
                                                    .build();
        return Resilience4jFeign.builder(feignDecorators);
    }

}
