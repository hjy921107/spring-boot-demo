package com.jyhuang.chapter04.service;

import com.jyhuang.chapter04.jpa.UserJPA;
import com.jyhuang.chapter04.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService {

	@Autowired
	private UserJPA userJPA;

	public List<User> findAll() {
		return userJPA.findAll();
	}
}
