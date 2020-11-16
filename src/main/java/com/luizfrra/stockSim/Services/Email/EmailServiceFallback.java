package com.luizfrra.stockSim.Services.Email;

import com.luizfrra.stockSim.EntitiesDomain.Message.Message;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailServiceFallback implements FallbackFactory<IEmailService> {

    @Override
    public IEmailService create(Throwable cause) {
        return new IEmailService() {
            @Override
            public String sendEmail(Message message) {
                log.info("unavailable Resource.");
                return null;
            }
        };
    }
}
