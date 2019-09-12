package com.zdsub.component.token;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @program: zdsub
 * @description:TOKEN的权限存储器
 * @author: lyy
 * @generate: 2019-09-12 17:44
 **/
public class TokenPermission<T extends Object> extends HashSet<T> {
    private TokenPermission(){}
    public static TokenPermission getInstance(){
        return build.BUILD.builder;
    }
    private enum build{
        BUILD;
        private TokenPermission builder;
       build(){builder = new TokenPermission<>();}
    }
}
