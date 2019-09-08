package com.zdsub.dao.menu;

import com.zdsub.component.hibernate.BaseDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;

import java.util.List;
import java.util.Set;

public interface MenuDao extends BaseDao<Menu,String> {
    List<Menu> getByPid(String pid);
}
