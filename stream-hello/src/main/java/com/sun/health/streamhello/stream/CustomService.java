package com.sun.health.streamhello.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding({CustomReceiver.class, CustomSender.class})//({CustomClient.class})
public class CustomService {

    private Logger logger = LoggerFactory.getLogger(CustomService.class);

    @StreamListener(CustomClient.INPUT)
    public void processInput(String payload) {
        logger.info("CustomService receive: " + payload);
    }

//    @StreamListener(CustomClient.OUTPUT)
//    public void processOutput(String payload) {
//        logger.info("CustomService output: " + payload);
//    }

}
