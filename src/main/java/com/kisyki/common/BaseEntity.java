package com.kisyki.common;

import com.kisyki.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by biscuit
 * Copyright (c) 2017, All Rights Reserved.
 * http://www.kisyki.com
 */
public class BaseEntity {

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public String formatDate(Date date) {
        String dateStr = "";
        if (date != null) {
            PrettyTime prettyTime = new PrettyTime(Locale.CHINA);
            dateStr = prettyTime.format(date);
        }
        return dateStr.replace(" ", "");
    }


    /**
     * 查找一段文本里以 @ 开头的字符串
     *
     * @param str
     * @return
     */
    public static List<String> fetchUsers(String pattern, String str) {
        List<String> ats = new ArrayList<>();
        if (StringUtils.isEmpty(pattern)) pattern = "@[^\\s]+\\s?";
        Pattern regex = Pattern.compile(pattern);
        Matcher regexMatcher = regex.matcher(str);
        while (regexMatcher.find()) {
            ats.add(regexMatcher.group());
        }
        return ats;
    }


    /**
     * 高亮title里的搜索关键字
     * @param q
     * @param title
     * @return
     */
    public String lightTitle(String q, String title) {
        return title.replace(q, "<b style='color: red;'>"+ q +"</b>");
    }


    public boolean isUp(int userId, String upIds) {
        return upIds != null && upIds.contains(Constants.COMMA + userId + Constants.COMMA);
    }

    public boolean isDown(int userId, String downIds) {
        return downIds != null && downIds.contains(Constants.COMMA + userId + Constants.COMMA);
    }
}
