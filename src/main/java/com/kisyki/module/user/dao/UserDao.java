package com.kisyki.module.user.dao;

import com.kisyki.module.user.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User, Integer> {

    @Cacheable
    User findOne(int id);

    @Cacheable
    User findByUsername(String username);

    @CacheEvict
    void delete(int id);

    @Cacheable
    User findByToken(String token);

}
