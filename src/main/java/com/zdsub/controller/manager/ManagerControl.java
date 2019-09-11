package com.zdsub.controller.manager;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.common.constant.Common;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.hibernate.PageCondition;
import com.zdsub.component.token.TokenBean;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.utils.Base64Util;
import com.zdsub.utils.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import static com.zdsub.common.constant.Common.*;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import static com.zdsub.utils.Md5Util.*;
/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 23:07
 **/
@RestController
@RequestMapping("/manager/")
public class ManagerControl {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ManagerService managerService;
    @RequestMapping("login")
    @ValidLog
    public ResponseBean login(@Valid @RequestBody ManagerInc manager, BindingResult b,
                           HttpServletResponse res){
        manager.setPass_word(Md5(manager.getPass_word()));
        Manager m = managerService.findUseLogin(manager);
        if(null == m)
            return ResponseBean.FAILD("用户名或密码错误！");
        String jwt = Jwt.createJWT(m.getUser_name(), m.getId(),
                m.getUser_name(), USER_LOGIN_TIME, Base64Util.Encoder(SALT));
        TokenBean.getInstance().put(jwt,jwt);
        res.setHeader("Access-Control-Expose-Headers", Common.AUTHORIZATION);
        res.setHeader(AUTHORIZATION,jwt);
        logger.debug(m.getUser_name()+"成功登录系统");
        return ResponseBean.SUCCESS("登录成功！",manager.getUser_name());
    }
    /*@description：带条件分页查询
     *@Date：2019/9/11 17:16
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Manager> page){
        return ResponseBean.SUCCESS(managerService.getPage(page));
    }
    /*@description：通过ID查询
     *@Date：2019/9/11 17:16
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("getById")
    public ResponseBean getById(String id){
        return ResponseBean.SUCCESS();
    }
}
