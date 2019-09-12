package com.zdsub.component.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * @program: zdsub
 * @description:TOKEN的权限存储器
 * @author: lyy
 * @generate: 2019-09-12 17:44
 **/
public class TokenPermission<K extends Object,V extends Set> extends Hashtable {
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
