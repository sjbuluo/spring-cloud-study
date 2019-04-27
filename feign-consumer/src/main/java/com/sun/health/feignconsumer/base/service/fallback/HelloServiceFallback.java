package com.sun.health.feignconsumer.base.service.fallback;

import com.sun.health.feignconsumer.base.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello(String name) {
        return "Fallback in HelloServiceFallback";
    }
}
