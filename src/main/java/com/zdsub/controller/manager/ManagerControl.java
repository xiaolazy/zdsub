package com.zdsub.controller.manager;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.service.manager.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    ManagerService managerService;
    @RequestMapping("login")
    @ValidLog
    public ResponseBean ss(@Valid @RequestBody ManagerInc m, BindingResult b, HttpSession session,
                           HttpServletRequest req){
        m.setPass_word(Md5(m.getPass_word()));
        Manager manager = managerService.findUseLogin(m);
        if(null == manager)
            return ResponseBean.FAILD("用户名或密码错误！");
        session.setAttribute(req.getSession().getId(),manager);
        System.out.println(req.hashCode() == session.hashCode());
        System.out.println(req.getSession().getId()+"req.get----");
        System.out.println(session.getId());
        logger.debug(manager.getUser_name()+"成功登录系统");
        return ResponseBean.SUCCESS("登录成功！");
    }
}
