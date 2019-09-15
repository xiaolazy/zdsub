package com.zdsub.service.entername.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.entername.EnternameDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.entername.Entername;
import com.zdsub.entity.entername.increase.EnternameInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.entername.EnternameService;
import static org.springframework.beans.BeanUtils.*;

import com.zdsub.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import static com.zdsub.utils.PageUtil.*;
import javax.annotation.Resource;
import java.sql.Date;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 22:43
 **/
@Service
public class EnternameServiceImpl implements EnternameService {
    @Resource//把enterNameDao注入到此处调用Dao层分页查询
    private EnternameDao enternameDao;
    @Resource
    private SchoolDao schoolDao;

    @Override
    public Page<Entername> getPage(Page<Entername> page) {
        //若分页参数为空则调用封装的默认分页查询
        if (page.getCondition() == null)
            return enternameDao.findPage(page);
        //若分页参数不为空则带上参数 调用封装的默认分页查询
        else
            return enternameDao.findPage(page,getRestrictions("user_name",page.getCondition().getUser_name()));
    }

    @Override
    public void save(EnternameInc entername) throws Exception {
        School school = schoolDao.find(entername.getSch_id());
        Entername newEn = new Entername();
        copyProperties(entername,newEn);
        newEn.setSchool(school);
        newEn.setSch_name(school.getSch_name());
        newEn.setCreate_time(DateUtil.getSysTimestamp());
        enternameDao.save(newEn);
    }
}
