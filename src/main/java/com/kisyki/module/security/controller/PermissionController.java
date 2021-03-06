package com.kisyki.module.security.controller;

import com.kisyki.common.BaseController;
import com.kisyki.module.security.entity.Permission;
import com.kisyki.module.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 角色列表
     *
     * @param pid
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Integer pid, Model model) {
        if (pid == null || pid == 0) {
            model.addAttribute("childPermissions", permissionService.findAllChildPermission());
            model.addAttribute("permissions", permissionService.findByPid(0));
        } else {
            model.addAttribute("childPermissions", permissionService.findByPid(pid));
            model.addAttribute("permissions", permissionService.findByPid(0));
        }
        model.addAttribute("pid", pid);
        return null;
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @GetMapping("/add")
    public String add(Integer pid, Model model) {
        model.addAttribute("pid", pid);
        model.addAttribute("permissions", permissionService.findByPid(0));
        return null;
    }

    /**
     * 保存添加的权限
     *
     * @param pid
     * @param name
     * @param description
     * @param url
     * @return
     */
    @PostMapping("/add")
    public String save(Integer pid, String name, String description, String url, HttpServletResponse response) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setPid(pid == null ? 0 : pid);
        permissionService.save(permission);
        return null;
    }

    /**
     * 跳转编辑页面
     *
     * @return
     * @Param id
     * @Param model
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("permission", permissionService.findById(id));
        model.addAttribute("permissions", permissionService.findByPid(0));
        return null;
    }

    /**
     * 更新权限
     *
     * @param id
     * @param pid
     * @param name
     * @param description
     * @param url
     * @return
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, Integer pid, String name, String description, String url, HttpServletResponse response) {
        Permission permission = permissionService.findById(id);
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setPid(pid == null ? 0 : pid);
        permissionService.save(permission);
        return null;
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, HttpServletResponse response) {
        permissionService.deleteById(id);
        return null;
    }
}
