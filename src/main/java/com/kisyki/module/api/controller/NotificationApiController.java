package com.kisyki.module.api.controller;

import com.kisyki.common.BaseController;
import com.kisyki.exception.ApiException;
import com.kisyki.exception.ErrorCode;
import com.kisyki.exception.Result;
import com.kisyki.module.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationApiController extends BaseController {

//    @Autowired
//    private NotificationService notificationService;

    /**
     * 查询当前用户未读的消息数量
     *
     * @return
     */
    @GetMapping("/notRead")
    public Result notRead(String token) throws ApiException {
        User user = getUser(token);
        if (user == null) throw new ApiException(ErrorCode.notLogin, "请先登录");
//        return Result.success(notificationService.countByTargetUserAndIsRead(user, false));
        return null;
    }

}
