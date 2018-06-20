package com.jyhuang.chapter03.jpa;

import com.jyhuang.chapter03.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA
		extends JpaRepository<User, Long> {
}
