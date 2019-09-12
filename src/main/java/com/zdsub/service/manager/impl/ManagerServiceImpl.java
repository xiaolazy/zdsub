package com.zdsub.service.manager.impl;

import com.google.common.base.Supplier;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.role.RoleDao;
import com.zdsub.dao.supportTibet.SchoolDao;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.manager.increase.ManagerSaveInc;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.entity.role.Role;
import com.zdsub.entity.university.School;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.utils.DateUtil;
import com.zdsub.utils.PageUtil;
import static org.springframework.beans.BeanUtils.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import static com.zdsub.utils.ExceptionUtil.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.zdsub.utils.PageUtil.*;
/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 15:11
 **/
@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerDao managerDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private SchoolDao schoolDao;

    @Override
    public Manager findUseLogin(ManagerInc m) {
        return managerDao.findUserByNameAndPwd(m.getUser_name(),m.getPass_word());
    }

    @Override
    public Manager findById(String id) {
        isBlank(id,"查询用户Id不能为空！");
        return managerDao.find(id);
    }

    @Override
    public Page<ManagerInc> getPage(Page<Manager> page) {
        Page<ManagerInc> managerIncPage = new Page<>();
        List<ManagerInc> res = new ArrayList<>();
        Page<Manager> rPage = null;
        if(page.getCondition() == null)
            rPage = managerDao.findPage(page);
        else
            rPage = managerDao.findPage(page, getRestrictions("user_name",page.getCondition().getUser_name()));
        List<Manager> resultList = rPage.getResultList();
        resultList.forEach(e->{
            ManagerInc m = new ManagerInc();
            Role role = roleDao.find(e.getRole_id());
            School school = schoolDao.find(e.getSch_id());
            Manager manager = managerDao.find(e.getCreate_user());
            m.setCreateName(manager.getUser_name());
            m.setRoleName(role.getRole_name());
            m.setSchName(school.getSch_name());
            copyProperties(e,m);
            res.add(m);
        });
        managerIncPage.setPageNo(rPage.getPageNo());
        managerIncPage.setPageSize(rPage.getPageSize());
        managerIncPage.setResultList(res);
        return managerIncPage;
    }
    @Override
    public Manager getById(String id) {
        isBlank(id,"用户查询ID不能为空");
        return managerDao.find(id);
    }

    @Override
    public void delById(String id) throws Exception {
        isBlank(id,"用户Id不能为空！");
        Manager manager = managerDao.find(id);
        isNull(manager,"用户不存在！");
        managerDao.delete(manager);
    }

    @Override
    public void add(ManagerSaveInc m) throws Exception {
        String schId = m.getSchId();
        isNull(m,"所添加用户信息丢失，请稍候重试或联系管理员！");
        Role role = roleDao.find(m.getRoleId());
        School school = schoolDao.find(schId);
        isNull("新增用户时，所查询学校/权限不存在！",role,school);
        Manager manager = new Manager();
        setManager(m,manager,school,role);
        managerDao.save(manager);
    }

    @Override
    public void update(ManagerSaveInc m) throws Exception {
        String schId = m.getSchId();
        isNull(m,"所修改用户信息丢失，请稍候重试或联系管理员！");
        Role role = roleDao.find(m.getRoleId());
        School school = schoolDao.find(schId);
        Manager manager = managerDao.find(m.getId());
        setManager(m,manager,school,role);
        isNull("修改时用户时，所查询学校/权限不存在！",role,school,manager);
        managerDao.save(manager);
    }
    public static void setManager(ManagerSaveInc source,Manager target,School school,Role role){
        copyProperties(source,target);
        target.setSch_id(school.getId());
        target.setRole_id(role.getId());
        target.setCreate_time(DateUtil.getDate());
        target.setCreate_user(TokenBean.activeUser.get());
    }
}
