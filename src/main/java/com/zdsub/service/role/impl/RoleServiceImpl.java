package com.zdsub.service.role.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.role.RoleDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.role.Role;
import com.zdsub.service.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        roleDao.save(role);
    }

    @Override
    public void update(Role role)throws Exception {
        roleDao.update(role);
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
