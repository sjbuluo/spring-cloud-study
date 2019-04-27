package com.sun.health.helloserivce.base.controller;

import com.sun.health.helloserivce.base.entity.User;
import com.sun.health.helloserivce.base.exception.CustomException;
import com.sun.health.helloserivce.base.exception.CustomIgnoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        String result = "Hello " + name + "! Welcome to " + applicationName + " : " + port;
        logger.info(result);
        return result;
    }

    @RequestMapping("/users/{id}")
    public User users(@PathVariable("id") int id) throws CustomException, CustomIgnoreException {
        Random random = new Random();
        int i = random.nextInt(3);
//        if(i == 0) {
//            throw new CustomIgnoreException();
//        } else if (i == 1) {
//            throw new CustomException();
//        }
        User user = new User();
        user.setId(id);
        user.setName("sj" + random.nextInt(100));
        return user;
    }

}
