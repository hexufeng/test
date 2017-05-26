package com.kisyki.common;

import com.kisyki.common.config.SiteConfig;
import com.kisyki.module.user.entity.User;
import com.kisyki.module.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
public class BaseController {

    private Logger log = Logger.getLogger(BaseController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取Security用户
     *
     * @return
     */
    protected UserDetails getSecurityUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean b = o instanceof UserDetails;
        if (b) {
            log.info(o.toString());
            return (UserDetails) o;
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    protected User getUser() {
        UserDetails userDetails = getSecurityUser();
        if (userDetails != null) {
            return userService.findByUsername(userDetails.getUsername());
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    protected User getUser(String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        } else {
            return userService.findByToken(token);
        }
    }

}
