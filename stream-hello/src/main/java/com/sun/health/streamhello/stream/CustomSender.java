package com.sun.health.streamhello.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSender {
    @Output(CustomClient.OUTPUT)
    MessageChannel output();
}
