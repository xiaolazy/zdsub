package com.zdsub.service.manager.impl;

import com.google.common.collect.Sets;
import com.zdsub.common.constant.Common;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.component.token.TokenPermission;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.role.RoleDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.manager.increase.ManagerSaveInc;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.role.Role;
import com.zdsub.entity.university.School;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.utils.DateUtil;

import static com.zdsub.utils.Md5Util.Md5;
import static java.awt.SystemColor.menu;
import static org.springframework.beans.BeanUtils.*;

import org.springframework.stereotype.Service;

import static com.zdsub.utils.ExceptionUtil.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        return managerDao.findUserByNameAndPwd(m.getUser_name(), m.getPass_word());
    }

    @Override
    public ManagerInc findById(String id) {
        isBlank(id, "查询用户Id不能为空！");
        ManagerInc res = new ManagerInc();
        Manager m = managerDao.find(id);
        res.setCreateName(getManagerName(m.getCreate_user()));
        res.setRoleName(getRoleName(m.getRole_id()));
        res.setSchName(getSchName(m.getSch_id()));
        copyProperties(m, res);
        return res;
    }

    @Override
    public Page<ManagerInc> getPage(Page<Manager> page) {
        Page<ManagerInc> managerIncPage = new Page<>();
        List<ManagerInc> res = new ArrayList<>();
        Page<Manager> rPage = null;
        if (page.getCondition() == null)
            rPage = managerDao.findPage(page);
        else
            rPage = managerDao.findPage(page, getRestrictions("user_name", page.getCondition().getUser_name()));
        List<Manager> resultList = rPage.getResultList();
        resultList.forEach(e -> {
            ManagerInc m = new ManagerInc();
            m.setCreateName(getManagerName(e.getCreate_user()));
            m.setRoleName(getRoleName(e.getRole_id()));
            m.setSchName(getSchName(e.getSch_id()));
            copyProperties(e, m);
            res.add(m);
        });
        managerIncPage.setPageNo(rPage.getPageNo());
        managerIncPage.setPageSize(rPage.getPageSize());
        managerIncPage.setTotalCount(rPage.getTotalCount());
        managerIncPage.setResultList(res);
        return managerIncPage;
    }

    @Override
    public Manager getById(String id) {
        isBlank(id, "用户查询ID不能为空");
        return managerDao.find(id);
    }

    @Override
    public void delById(String id) throws Exception {
        isBlank(id, "用户Id不能为空！");
        Manager manager = managerDao.find(id);
        isNull(manager, "用户不存在！");
        managerDao.delete(manager);
    }

    @Override
    public void add(ManagerSaveInc m) throws Exception {
        String schId = m.getSchId();
        isNull(m, "所添加用户信息丢失，请稍候重试或联系管理员！");
        Role role = roleDao.find(m.getRoleId());
        School school = schoolDao.find(schId);
        isNull("新增用户时，所查询学校/权限不存在！", role, school);
        Manager manager = new Manager();
        setManager(m, manager, school, role);
        manager.setPass_word(Md5(m.getPass_word()));
        managerDao.save(manager);
    }

    @Override
        public void update(ManagerSaveInc m) throws Exception {
        String schId = m.getSchId();
        isNull(m, "所修改用户信息丢失，请稍候重试或联系管理员！");
        Role role = roleDao.find(m.getRoleId());
        HashSet<String> urls = Sets.newHashSet();
        List<Menu> menus = role.getMenus();
        if (!isNull(role))
            menus.forEach(e -> {
                if (!e.getMenu_url().equals(Common.ROOT))
                    urls.add(e.getMenu_url());
            });
        urls.add(Common.MENU);
        urls.add(Common.LOGOUT);
        TokenPermission.getInstance().put(TokenBean.activeUserId.get(), urls);
        School school = schoolDao.find(schId);
        Manager manager = managerDao.find(m.getId());
        String pass_word = manager.getPass_word();
        setManager(m, manager, school, role);
        manager.setPass_word(pass_word);
        isNull("修改时用户时，所查询学校/权限不存在！", role, school, manager);
        managerDao.save(manager);
    }

    @Override
    public boolean findByName(String name) {
        return (managerDao.findUserByName(name) == null) ? false : true;
    }

    public static void setManager(ManagerSaveInc source, Manager target, School school, Role role) {
        copyProperties(source, target);
        target.setSch_id(school.getId());
        target.setRole_id(role.getId());
        target.setCreate_time(DateUtil.getDate());
        target.setCreate_user(TokenBean.activeUserId.get());
    }

    private String getRoleName(String id) {
        Role role = roleDao.find(id);
        return role == null ? "无" : role.getRole_name();
    }

    private String getSchName(String id) {
        School school = schoolDao.find(id);
        return school == null ? "无" : school.getSch_name();
    }

    private String getManagerName(String id) {
        Manager manager = managerDao.find(id);
        return manager == null ? "无" : manager.getUser_name();
    }
}
