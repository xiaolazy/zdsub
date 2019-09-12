package com.zdsub.component.token;

import jdk.nashorn.internal.objects.Global;

import java.util.Hashtable;
import java.util.List;

/**
 * @program: zdsub
 * @description: 存储TOKEN
 * @author: lyy
 * @generate: 2019-09-11 09:15
 **/
public class TokenBean<K extends Object,V extends Object> extends Hashtable {
    private TokenBean(){}
    public static TokenBean getInstance(){
        return build.BUILDER.builder;
    }
    private enum build {
        BUILDER;
        private TokenBean builder;
        build(){
            builder = new TokenBean();
        }
    }
    public static ThreadLocal<String> activeUser = new ThreadLocal<>();
    public static ThreadLocal<String> activeUserId = new ThreadLocal<>();
}
