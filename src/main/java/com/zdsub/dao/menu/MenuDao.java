package com.zdsub.dao.menu;

import com.zdsub.component.hibernate.BaseDao;
import com.zdsub.entity.menu.Menu;

import java.util.List;

public interface MenuDao extends BaseDao<Menu,String> {
    List<Menu> getByPid(String pid);
    List<Menu> findNotParent();
}

