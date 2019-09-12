package com.zdsub.component.filter;


import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.utils.Base64Util;
import com.zdsub.utils.ExceptionUtil;
import com.zdsub.utils.Jwt;

import static com.zdsub.utils.ExceptionUtil.*;

import com.zdsub.utils.SpringUtil;
import io.jsonwebtoken.Claims;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.*;

import static com.zdsub.common.constant.Common.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @program: zdsub
 * @description: 判断Token是否过时与后台登录的过滤器
 * @author: lyy
 * @generate: 2019-09-06 20:11
 **/
public class AuthenticateFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }
        String subject = "";
        Date expiration = null;
        String servletPath = request.getServletPath();
        String protalURL = protalURL(servletPath);
        String authorization = request.getHeader(AUTHORIZATION);
        if (servletPath.equals(LOINGURL) || protalURL.equals(ROOT) || servletPath.equals(REGISTERURL) || protalURL.equals(PORTALURL))
            filterChain.doFilter(request, response);
        else if (protalURL.equals(PORTALURL) && StringUtils.isNotBlank(authorization)) {
            Claims claims = authenticate(authorization);
            if (!permission(protalURL)){
                response.setStatus(USER_NOT_PERMISSION);
                return;
            }
            if (claims == null)
                response.setStatus(USER_NOT_LOGIN);
            else {
                setAndUpdateJwt(claims);
                filterChain.doFilter(request, response);
            }
        } else if (!protalURL.equals(PORTALURL)) {
            Claims claims = authenticate(authorization);
            if (!permission(protalURL)){
                response.setStatus(USER_NOT_PERMISSION);
                return;
            }
            if (null == claims)
                response.setStatus(USER_NOT_LOGIN);
            else {
                try {
                    subject = claims.getSubject();
                    expiration = claims.getExpiration();
                } catch (java.lang.NullPointerException n) {
                    TokenBean.getInstance().remove(authorization);
                    response.setStatus(USER_NOT_LOGIN);
                }
                if (new Date().before(expiration)) {
                    ManagerService managerService = SpringUtil.getBean(ManagerService.class);
                    Manager m = managerService.findById(subject);
                    if (null != m) {
                        setAndUpdateJwt(claims);
                        filterChain.doFilter(request, response);
                    } else {
                        TokenBean.getInstance().remove(authorization);
                        response.setStatus(USER_NOT_LOGIN);
                    }
                } else {
                    TokenBean.getInstance().remove(authorization);
                    response.setStatus(USER_NOT_LOGIN);
                }
            }
        } else {
            logger.error("路径" + servletPath + "非法访问被拦截");
            response.setStatus(USER_NOT_LOGIN);
        }

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
            return url.substring(SUBSTRING_LENG_START, SUBSTRING_LENG_END);
    }

    /*@description：认证
     *@Date：2019/9/11 10:37
     *@Param
     *@Return
     *@Author lyy
     */
    private static Claims authenticate(String authorization) {
        if (isBlank(authorization))
            return null;
        String token = (String) TokenBean.getInstance().get(authorization);
        if (isBlank(token))
            return null;
        Claims claims = Jwt.parseJWT(token, Base64Util.Encoder(SALT));
        if (isNull(claims))
            return null;
        return claims;
    }

    /*@description：更新TOKEN时间
     *@Date：2019/9/11 11:14
     *@Param
     *@Return
     *@Author lyy
     */
    private static void upTokenTime(String key, String val) {
        TokenBean.getInstance().put(key, val);
    }

    /*@description：设置并创建JWT
     *@Date：2019/9/11 16:08
     *@Param
     *@Return
     *@Author lyy
     */
    private static void setAndUpdateJwt(Claims claims) {
        String token = Jwt.createJWT(claims.getIssuer(), claims.getSubject(),
                claims.getIssuer(), USER_LOGIN_TIME, Base64Util.Encoder(SALT));
        TokenBean.activeUser.set(claims.getIssuer());
        TokenBean.activeUserId.set(claims.getSubject());
        upTokenTime(token, token);
    }

    /*@description：权限认证，拦截非法的REQUEST
     *@Date：2019/9/12 15:44
     *@Param：
     *@Return：
     *@Author： lyy
     */
    private static boolean permission(String protalURL) {
        protalURL = protalURL.substring(protalURL.indexOf("/")+1,protalURL.length());
        if(TokenBean.activePermission.get() == null)
            return false;
        for (String url : TokenBean.activePermission.get()) {
            if (url.equals(protalURL))
                return true;
        }
        return false;
    }
}
