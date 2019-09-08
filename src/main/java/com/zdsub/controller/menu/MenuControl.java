package com.zdsub.controller.menu;

import com.zdsub.common.ResultBean.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 15:45
 **/
@RestController
@RequestMapping("/menu/")
public class MenuControl {
    @GetMapping("menuTree")
    public ResponseBean menuTree(HttpServletRequest req){
        return null;
    }

}
