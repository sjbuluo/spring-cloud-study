package com.sun.health.consumer.base.service;

import com.sun.health.consumer.base.entity.User;

import java.util.List;

public interface UserService {

    User find(Long id);

    List<User> findAll(List<Long> userIds);

}
