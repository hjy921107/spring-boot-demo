package com.jyhuang.chapter06.jpa;

import com.jyhuang.chapter06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJPA extends JpaRepository <User, Long>, JpaSpecificationExecutor<User> {
}
