package com.zdsub.dao.Manager.Impl;

import com.zdsub.component.Hibernate.BaseDaoImpl;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.dao.Manager.ManagerDao;
import com.zdsub.entity.Manager.Manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 15:16
 **/
@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager,String> implements ManagerDao{
    @Resource
    HibernateTemplate hibernateTemplate;
    @Autowired
    protected SessionFactory sessionFactory;
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<?> listAll() {
        String se = "from Manager";
        return getSession().createQuery(se).list();
    }

    @Override
    public Object findManager(String id) {
        return find("1001");
    }

    @Override
    public Page<Manager> page(Page page) {
        page.setPageNo(0);
        page.setPageSize(8);
        System.out.println("===========================");
        return findPage(page);
    }

}
