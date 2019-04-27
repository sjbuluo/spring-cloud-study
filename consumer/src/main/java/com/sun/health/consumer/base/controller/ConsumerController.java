package com.sun.health.consumer.base.controller;

import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping("/hello/{name}")
//    public String hello(@PathVariable("name") String name) {
//        logger.info("Consumer 请求 Spring Cloud中HelloService的hello服务 参数name: {}", name);
//        return restTemplate.getForEntity("http://HELLO-SERVICE/hello/" + name, String.class).getBody();
//    }

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        logger.info("Consumer 请求 Spring Cloud中HelloService的hello服务 参数name: {}", name);
        return helloService.helloService(name);
    }

    @RequestMapping("/user/exec/{id}")
    @ResponseBody
    public User execUser(@PathVariable("id") String id) {
        User u1 = helloService.executeUser(id);
        User u2 = helloService.executeUser(id);
        User u3 = helloService.executeUser(id);
        User u4 = helloService.executeUser(id);
        User u5 = helloService.executeUser(id);
        return helloService.executeUser(id);
    }

    @RequestMapping("/user/update/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") String id) {
        return helloService.update(id);
    }

    @RequestMapping("/user/observe/{id}")
    @ResponseBody
    public Observable<User> observeUser(@PathVariable("id") String id) {
        return helloService.observe(id);
    }

    @RequestMapping("/user/observable/{id}")
    @ResponseBody
    public Observable<User> observableUser(@PathVariable("id") String id) {
        return helloService.toObservable(id);
    }

}
