package com.zdsub.service.Manager.Impl;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.dao.Manager.ManagerDao;
import com.zdsub.entity.Manager.Manager;
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
    public List<?> listAll() {
        List<?> objects = managerDao.listAll();
        System.out.println("size:-----------------------"+objects.size());
        return objects;
    }

    @Override
    public Object findManager(String id) {
        return managerDao.findManager(id);
    }

    @Override
    public Page<Manager> page(Page page) {
        return managerDao.page(page);
    }
}
