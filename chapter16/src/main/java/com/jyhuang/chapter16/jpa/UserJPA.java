package com.jyhuang.chapter16.jpa;

import com.jyhuang.chapter16.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User , Long> {
}
