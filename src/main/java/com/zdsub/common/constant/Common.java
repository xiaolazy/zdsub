package com.zdsub.common.constant;

public interface Common {
    //响应 成功时的返回码
    static final int SUCCESS = 200;
    //响应 失败时的返回码
    static final int FAIL = 500;
    //用户没有登录
    static final int USER_NOT_LOGIN = 888;
    //用户密码的盐
    static final String SALT = "lyy+";
    //升序排序
    static final String ASC = "ASC";
    //降序排序
    static final String DESC = "DESC";
    //门户展示URL
    static final String PORTALURL="/home";
    //根路径
    static final String ROOT = "/";
    //登录路径
    static final String LOINGURL="/manager/login";
    //注册路径
    static final String REGISTERURL="/manager/register";
    //路径截取的长度
    static final int SUBSTRING_LENG_START = 0;
    static final int SUBSTRING_LENG_END = 5;
}
