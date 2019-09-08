package com.zdsub.service.menu;

import com.zdsub.entity.menu.increase.MenuInc;

import java.util.List;

public interface MenuService {
    List<MenuInc> getMenuTree(String account);
}
