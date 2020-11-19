package com.luizfrra.stockSim.Services.Email;

import com.luizfrra.stockSim.EntitiesDomain.Message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FallBackEmailService implements IEmailService {
    @Override
    public String sendEmail(Message message) {
        log.info("Unavailable Resource - Circuit Breaker Working !");
        // Aqui poderia ser utilizado um sistema para enviar a mensagem em um outro momento
        // ou um sistema de cache dependendo do objetivo do endpoint a ser utilizado
        return null;
    }
}
