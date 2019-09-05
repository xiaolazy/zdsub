package com.zdsub.dao.Manager;

import com.zdsub.component.Hibernate.BaseDao;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.Manager.Manager;

import java.util.List;

public interface ManagerDao extends BaseDao<Manager,String> {
    List<?> listAll();
    Object findManager(String id);
    Page<Manager> page(Page page);
}
