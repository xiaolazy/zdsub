package com.zdsub.dao.manager;

import com.zdsub.component.hibernate.BaseDao;
import com.zdsub.entity.manager.Manager;

public interface ManagerDao extends BaseDao<Manager,String> {
    Manager findUserByNameAndPwd(String account,String password);
}
