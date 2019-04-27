package com.sun.health.streamhello.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

//    @Autowired
//    private CustomClient customClient;

    @Autowired
    private CustomSender sender;

    @RequestMapping("/send")
    public String send() {
//        customClient.output().send(MessageBuilder.withPayload("some msg").build());
        sender.output().send(MessageBuilder.withPayload("some msg").build());
        return "ok";
    }

}
