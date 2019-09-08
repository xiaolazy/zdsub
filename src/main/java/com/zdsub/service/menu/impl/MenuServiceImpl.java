package com.zdsub.service.menu.impl;

import com.google.common.collect.Lists;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.menu.MenuDao;
import com.zdsub.dao.menu.MenuIncDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.service.menu.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private MenuIncDao menuIncDao;
    @Override
    public List<MenuInc> getMenuTree(String account) {
        managerDao.findUserByName(account);
        List<Menu> parent = menuDao.getByPid("0");
        List<MenuInc> trees = Lists.newArrayList();
        parent.forEach(s-> {
            trees.add(getTree(s));
        });
        return trees;
    }
    public MenuInc getTree(Menu s){
        MenuInc t = new MenuInc();
        copyProperties(s,t);
        t.setChildrens(getChild(t.getId()));
        return t;
    }
    public List<MenuInc> getChild(String pid){
        List<MenuInc> t = Lists.newArrayList();
        menuDao.getByPid(pid).forEach(s->{
            t.add(getTree(s));
        });
        return t;
    }

}
