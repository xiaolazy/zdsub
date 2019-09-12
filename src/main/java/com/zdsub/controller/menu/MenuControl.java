package com.zdsub.controller.menu;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.service.menu.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private MenuService menuService;
    @GetMapping("menuTree")
    public ResponseBean menuTree(HttpServletRequest req){
        String account = req.getRequestedSessionId();
        return ResponseBean.SUCCESS(menuService.getMenuTree(account));
    }
    @GetMapping("findNotParent")
    public ResponseBean findNotParent(){
        return ResponseBean.SUCCESS(menuService.findNotParent());
    }
}
