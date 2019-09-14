package com.zdsub.dao.menu.impl;

import com.zdsub.component.hibernate.BaseDaoImpl;
import com.zdsub.dao.menu.MenuDao;
import com.zdsub.entity.menu.Menu;
import com.zdsub.entity.menu.increase.MenuInc;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 16:34
 **/
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu,String > implements MenuDao {
    @Override
    public List<Menu> getByPid(String pid) {
        return getSession().createQuery("from Menu where pid = ?").
                setParameter(0, pid).list();
    }

    @Override
    public List<Menu> findNotParent() {
        return getSession().createQuery("from Menu where menu_url != ?")
                .setParameter(0,"/").list();
    }

    @Override
    public List<Menu> getParent() {
        return getSession().createQuery("from Menu where pid = 0").list();
    }
}
