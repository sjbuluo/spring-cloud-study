package com.sun.health.consumer.base.command;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.service.HelloService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserCollapseCommand extends HystrixCollapser<List<User>, User, String> {

    private HelloService helloService;

    private String id;

    private Setter setter;

    public UserCollapseCommand(HelloService helloService, String id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.setter = setter;
        this.helloService = helloService;
        this.id = id;
    }

    @Override
    public String getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, String>> collection) {
        List<String> ids = new ArrayList<>(collection.size());
        ids.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(helloService, ids);
    }

    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, String>> collection) {
        int count = 0;
        for (CollapsedRequest<User, String> userStringCollapsedRequest : collection) {
            userStringCollapsedRequest.setResponse(users.get(count++));
        }
    }
}
