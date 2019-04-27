package com.sun.health.consumer.base.controller;

import com.sun.health.consumer.base.entity.User;
import com.sun.health.consumer.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/find/{id}")
    public User find(@PathVariable("id") Long id) {
        return userService.find(id);
    }

}
