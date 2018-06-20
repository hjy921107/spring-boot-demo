package com.jyhuang.chapter04.controller;

import com.jyhuang.chapter04.model.User;
import com.jyhuang.chapter04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}
}
