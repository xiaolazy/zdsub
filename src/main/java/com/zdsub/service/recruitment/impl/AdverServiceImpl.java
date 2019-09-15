package com.zdsub.service.recruitment.impl;

import com.zdsub.common.constant.Common;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.recruitment.AdverDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.recruitment.increase.AdverInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.recruitment.AdverService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zdsub.utils.PageUtil.getRestrictions;


/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.recruitment.impl
 * @Author: ly
 * @CreateTime: 2019-09-06 20:30
 * @Description:
 */
@Slf4j
@Service
public class AdverServiceImpl implements AdverService {
    @Autowired
    private AdverDao adverDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private ManagerDao managerDao;

    @Override
    public void add(AdverInc adverInc) {
        Adver adver = new Adver();
        BeanUtils.copyProperties(adverInc, adver);
        School school = getSchool(adverInc);
        adver.setSchool(school);
        adver.setCreate_time(DateUtil.getDateTime());
        adver.setCreate_user(findManager().getUser_name());
        adver.setUpdate_time(DateUtil.getDateTime());
        adver.setUpdate_user(TokenBean.activeUserId.get());
        adverDao.save(adver);
    }

    @Override
    public void edit(AdverInc adverInc) {
        Adver adver = get(adverInc.getId());
        School school = getSchool(adverInc);
        BeanUtils.copyProperties(adverInc, adver);
        adver.setSchool(school);
        adver.setUpdate_time(DateUtil.getDateTime());
        adver.setUpdate_user(TokenBean.activeUserId.get());
        adverDao.update(adver);
    }

    private Manager findManager() {
        Manager manager = managerDao.find(TokenBean.activeUserId.get());
        if (manager == null) {
            manager=new Manager();
            manager.setUser_name("无");
        }
        return manager;
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
            log.error("查找名为" + adverInc.getSchId() + "的学校记录，已不再数据库中了");
            throw new GlobalException(Common.FAIL, "选择该关联的学校记录已不再数据库中了");
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
    public Page<Adver> page(Page<Adver> page) {
        Page<Adver> adverPage;
        if (page.getCondition() == null)
            adverPage = adverDao.findPage(page);
        else
            adverPage = adverDao.findPage(page, getRestrictions("title", page.getCondition().getTitle()));
        return adverPage;
    }

    @Override
    public Adver updAdverShow(String id) {
        Adver adver = get(id);
        adver.setRead_num(adver.getRead_num() + 1);
        adverDao.update(adver);
        return adver;
    }
}
