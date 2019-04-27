package com.sun.health.streamhello.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomServiceTest {

    private Logger logger = LoggerFactory.getLogger(CustomService.class);

    @Autowired
    private CustomClient customClient;

    @Test
    public void test1() {
        logger.info("Test In CustomServiceTest");
        customClient.output().send(MessageBuilder.withPayload("Test In CustomServiceTest").build());
    }

    @Test
    public void test2() {

    }

}
