package com.jyhuang.chapter03.controller;

import com.jyhuang.chapter03.model.User;
import com.jyhuang.chapter03.service.UserService;
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

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public User save(User user) {
		return userService.saveOrUpdate(user);
	}

	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	public List<User> deleteById(Long id) {
		userService.deleteById(id);
		return userService.findAll();
	}
}
