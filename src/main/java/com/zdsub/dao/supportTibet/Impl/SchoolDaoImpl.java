package com.zdsub.dao.supportTibet.Impl;

import com.zdsub.component.hibernate.BaseDaoImpl;
import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.supportTibet.SchoolDao;
import com.zdsub.entity.university.School;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.dao.university.impl
 * @Author: ly
 * @CreateTime: 2019-09-06 11:01
 * @Description:
 */
@Repository
public class SchoolDaoImpl extends BaseDaoImpl<School,String> implements SchoolDao {
    @Override
    public Page<School> page(Page page) {
        return findPage(page);
    }

}
