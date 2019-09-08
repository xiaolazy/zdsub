package com.zdsub.service.Manager.Impl;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.dao.Manager.ManagerDao;
import com.zdsub.entity.manager.Increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.service.Manager.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
