package com.sun.health.consumer.base.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.service.HelloService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<User>> {

    private HelloService helloService;

    private List<String> ids;

    public UserBatchCommand(HelloService helloService, List<String> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.helloService = helloService;
        this.ids = ids;
    }

    @Override
    protected List<User> run() throws Exception {
//        return helloService.findAddUsers(ids);
        return null;
    }
}
