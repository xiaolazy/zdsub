package com.zdsub.service.university.impl;

import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.university.School;
import com.zdsub.entity.university.increase.SchoolInc;
import com.zdsub.service.university.SchollService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zdsub.utils.PageUtil.getRestrictions;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.university.impl
 * @Author: ly
 * @CreateTime: 2019-09-06 11:05
 * @Description:
 */
@Service
@Slf4j
public class SchollServiceImpl implements SchollService {

    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private ManagerDao managerDao;

    @Override
    public void add(SchoolInc schoolInc) {
        School school = new School();
        BeanUtils.copyProperties(schoolInc, school);
        school.setCreate_time(DateUtil.getDateTime());
        school.setUpdate_time(DateUtil.getDateTime());
        school.setCreate_user(findManager().getUser_name());
        school.setUpdate_user(TokenBean.activeUserId.get());
        schoolDao.save(school);
    }

    private Manager findManager() {
        Manager manager = managerDao.find(TokenBean.activeUserId.get());
        if (manager == null) {
            manager=new Manager();
            manager.setUser_name("无");
        }
        return manager;
    }

    @Override
    public void edit(SchoolInc schoolInc) {
        School school = get(schoolInc.getId());
        BeanUtils.copyProperties(schoolInc, school);
        school.setUpdate_time(DateUtil.getDateTime());
        school.setUpdate_user(TokenBean.activeUserId.get());
        schoolDao.update(school);
    }

    @Override
    public void remove(String id) {
        get(id);
        schoolDao.delete(id);
    }

    @Override
    public Page<School> page(Page<School> schoolPage) {
        Page<School> rPage;
        if (schoolPage.getCondition() == null)
            rPage = schoolDao.findPage(schoolPage);
        else
            rPage = schoolDao.findPage(schoolPage, getRestrictions("sch_name", schoolPage.getCondition().getSch_name()));
        return rPage;
    }

    @Override
    public School get(String id) {
        if (id == null || id.equals("")) {
            log.error("查询学校的id不能为空");
            throw new GlobalException("查询学校的id不能为空");
        }
        School School = schoolDao.find(id);
        if (School == null) {
            log.error("查询名为" + id + "的学校记录，已不再数据库中了");
            throw new GlobalException("该条学校记录已不再数据库中了");
        }
        return School;
    }

    @Override
    public List<School> listAll() {
        return schoolDao.findAll();
    }
}
