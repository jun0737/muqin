package com.open.jun.muqin.common.utils;


import org.apache.commons.lang3.StringUtils;

/***
 *@description: 自定义字符串工具类
 *@author: jun
 *version: 1.0
 *time: 2023/2/11 17:37
 **/
public final class JunStringUtils {

    public static final String EMPTY = StringUtils.EMPTY;

    private JunStringUtils() {
    }

    public static Boolean isBlack(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        return StringUtils.isBlank(str.trim());
    }

    public static Boolean notBlack(String str) {
        if (StringUtils.isNotBlank(str)) {
            return StringUtils.isNotBlank(str.trim());
        }
        return false;
    }

}
