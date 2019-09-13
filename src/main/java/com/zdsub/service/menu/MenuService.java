package com.zdsub.service.menu;

import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.entity.menu.increase.MenuRole;

import java.util.List;

public interface MenuService {
    /*@description：菜单树
     *@Date：2019/9/11 10:33
     *@Param
     *@Return
     *@Author lyy
     */
    List<MenuInc> getMenuTree();
    MenuRole findNotParent();
    List<Menu> findAll();
}
