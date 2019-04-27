package com.sun.health.streamhello.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SenderTest {

    @Autowired
    private SinkSender sender;

    @Test
    public void test1() {
        sender.output().send(MessageBuilder.withPayload("From SinkSender").build());
        System.out.println(123);
    }

}
