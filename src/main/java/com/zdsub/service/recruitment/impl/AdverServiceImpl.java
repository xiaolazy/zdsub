package com.zdsub.service.recruitment.impl;

import com.zdsub.common.constant.Common;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.dao.recruitment.AdverDao;
import com.zdsub.dao.supportTibet.SchoolDao;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.recruitment.increase.AdverInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.recruitment.AdverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.recruitment.impl
 * @Author: ly
 * @CreateTime: 2019-09-06 20:30
 * @Description:
 */
@Service
@Slf4j
public class AdverServiceImpl implements AdverService {
    @Autowired
    private AdverDao adverDao;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public void add(AdverInc adverInc) {
        Adver adver = new Adver();
        BeanUtils.copyProperties(adverInc, adver);
        School school = getSchool(adverInc);
        adver.setSchool(school);
        adverDao.save(adver);
    }

    @Override
    public void edit(AdverInc adverInc) {
        get(adverInc.getId());
        School school = getSchool(adverInc);
        Adver adver = new Adver();
        BeanUtils.copyProperties(adverInc, adver);
        adver.setSchool(school);
        adverDao.update(adver);
    }

    /**
     * 查找关联的学校记录存不存在
     *
     * @param adverInc
     * @return
     */
    private School getSchool(AdverInc adverInc) {
        School school = schoolDao.find(adverInc.getSchId());
        if (school == null) {
            log.error("查找名为" + school.getId() + "的学校记录，已不再数据库中了");
            throw new GlobalException(Common.FAIL, "选择该关联的学校记录已被删除了");
        }
        return school;
    }

    @Override
    public void remove(String id) {
        Adver adver = get(id);
        adverDao.delete(adver);
    }

    @Override
    public Adver get(String id) {
        if (id == null || id.equals("")) {
            log.error("查询人才的id不能为空");
            throw new GlobalException("查询人才的id不能为空");
        }
        Adver adver = adverDao.find(id);
        if (adver == null) {
            log.error("查找名为" + id + "的人才记录，已不再数据库中了");
            throw new GlobalException(Common.FAIL, "该条人才记录已被删除了");
        }
        return adver;
    }

    @Override
    public Page<Adver> page(Page page) {
        return adverDao.findPage(page);
    }

    @Override
    public Adver toAdverShow(String id) {
        Adver adver = get(id);
        adver.setRead_num(adver.getRead_num() + 1);
        adverDao.update(adver);
        return adver;
    }
}
