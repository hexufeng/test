package com.kisyki.module.user.controller;

import com.kisyki.common.BaseController;
import com.kisyki.common.config.SiteConfig;
import com.kisyki.module.security.entity.Role;
import com.kisyki.module.security.service.RoleService;
import com.kisyki.module.user.entity.User;
import com.kisyki.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController extends BaseController {

    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     *
     * @param p
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Integer p, Model model) {
        model.addAttribute("page", userService.pageUser(p == null ? 1 : p, siteConfig.getPageSize()));
        return null;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
//    @GetMapping(value = "/{id}/delete")
//    public String delete(@PathVariable Integer id, HttpServletResponse response) {
//        userService.deleteById(id);
//        return redirect(response, "/admin/user/list");
//    }

    /**
     * 禁用用户
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/{id}/block")
    public String block(@PathVariable Integer id, HttpServletResponse response) {
        userService.blockUser(id);
        return null;
    }

    /**
     * 解禁用户
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/{id}/unblock")
    public String unblock(@PathVariable Integer id, HttpServletResponse response) {
        userService.unBlockUser(id);
        return null;
    }

    /**
     * 配置用户的角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/role")
    public String role(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return null;
    }

    /**
     * 保存配置用户的角色
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}/role")
    public String saveRole(@PathVariable Integer id, Integer[] roleIds, HttpServletResponse response) {
        User user = userService.findById(id);
        Set<Role> roles = new HashSet<>();
        for (int i : roleIds) {
            Role role = roleService.findById(i);
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
        return null;
    }

}
