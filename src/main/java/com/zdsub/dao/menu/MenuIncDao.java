package com.zdsub.dao.menu;

import com.zdsub.component.hibernate.BaseDao;
import com.zdsub.entity.menu.increase.MenuInc;

import java.util.List;

public interface MenuIncDao extends BaseDao<MenuInc,String> {
    List<MenuInc> getByPid(String pid);
}
