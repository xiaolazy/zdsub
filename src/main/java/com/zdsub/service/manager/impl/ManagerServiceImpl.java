package com.zdsub.service.manager.impl;

import com.google.common.base.Supplier;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.hibernate.PageCondition;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.utils.PageUtil;
import lombok.val;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import static com.zdsub.utils.ExceptionUtil.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 15:11
 **/
@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    ManagerDao managerDao;

    @Override
    public Manager findUseLogin(ManagerInc m) {
        return managerDao.findUserByNameAndPwd(m.getUser_name(),m.getPass_word());
    }

    @Override
    public Manager findById(String id) {
        return managerDao.find(id);
    }

    @Override
    public Page<Manager> getPage(Page<Manager> page) {
        return managerDao.findPage(page, PageUtil.getRestri("user_name",page.getCondition().getUser_name()));
    }
    @Override
    public Manager getById(String id) {
        isBlank(id,"用户查询ID不能为空");
        return managerDao.find(id);
    }
}
