package com.kisyki.module.api.controller;

import com.kisyki.common.BaseController;
import com.kisyki.common.config.SiteConfig;
import com.kisyki.exception.ApiException;
import com.kisyki.exception.Result;
import com.kisyki.module.user.entity.User;
import com.kisyki.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private SiteConfig siteConfig;

    /**
     * 用户首页接口
     * @param username
     * @return
     * @throws ApiException
     */
    @GetMapping("/{username}")
    public Result profile(@PathVariable String username) throws ApiException {
        User user = userService.findByUsername(username);
        if (user == null) throw new ApiException("用户名不存在");
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return Result.success(map);
    }

    /**
     * 用户发布的所有话题
     *
     * @param username
     * @return
     */
    @GetMapping("/{username}/topics")
    public Result topics(@PathVariable String username, Integer p) throws ApiException {
        User user = userService.findByUsername(username);
        if (user == null) throw new ApiException("用户名不存在");
//        Page page = topicService.findByUser(p == null ? 1 : p, siteConfig.getPageSize(), user);
//        return Result.success(page);
        return null;
    }

    /**
     * 用户发布的所有回复
     *
     * @param username
     * @return
     */
    @GetMapping("/{username}/replies")
    public Result replies(@PathVariable String username, Integer p) throws ApiException {
        User user = userService.findByUsername(username);
        if (user == null) throw new ApiException("用户名不存在");
//        Page page = replyService.findByUser(p == null ? 1 : p, siteConfig.getPageSize(), user);
//        return Result.success(page);
        return null;
    }

    /**
     * 用户收藏的所有话题
     *
     * @param username
     * @return
     */
    @GetMapping("/{username}/collects")
    public Result collects(@PathVariable String username, Integer p) throws ApiException {
        User user = userService.findByUsername(username);
        if (user == null) throw new ApiException("用户名不存在");
//        Page page = collectService.findByUser(p == null ? 1 : p, siteConfig.getPageSize(), user);
//        return Result.success(page);
        return null;
    }

}
