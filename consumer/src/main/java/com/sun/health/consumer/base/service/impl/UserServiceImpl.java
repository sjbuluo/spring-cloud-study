package com.sun.health.consumer.base.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public User find(Long id) {
        return null;
    }

    @Override
    @HystrixCommand
    public List<User> findAll(List<Long> userIds) {
        List<LinkedHashMap> body = restTemplate.getForEntity("http://HELLO-SERVICE/user/users?ids={1}", List.class, StringUtils.join(userIds, ",")).getBody();
        List<User> users = new ArrayList<>();
        for (LinkedHashMap linkedHashMap : body) {
            User user = new User();
            user.setId(Integer.valueOf((String) linkedHashMap.get("id")));
            user.setName((String) linkedHashMap.get("name"));
            users.add(user);
        }
        return users;
    }
}
