package com.kisyki.module.api.controller;

import com.kisyki.common.BaseController;
import com.kisyki.exception.ApiException;
import com.kisyki.exception.Result;
import com.kisyki.module.notification.service.NotificationService;
import com.kisyki.module.reply.entity.Reply;
import com.kisyki.module.reply.service.ReplyService;
import com.kisyki.module.topic.entity.Topic;
import com.kisyki.module.topic.service.TopicService;
import com.kisyki.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@RestController
@RequestMapping("/api/reply")
public class ReplyApiController extends BaseController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/save")
    public Result save(Integer topicId, String content, String token, String editor) throws ApiException {
        User user = getUser(token);
        if(user == null) throw new ApiException("用户不存在");
        if(user.isBlock()) throw new ApiException("你的帐户已经被禁用了，不能进行此项操作");

        if(topicId == null) throw new ApiException("话题ID不能为空");

        Topic topic = topicService.findById(topicId);
        if(topic == null) throw new ApiException("话题不存在");

        if(StringUtils.isEmpty(content)) throw new ApiException("回复内容不能为空");
        if(StringUtils.isEmpty(editor)) editor = "markdown";
        if(!editor.equals("markdown") || !editor.equals("wangeditor")) editor = "markdown";

        Reply reply = new Reply();
        reply.setUser(user);
        reply.setTopic(topic);
        reply.setInTime(new Date());
        reply.setUp(0);
        reply.setContent(content);
        reply.setEditor(editor);

        replyService.save(reply);

        //回复+1
        topic.setReplyCount(topic.getReplyCount() + 1);
        topicService.save(topic);

        notificationService.sendNotification(user, topic, content, reply);

        return Result.success();
    }
}