package com.jyhuang.chapter05.jpa;

import com.jyhuang.chapter05.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, Long> {

}
