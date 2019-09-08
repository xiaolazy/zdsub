package com.zdsub.component.filter;


import com.zdsub.entity.manager.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;

import static com.zdsub.common.constant.Common.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @program: zdsub
 * @description: 判断Session是否过时与后台登录的过滤器
 * @author: lyy
 * @generate: 2019-09-06 20:11
 **/
public class SessionFilter extends OncePerRequestFilter {
    private  Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Manager manager = (Manager)session.getAttribute(session.getId());

        String servletPath = request.getServletPath();
        String protalURL = protalURL(servletPath);
        if(null != manager)
            System.out.println(manager.getUser_name()+"-------------------------");
        else
            System.out.println(9999999999999L);
        if (servletPath.equals(LOINGURL)||protalURL.equals(ROOT)||servletPath.equals(REGISTERURL)||protalURL.equals(PORTALURL))
        {
            filterChain.doFilter(request, response);
        }
        else if(null != manager){
            filterChain.doFilter(request,response);
        }

        else if (!protalURL.equals(PORTALURL) && null != manager){
            filterChain.doFilter(request, response);
        }
        else
            logger.error("路径"+servletPath+"非法访问被拦截");

    }
    /*@description：URL判断
     *@Date：2019/9/7 16:32
     *@Param url
     *@Return String
     *@Author lyy
     */
    private static String protalURL(String url) {
        if (url.length() < SUBSTRING_LENG_END)
            return ROOT;
        else
            return url.substring(SUBSTRING_LENG_START,SUBSTRING_LENG_END);
    }
}
