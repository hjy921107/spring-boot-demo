package com.jyhuang.chapter04.jpa;

import com.jyhuang.chapter04.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, Long> {
}
