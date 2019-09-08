package com.zdsub.dao.menu.impl;

import com.zdsub.component.hibernate.BaseDaoImpl;
import com.zdsub.dao.menu.MenuIncDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 18:34
 **/
@Repository
public class MenuIncDaoImpl extends BaseDaoImpl<MenuInc, String> implements MenuIncDao {
    @Override
    public List<MenuInc> getByPid(String pid) {
        return getSession().createSQLQuery("select * from menu where PID = ?").
                setResultTransformer(Transformers.aliasToBean(MenuInc.class)).setParameter(0, pid).list();
    }
}
