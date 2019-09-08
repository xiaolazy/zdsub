package com.zdsub.service.work.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.dao.work.WorkDynamicDao;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.service.work.WorkDynamicService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.work.impl
 * @Author: ly
 * @CreateTime: 2019-09-07 21:56
 * @Description:
 */
@Service
@Slf4j
public class WorkDynamicServiceImpl implements WorkDynamicService {

    @Autowired
    private WorkDynamicDao workDynamicDao;

    @Override
    public void add(WorkDynamic workDynamic) {
        workDynamic.setCreate_time(DateUtil.getDateTime());
        workDynamic.setCreate_user("===========");
        workDynamic.setUpdate_time(DateUtil.getDateTime());
        workDynamic.setUpdate_user("===========");
        workDynamicDao.save(workDynamic);
    }

    @Override
    public void edit(WorkDynamic workDynamic) {
        get(workDynamic.getId());
        workDynamic.setUpdate_user("=========");
        workDynamic.setUpdate_time(DateUtil.getDateTime());
        workDynamicDao.update(workDynamic);
    }

    @Override
    public void remove(String id) {
        WorkDynamic workDynamic = get(id);
        workDynamicDao.delete(workDynamic);

    }

    @Override
    public Page<WorkDynamic> page(Page<WorkDynamic> page) {
        WorkDynamic workDynamic = (WorkDynamic) page.getCondition();
        workDynamic.setTitle("%" + workDynamic.getTitle() + "%");
        return workDynamicDao.findPage(page);
    }

    @Override
    public WorkDynamic get(String id) {
        if (id == null || id.equals("")) {
            log.error("查询工作状态的id不能为空");
            throw new GlobalException("查询工作状态的id不能为空");
        }
        WorkDynamic workDynamic = workDynamicDao.find(id);
        if (workDynamic == null) {
            log.error("查询id为" + id + "的工作状态，已不再数据库中了");
            throw new GlobalException("该条工作状态已被删除了");
        }
        return workDynamic;
    }

    @Override
    public WorkDynamic toAdverShow(String id) {
        WorkDynamic workDynamic = get(id);
        workDynamic.setRead_num(workDynamic.getRead_num() + 1);
        workDynamicDao.update(workDynamic);
        return workDynamic;
    }
}
