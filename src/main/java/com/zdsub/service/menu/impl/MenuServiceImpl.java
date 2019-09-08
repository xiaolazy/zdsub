package com.zdsub.service.menu.impl;

import com.zdsub.dao.menu.MenuDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.service.menu.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Override
    public List<MenuInc> getMenuTree() {
        List<Menu> parent = menuDao.getByPid("0");
        parent.forEach(System.out::println);
        return null;
    }
}
