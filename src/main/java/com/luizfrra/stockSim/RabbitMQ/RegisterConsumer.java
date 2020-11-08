package com.luizfrra.stockSim.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserRegister.UserRegisterDetails;
import com.luizfrra.stockSim.Services.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegisterConsumer {

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserService userService;

    @RabbitListener(queues = {"stockSim"}, concurrency = "1")
    public void receivedMessageFromStockSimQueue(String message) throws Exception {

        log.info("Received Register Event From Keycloak");

        UserRegisterDetails userRegisterDetails = objectMapper.readValue(message, UserRegisterDetails.class);

        log.info("Parsing User: " + userRegisterDetails.getUserId());

        User user = new User(userRegisterDetails.getUserId(), userRegisterDetails.getEmail());

        log.info("Saving User: " + userRegisterDetails.getUserId());
        userService.save(user);

        log.info("User Saved: " + userRegisterDetails.getUserId());

    }
}
