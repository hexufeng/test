package com.kisyki.module.security.service;

import com.kisyki.module.security.dao.RoleDao;
import com.kisyki.module.security.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有的角色
     *
     * @return
     */
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteById(Integer id) {
        roleDao.delete(id);
    }

    /**
     * 根据id查找角色
     *
     * @param id
     * @return
     */
    public Role findById(int id) {
        return roleDao.findOne(id);
    }

    public void save(Role role) {
        roleDao.save(role);
    }

}
