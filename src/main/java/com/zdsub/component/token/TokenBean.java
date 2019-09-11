package com.zdsub.component.token;

import java.util.Hashtable;

/**
 * @program: zdsub
 * @description: 存储TOKEN
 * @author: lyy
 * @generate: 2019-09-11 09:15
 **/
public class TokenBean<K,V> extends Hashtable {
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
}
