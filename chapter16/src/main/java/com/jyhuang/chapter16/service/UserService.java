package com.jyhuang.chapter16.service;

import com.jyhuang.chapter16.jpa.UserJPA;
import com.jyhuang.chapter16.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
@CacheConfig(cacheNames = {"user"})
public class UserService {

    @Autowired
    private UserJPA userJPA;

    @Cacheable
    public List<User> findAll() {
        return userJPA.findAll();
    }

}
