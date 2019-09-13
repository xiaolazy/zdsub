package com.zdsub.service.work.impl;

import com.zdsub.common.constant.Common;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.dao.work.WorkDynamicDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.recruitment.increase.AdverInc;
import com.zdsub.entity.university.School;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.entity.work.increase.WorkDynamicInc;
import com.zdsub.service.work.WorkDynamicService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.zdsub.utils.PageUtil.getRestrictions;

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

    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private ManagerDao managerDao;

    @Override
    public void add(WorkDynamicInc workDynamicInc) {
        WorkDynamic workDynamic = new WorkDynamic();
        BeanUtils.copyProperties(workDynamicInc, workDynamic);
        School school = getSchool(workDynamicInc);
        workDynamic.setSchool(school);
        workDynamic.setCreate_time(DateUtil.getDateTime());
        workDynamic.setCreate_user(managerDao.find(TokenBean.activeUserId.get()));
        workDynamic.setUptate_time(DateUtil.getDateTime());
        workDynamic.setUptate_user(TokenBean.activeUserId.get());
        workDynamicDao.save(workDynamic);
    }

    /**
     * 查找关联的学校记录存不存在
     *
     * @param workDynamicInc
     * @return
     */
    private School getSchool(WorkDynamicInc workDynamicInc) {
        School school = schoolDao.find(workDynamicInc.getSchId());
        if (school == null) {
            log.error("查找名为" + school.getId() + "的学校记录，已不再数据库中了");
            throw new GlobalException(Common.FAIL, "选择该关联的学校记录已被删除了");
        }
        return school;
    }

    @Override
    public void edit(WorkDynamicInc workDynamicInc) {
        WorkDynamic workDynamic = get(workDynamicInc.getId());
        BeanUtils.copyProperties(workDynamicInc, workDynamic);
        workDynamic.setUptate_time(DateUtil.getDateTime());
        workDynamic.setUptate_user(TokenBean.activeUserId.get());
        workDynamicDao.update(workDynamic);
    }

    @Override
    public void remove(String id) {
        WorkDynamic workDynamic = get(id);
        workDynamicDao.delete(workDynamic);

    }

    @Override
    public Page<WorkDynamic> page(Page<WorkDynamic> page) {
        Page<WorkDynamic> workDynamicPage;
        if (page.getCondition() == null)
            workDynamicPage = workDynamicDao.findPage(page);
        else
            workDynamicPage = workDynamicDao.findPage(page, getRestrictions("title", page.getCondition().getTitle()));
        workDynamicPage.getResultList().forEach((workDynamic) -> {
            showWorkDynamic(workDynamic);
        });
        return workDynamicPage;
    }

    private void showWorkDynamic(WorkDynamic workDynamic) {
        Manager create_user = workDynamic.getCreate_user();
        create_user.setId("");
        create_user.setCreate_user("");
        create_user.setSch_id("");
        create_user.setCreate_time("");
        create_user.setPass_word("");
        create_user.setRole_id("");
        create_user.setTelephone("");

        //showManager.setUser_name(Optional.ofNullable(create_user).map(Manager::getUser_name).orElse(","));
        //workDynamic.setCreate_user(Optional.ofNullable(showManager).get());
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
        showWorkDynamic(workDynamic);
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
