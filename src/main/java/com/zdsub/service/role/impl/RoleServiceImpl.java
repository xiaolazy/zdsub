package com.zdsub.service.role.impl;

import com.google.common.collect.Sets;
import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.menu.MenuDao;
import com.zdsub.dao.role.RoleDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.role.Role;
import com.zdsub.entity.role.increase.RoleInc;
import com.zdsub.service.role.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.zdsub.utils.ExceptionUtil.isNull;
import static com.zdsub.utils.PageUtil.*;
import static com.zdsub.utils.ExceptionUtil.isBlank;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-11 21:42
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private MenuDao menuDao;
    @Override
    public Page<Role> getPage(Page<Role> page) {
        if(page.getCondition() == null)
            return roleDao.findPage(page);
        return roleDao.findPage(page,getRestrictions("role_name",page.getCondition().getRole_name()));
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        isBlank(id,"查询权限Id不能为空！");
        return roleDao.find(id);
    }

    @Override
    public void add(Role role)throws Exception {
        List<Menu> all = menuDao.findAll();
        role.setMenus(Sets.newHashSet(all));
        roleDao.save(role);
    }

    @Override
    public void update(RoleInc role)throws Exception {
        Role newRole = roleDao.find(role.getId());
        HashSet<Menu> menus = Sets.newHashSet();
        role.getIds().forEach(e->{
            Menu menu = menuDao.find(e);
            isNull(menu,"权限所选菜单不存在！");
            menus.add(menu);
        });
        BeanUtils.copyProperties(role,newRole);
        newRole.setMenus(menus);
        roleDao.update(newRole);
    }
    @Override
    public void delById(String id) throws Exception {
        isBlank(id,"用户Id不能为空！");
        Role role = roleDao.find(id);
        isNull(role,"用户不存在！");
        role.setMenus(null);
        roleDao.delete(role);
    }
}
