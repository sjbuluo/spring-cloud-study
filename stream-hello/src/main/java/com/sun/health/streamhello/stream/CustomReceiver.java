package com.sun.health.streamhello.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomReceiver {
    @Input(CustomClient.INPUT)
    SubscribableChannel input();
}
