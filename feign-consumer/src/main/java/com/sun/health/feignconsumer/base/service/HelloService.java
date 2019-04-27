package com.sun.health.feignconsumer.base.service;

import com.sun.health.feignconsumer.base.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name);

}
