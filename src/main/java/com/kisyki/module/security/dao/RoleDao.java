package com.kisyki.module.security.dao;

import com.kisyki.module.security.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@Repository
@CacheConfig(cacheNames = "roles")
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Cacheable
    Role findOne(int id);

    @Cacheable
    List<Role> findAll();

    void delete(int id);
}
