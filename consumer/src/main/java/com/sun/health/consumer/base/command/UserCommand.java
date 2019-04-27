package com.sun.health.consumer.base.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.sun.health.consumer.base.entity.User;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("executeUser");

    private RestTemplate restTemplate;

    private int id;

    public UserCommand(Setter setter, RestTemplate restTemplate, int id) {
        super(setter.andCommandKey(HystrixCommandKey.Factory.asKey("executeUser")).withGroupKey(HystrixCommandGroupKey.Factory.asKey("userGroup")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("executeUserThread")));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForEntity("http://USER-SERVICE/users/{1}", User.class, id).getBody();
    }

    @Override
    protected User getFallback() {
        return null;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void flushCache(int id) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
