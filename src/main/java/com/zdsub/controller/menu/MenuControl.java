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
    /*@description：根据当前用户权限查询菜单树
     *@Date：2019/9/12 13:10
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("menuTree")
    public ResponseBean menuTree(){
        return ResponseBean.SUCCESS(menuService.getMenuTree());
    }
    /*@description：废弃
     *@Date：2019/9/12 13:10
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("findNotParent")
    public ResponseBean findNotParent(){
        return ResponseBean.SUCCESS(menuService.findNotParent());
    }
    /*@description：查询所有菜单
     *@Date：2019/9/12 13:12
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("findAll")
    public ResponseBean findAll(){
        return ResponseBean.SUCCESS(menuService.findAll());
    }
}
