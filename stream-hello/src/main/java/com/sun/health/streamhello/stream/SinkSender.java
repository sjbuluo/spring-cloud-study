package com.sun.health.streamhello.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {

    String OUTPUT = "output";

    @Output(Sink.INPUT)
    MessageChannel output();

}
