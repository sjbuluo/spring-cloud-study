package com.sun.health.streamhello.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomClient {

    String INPUT = "customInput";

    String OUTPUT = "customOutput";

    @Input(CustomClient.INPUT)
    SubscribableChannel input();

    @Input(CustomClient.OUTPUT)
    MessageChannel output();

}
