package com.kisyki.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
@ConfigurationProperties(prefix = "global")
public class SiteConfig {

    private Integer pageSize;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
