package com.zdsub.dao.work.Impl;

import com.zdsub.component.Hibernate.BaseDaoImpl;
import com.zdsub.dao.work.ProcessDao;
import com.zdsub.entity.work.Process;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.dao.work.impl
 * @Author: ly
 * @CreateTime: 2019-09-07 09:17
 * @Description:
 */
@Repository
public class ProcessDaoImpl extends BaseDaoImpl<Process, String> implements ProcessDao {
}
