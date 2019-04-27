package com.sun.health.helloserivce.base.controller;

import com.sun.health.helloserivce.base.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/users")
    public List<User> findAll(Integer[] ids) {
        User[] users = new User[ids.length];
        for (int i = 0; i < ids.length; i++) {
            User user = new User();
            user.setId(ids[i]);
            user.setName("p: " + i);
            users[i] = user;
        }
        return Arrays.asList(users);
    }

}
