package com.zdsub.common.constant;

public interface Common {
    //响应 成功时的返回码
    static final int SUCCESS = 200;
    //响应 失败时的返回码
    static final int FAIL = 500;
    //用户没有登录
    static final int USER_NOT_LOGIN = 401;
    static final int USER_NOT_PERMISSION = 402;
    //用户登录时间限制
    static final int USER_LOGIN_TIME=1800000000;
    //jwt签名
    static final String SALT = "lyy+";
    //升序排序
    static final String ASC = "ASC";
    //降序排序
    static final String DESC = "DESC";
    //门户展示URL
    static final String PORTALURL="home";
    static final String MENU="menu";
    //根路径
    static final String ROOT = "/";
    //登录路径
    static final String LOINGURL="/manager/login";
    static final String LOGOUTURL="/manager/logout";
    //注册路径
    static final String REGISTERURL="/manager/register";
    static final String LOGOUT = "logout";
    //接收的域名
    static final String ORIGIN = "http://182.92.64.223:3000";
    //路径截取的长度
    static final int SUBSTRING_LENG_START = 1;
    static final int SUBSTRING_LENG_END = 5;
    //认证请求头
    static final String AUTHORIZATION="Authorization";
    //上传文件路径
    static final String UPLOAD_PATH = "../upload/policyfile/";
}
