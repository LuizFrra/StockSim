package com.luizfrra.stockSim.EntitiesDomain.Message;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

public class Message {

    public String to;

    public String text;

    public String subject;


    public Message(String to, String text, String subject) {
        this.to = to;
        this.text = text;
        this.subject = subject;
    }

    public SimpleMailMessage convertTOMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        return simpleMailMessage;
    }

    public boolean isValid() {
        return !(StringUtils.isEmpty(to.trim()) || StringUtils.isEmpty(text.trim()) || StringUtils.isEmpty(subject.trim()));
    }
}
