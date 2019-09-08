package com.zdsub.dao.manager.impl;

import com.zdsub.component.hibernate.BaseDaoImpl;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.entity.manager.Manager;
import org.springframework.stereotype.Repository;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 15:16
 **/
@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager,String> implements ManagerDao{

    @Override
    public Manager findUserByNameAndPwd(String account,String password) {
        String hql = "from Manager where user_name = ? and pass_word = ?";
        return (Manager) getSession().createQuery(hql)
                .setParameter(0, account).setParameter(1, password).uniqueResult();
    }

    @Override
    public Manager findUserByName(String account) {
        return (Manager) getSession().createQuery("from Manager where user_name = ?")
                .setParameter(0, account).uniqueResult();
    }

}
