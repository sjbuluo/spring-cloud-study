package com.sun.health.streamhello.integeration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding({Sink.class})
public class SinkReceiver {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(String payload) {
        System.out.println("Receive: " + payload);
    }

}
