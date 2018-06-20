package com.jyhuang.chapter03.service;

import com.jyhuang.chapter03.jpa.UserJPA;
import com.jyhuang.chapter03.model.User;
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

	@Transactional(propagation = Propagation.REQUIRED)
	public User saveOrUpdate(User user) {
		return userJPA.save(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(Long id) {
		userJPA.deleteById(id);
	}
}
