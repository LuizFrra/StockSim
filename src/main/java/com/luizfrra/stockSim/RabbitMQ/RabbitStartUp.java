package com.luizfrra.stockSim.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitStartUp {

    @Bean("emailToSend")
    Queue emailToSend() {
        return new Queue("emailToSend", true, false, false);
    }

    @Bean("emailHistory")
    Queue emailHistory() {
        return new Queue("emailHistory", true, false, false);
    }

    @Bean("stockSim")
    Queue stockSim() { return new Queue("stockSim", true, false, false); }

    @Bean
    FanoutExchange fanoutExchange()  {
        return new FanoutExchange("stocksim.email", true, false);
    }

    @Bean
    Binding bindingEmailOne(@Qualifier("emailToSend") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingEmailTwo(@Qualifier("emailHistory") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
