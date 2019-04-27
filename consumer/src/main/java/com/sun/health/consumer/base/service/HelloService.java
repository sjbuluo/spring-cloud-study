package com.sun.health.consumer.base.service;

import com.sun.health.consumer.base.entity.User;
import rx.Observable;

import java.util.List;

public interface HelloService {
    String helloService(String name);
    String error(String name);

    User executeUser(String id);

    String executeUserCacheKey(String id);

    User update(String id);

    Observable<User> observe(String id);

    Observable<User> toObservable(String id);

    User errorUser(String id, Throwable e);

}
