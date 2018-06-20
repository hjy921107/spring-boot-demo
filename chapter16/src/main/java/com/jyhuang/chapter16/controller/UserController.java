package com.jyhuang.chapter16.controller;

import com.jyhuang.chapter16.model.User;
import com.jyhuang.chapter16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAll")
    public List<User> findById() {
        return userService.findAll();
    }
}
