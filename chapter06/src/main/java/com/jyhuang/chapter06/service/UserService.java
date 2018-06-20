package com.jyhuang.chapter06.service;

import com.jyhuang.chapter06.jpa.UserJPA;
import com.jyhuang.chapter06.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService {

	@Autowired
	private UserJPA userJPA;

	public boolean findOne(User user) {
		Optional<User> optionalUser = userJPA.findOne(new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
				return null;
			}
		});

		if (!optionalUser.isPresent()) { // 用户不存在，登录失败！
			return false;
		} else if (!optionalUser.get().getPassword().equals(user.getPassword())) { // 用户名或密码错误，请重新登录！
			return false;
		} else { // 登录成功！
			return true;
		}
	}
}
