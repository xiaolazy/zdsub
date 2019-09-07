package com.zdsub.service.university.Impl;

import com.zdsub.common.constant.Common;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.dao.supportTibet.SchoolDao;
import com.zdsub.entity.university.Increase.SchoolInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.university.SchollService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.university.Impl
 * @Author: ly
 * @CreateTime: 2019-09-06 11:05
 * @Description:
 */
@Service
@Slf4j
public class SchollServiceImpl implements SchollService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void add(SchoolInc schoolInc) {
        School school = new School();
        BeanUtils.copyProperties(schoolInc, school);
        school.setCreate_time(DateUtil.getDate());
        school.setCreate_user("===========");
        school.setUpdate_time(DateUtil.getDate());
        school.setUpdate_user("===========");
        schoolDao.save(school);
    }

    @Override
    public void edit(SchoolInc schoolInc) {
        School school = get(schoolInc.getId());
        BeanUtils.copyProperties(schoolInc, school);
        school.setUpdate_time(DateUtil.getDate());
        school.setUpdate_user("----------");
        schoolDao.update(school);
    }

    @Override
    public void remove(String id) {
        get(id);
        schoolDao.delete(id);
    }

    @Override
    public Page<School> page(Page page) {
        return schoolDao.page(page);
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
            throw new GlobalException("该条学校记录已被删除了");
        }
        return School;
    }

    @Override
    public List<School> listAll() {
        return schoolDao.findAll();
    }
}
