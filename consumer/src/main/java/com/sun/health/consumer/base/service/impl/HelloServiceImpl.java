package com.sun.health.consumer.base.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.exception.CustomIgnoreException;
import com.sun.health.consumer.base.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String helloService(String name) {
        logger.info("发送 " + name);
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello/{1}", String.class, name).getBody();
        long end = System.currentTimeMillis();
        logger.info("Spend time: " + (end - start));
        return result;
    }

    @Override
    public String error(String name) {
        return name + "error" ;
    }

    @Override
    @CacheResult() //(cacheKeyMethod = "executeUserCacheKey")
    @HystrixCommand(fallbackMethod = "errorUser", ignoreExceptions = {CustomIgnoreException.class})//, commandKey = "executeUser", groupKey = "userGroup", threadPoolKey = "executeUserThread")
    public User executeUser(@CacheKey String id) {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/hello/users/{1}", User.class, id);
        return user;
    }

    public String executeUserCacheKey(String id) {
        return String.valueOf(id);
    }

    @Override
    @CacheRemove(commandKey = "executeUser") //, cacheKeyMethod = "executeUserCacheKey")
    @HystrixCommand
    public User update(@CacheKey String id) {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/hello/users/{1}", User.class, id);
        return user;
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorUser", observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> observe(String id) {
        return getUserObservable(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorUser", observableExecutionMode = ObservableExecutionMode.LAZY)
    public Observable<User> toObservable(String id) {
        return getUserObservable(id);
    }

    private Observable<User> getUserObservable(String id) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<User>() {

            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    User user = restTemplate.getForObject("http://HELLO-SERVICE/hello/users/{1}", User.class, id);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public User errorUser(String id, Throwable e) {
        if(e != null) {
            System.out.println("在errorUser中抛出异常");
            e.printStackTrace();
        }
        return null;
    }

}
