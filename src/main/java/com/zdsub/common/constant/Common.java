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
}
