package com.zdsub.component.filter;

import com.zdsub.common.constant.Common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: i-workspace
 * @description: 跨域请求处理过滤器
 * @author: lyy
 * @generate: 2019-05-14 12:25
 **/
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
             * 浏览器把CORS请求分为简单请求与非简单请求
             * 简单请求满足下列两个条件：
             * 1) 请求方法是以下三种方法之一：
             * HEAD
             * GET
             * POST
             * （2）HTTP的头信息不超出以下几种字段：
             * Accept
             * Accept-Language
             * Content-Language
             * Last-Event-ID
             * Content-Type：只限于三个值application/x-www-form-urlencoded、multipart/form-data、text/plain
         **/
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        //适配跨域域名，防止携带的cookie失效
        String origin = req.getHeader("Origin");
        //获取请求头信息，用以适配响应头
        String headers = req.getHeader("Access-Control-Request-Headers");
        System.out.println("----Origin:"+origin+"---header:"+headers);
        /**
             * 字段Access-Control-Allow-Origin非可选
             * *或者Origin字段的值---指服务器支持哪些域名可进行跨域请求
         **/
//        res.setHeader("Access-Control-Allow-Origin",getOrigin(origin));
        res.setHeader("Access-Control-Allow-Origin", "*");
        /**
             * 如果浏览器请求包括Access-Control-Request-Headers字段，
             * 则Access-Control-Allow-Headers字段是非可选的。
             * 它也是一个逗号分隔的字符串，表明服务器支持的所有头信息字段，
             * 不限于浏览器在"预检"中请求的字段。
         * */
        res.setHeader("Access-Control-Allow-Headers",
                getHeaders(headers));
        /**
             * 字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：
             * Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
             * 如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定
         * */
        res.setHeader("Access-Control-Expose-Headers",
                getHeaders(headers));
        /**
             * 字段Access-Control-Allow-Methods非可选，它的值是逗号分隔的一个字符串，
             * 表明服务器支持的所有跨域请求的方法。
             * 注意，返回的是所有支持的方法，而不单是浏览器请求的那个方法。这是为了避免多次"预检"请求。
             * 可按需指定：GET,POST,PUT,DELETE（无特殊情况时）GET,POST,PUT,DELETE
         **/
        res.setHeader("Access-Control-Allow-Methods","*");
        /**
             * 默认情况下，Cookie不包括在CORS请求之中
             * 设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。
             * 这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可
         * */
        res.setHeader("Access-Control-Allow-Credentials","true");
        chain.doFilter(request,response);
    }
    /**
        * 判断是否适配跨域域名，当不适配(请求Header为null或"")时采用* --所有域名均可请求
     * */
    private String getOrigin(String origin){
        if(null == origin || origin == "")
            origin = "*";
        return origin;
    }
    /**
        * 判断响应头是否适配自定义请求头 当不适配时(请求Header为null或"")采用指定的响应头
     * */
    private String getHeaders(String headers){
        if(null == headers || "" == headers)
            headers = "Origin,X-Requested-With,Content-Type,Accept";
        return headers;
    }
    @Override
    public void destroy() {

    }
}
