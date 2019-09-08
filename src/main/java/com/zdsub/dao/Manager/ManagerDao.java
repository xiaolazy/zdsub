package com.zdsub.dao.Manager;

import com.zdsub.component.Hibernate.BaseDao;
import com.zdsub.entity.manager.Manager;

public interface ManagerDao extends BaseDao<Manager,String> {
    Manager findUserByNameAndPwd(String account,String password);
}
