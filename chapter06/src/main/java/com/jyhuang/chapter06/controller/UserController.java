package com.jyhuang.chapter06.controller;

import com.jyhuang.chapter06.model.User;
import com.jyhuang.chapter06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public String login(User user, HttpServletRequest request) {
		if (userService.findOne(user)) {
			request.getSession().setAttribute("_session_user", user);
			return "登录成功";
		} else {
			return "用户名或密码错误！";
		}
	}
}
