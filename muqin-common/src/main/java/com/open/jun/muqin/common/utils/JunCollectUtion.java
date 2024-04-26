package com.open.jun.muqin.common.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Collection;
import java.util.Map;

/***
 *@description: 集合工具类
 *@author: jun
 *version: 1.0
 *time: 2023/3/5 7:00
 **/
public final class JunCollectUtion {

    public static <E extends Collection> Boolean isEmpty(E e){
        return CollectionUtil.isEmpty(e);
    }

    public static <E extends Collection> Boolean isNotEmpty(E e){
        return CollectionUtil.isNotEmpty(e);
    }


    public static <M extends Map> Boolean isEmpty(M m){
        return CollectionUtil.isEmpty(m);
    }

    public static <M extends Map> Boolean isNotEmpty(M m){
        return CollectionUtil.isNotEmpty(m);
    }

}
