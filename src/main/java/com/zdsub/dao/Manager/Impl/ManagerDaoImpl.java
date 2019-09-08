package com.zdsub.dao.Manager.Impl;

import com.zdsub.component.Hibernate.BaseDaoImpl;
import com.zdsub.dao.Manager.ManagerDao;
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
        return (Manager) getSession().createQuery("from Manager where user_name = ? and pass_word = ?")
                .setParameter(0, account).setParameter(1, password).uniqueResult();
    }

}
