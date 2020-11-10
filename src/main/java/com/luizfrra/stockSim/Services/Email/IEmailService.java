package com.luizfrra.stockSim.Services.Email;

import com.luizfrra.stockSim.EntitiesDomain.Message.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("stockSim-Email")
public interface IEmailService {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/email")
    String sendEmail(@RequestBody Message message);

}
