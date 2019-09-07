package com.zdsub.controller.Manager;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.service.Manager.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import java.util.List;

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
    @RequestMapping(value = "2233",produces="text/html; charset=UTF-8")
    String ha(){
        return "你妈饭";
    }
    @RequestMapping("test")
    public List<?> test(){
        String te= "22";
        if("22".equals(te))
            throw new GlobalException(888,"测试异常测试异常");
        System.out.println("5566");
        logger.debug("4444444444444");
        logger.info("ooooooooooooooo");
        logger.error("--------------------------");
        return managerService.listAll();
    }
    @RequestMapping("ss")
    public ResponseBean ss(){
        System.out.println("天123");
        logger.error("今天是个好日子");
        System.out.println("中文");
        return ResponseBean.SUCCESS("测试响应成功",managerService.findManager(""));
    }
    @RequestMapping("page")
    public ResponseBean page(){
        return ResponseBean.PAGESUCCESS(managerService.page(new Page()));
    }
}
