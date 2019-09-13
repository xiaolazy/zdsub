package com.zdsub.service.menu.impl;

import com.google.common.collect.Lists;
import com.zdsub.common.constant.Common;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.menu.MenuDao;
import com.zdsub.dao.menu.MenuIncDao;
import com.zdsub.dao.role.RoleDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.entity.menu.increase.MenuRole;
import com.zdsub.entity.role.Role;
import com.zdsub.service.menu.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 15:23
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;
    @Resource
    private ManagerDao managerDao;
    @Resource
    private RoleDao roleDao;

    @Override
    public MenuRole findNotParent() {
        List<Menu> menus = roleDao.find(managerDao.find(TokenBean.activeUserId.get()).getRole_id()).getMenus();
        List<String> ids = Lists.newArrayList();
        menus.forEach(e -> {
            if (!e.getMenu_url().equals(Common.ROOT))
                ids.add(e.getId());
        });
        MenuRole m = new MenuRole();
        m.setIds(ids);
        m.setMenus(menuDao.findNotParent());
        return m;
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<MenuInc> getMenuTree() {
        Role role = roleDao.find(managerDao.find(TokenBean.activeUserId.get()).getRole_id());
        List<Menu> menus = role.getMenus();
        List<MenuInc> trees = Lists.newArrayList();
        menus.forEach(e -> {
            if (e.getPid().equals("0"))
                trees.add(getTree(e, menus));
        });
        return trees;
    }

    public MenuInc getTree(Menu s, List<Menu> menus) {
        MenuInc t = new MenuInc();
        copyProperties(s, t);
        t.setChildrens(getChild(t.getId(), menus));
        return t;
    }

    public List<MenuInc> getChild(String pid, List<Menu> menus) {
        List<MenuInc> t = Lists.newArrayList();
        menus.forEach(s -> {
            if (s.getPid().equals(pid))
                t.add(getTree(s, menus));
        });
        return t;
    }

}
