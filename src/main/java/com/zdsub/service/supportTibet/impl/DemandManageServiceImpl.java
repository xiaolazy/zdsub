package com.zdsub.service.supportTibet.impl;

import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.supportTibet.DemandManageDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.supportTibet.DemandManageService;
import com.zdsub.utils.DateUtil;
import com.zdsub.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static com.zdsub.utils.PageUtil.getRestrictions;

@Service
@Slf4j
public class DemandManageServiceImpl implements DemandManageService {

    @Autowired
    private DemandManageDao demandManageDao;
    @Resource
    private SchoolDao schoolDao;

    @Autowired
    private ManagerDao managerDao;

    @Override
    public void add(DemandManageInc demandManageInc) {
        DemandManage demandManage = new DemandManage();
        BeanUtils.copyProperties(demandManageInc, demandManage);
        School school = schoolDao.find(demandManageInc.getSchId());
        demandManage.setSchool(school);
        demandManage.setCreate_user(findManager().getUser_name());
        demandManage.setSend_time(DateUtil.getDateTime());
        demandManage.setUpdate_time(DateUtil.getDateTime());
        demandManage.setRead_num(0);
        demandManage.setUpdate_user(findManager().getUser_name());
        demandManageDao.save(demandManage);
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
    public void edit(DemandManageInc demandManageInc) {
        DemandManage demandManage = get(demandManageInc.getId());
        IncToPo(demandManageInc, demandManage);
        School school = schoolDao.find(demandManageInc.getSchId());
        demandManage.setSchool(school);
        demandManage.setUpdate_time(DateUtil.getDateTime());
        demandManage.setUpdate_user(findManager().getId());
        demandManageDao.update(demandManage);
    }

    private void IncToPo(DemandManageInc demandManageInc, DemandManage demandManage) {
        demandManage.setTitle(Optional.of(demandManageInc).map(DemandManageInc::getTitle).orElse("无"));
        demandManage.setLevel(Optional.of(demandManageInc).map(DemandManageInc::getLevel).orElse(1));
        demandManage.setContext(Optional.of(demandManageInc).map(DemandManageInc::getContext).orElse(""));
    }

    @Override
    public void remove(String id) {
        get(id);
        demandManageDao.delete(id);
    }


    @Override
    public Page<DemandManage> page(Page<DemandManage> page) {
        if (page.getCondition() == null)
            page = demandManageDao.findPage(page);
        else
            page = demandManageDao.findPage(page, getRestrictions("title", page.getCondition().getTitle()));
        return page;
    }

    @Override
    public DemandManage get(String id) {
        ExceptionUtil.isBlank(id, "查询需求的id不能为空");
        DemandManage demandManage = demandManageDao.find(id);
        if (demandManage == null) {
            log.error("查询名为" + id + "的需求，已不再数据库中了");
            throw new GlobalException("该条需求记录已不再数据库中了");
        }
        return demandManage;
    }

    @Override
    public DemandManage toAdverShow(String id) {
        DemandManage demandManage = get(id);
        demandManage.setRead_num(demandManage.getRead_num() + 1);
        demandManageDao.update(demandManage);
        return demandManage;
    }


}
