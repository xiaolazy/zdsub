package com.zdsub.dao.supportTibet;

import com.zdsub.component.Hibernate.BaseDao;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.university.School;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.dao.university
 * @Author: ly
 * @CreateTime: 2019-09-06 10:59
 * @Description:
 */
public interface SchoolDao extends BaseDao<School,String> {

    Page<School> page(Page page);

}
