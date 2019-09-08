package com.zdsub.service.manager.impl;

import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.increase.MenuInc;
import com.zdsub.service.manager.ManagerService;
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
