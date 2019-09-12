package com.zdsub.service.supportTibet.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.dao.supportTibet.DemandManageDao;
import com.zdsub.dao.university.SchoolDao;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;
import com.zdsub.entity.university.School;
import com.zdsub.service.supportTibet.DemandManageService;
import com.zdsub.utils.DateUtil;
import com.zdsub.utils.ExceptionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsub.entity.PageCondition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.zdsub.utils.PageUtil.getRestrictions;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class DemandManageServiceImpl implements DemandManageService {

    @Autowired
    private DemandManageDao demandManageDao;
    @Resource
    private SchoolDao schoolDao;

    @Override
    public void add(DemandManageInc demandManageInc) {
        System.out.println("----------------------------");
        DemandManage demandManage = new DemandManage();
        BeanUtils.copyProperties(demandManageInc, demandManage);
        demandManage.setUpdate_time(DateUtil.getDateTime());
        demandManage.setCreate_user("===========");
        demandManage.setUpdate_time(DateUtil.getDateTime());
        demandManage.setUpdate_user("===========");
        System.out.println("----------------------------" + demandManage.getSch_id());
        demandManageDao.save(demandManage);
    }

    @Override
    public void edit(DemandManageInc demandManageInc) {
        DemandManage demandManage = get(demandManageInc.getId());
        BeanUtils.copyProperties(demandManageInc, demandManage);
        demandManage.setUpdate_time(DateUtil.getDateTime());

        demandManage.setUpdate_user("----------");
        demandManageDao.update(demandManage);
    }

    @Override
    public void remove(String id) {
        get(id);
        demandManageDao.delete(id);
    }

    @Override
    public Page<DemandManage> page(Page page) {
        return null;
    }

    @Override
    public Page<DemandManage> page(PageCondition<DemandManage> demandManagePageCondition) {
        return null;
    }


    public Page<DemandManageInc> getPage(Page<DemandManage> page) {
        Page<DemandManageInc> demandManageIncPage = new Page<>();
        List<DemandManageInc> res = new ArrayList<>();
        Page<DemandManage> rPage = null;
        if(page.getCondition() == null)
            rPage = demandManageDao.findPage(page);
        else
            rPage = demandManageDao.findPage(page, getRestrictions("user_name",page.getCondition().getSch_id()));
        List<DemandManage> resultList = rPage.getResultList();
        resultList.forEach(e->{
            DemandManageInc m = new DemandManageInc();
            School school = schoolDao.find(e.getSch_id());
            DemandManage demandManage = demandManageDao.find(e.getCreate_user());
            m.setSch_id(demandManage.getSch_id());
            m.setSch_name(school.getSch_name());
            copyProperties(e,m);
            res.add(m);
        });
        demandManageIncPage.setPageNo(rPage.getPageNo());
        demandManageIncPage.setPageSize(rPage.getPageSize());
        demandManageIncPage.setResultList(res);
        return demandManageIncPage;
    }

    @Override
    public DemandManage get(String id) {
        ExceptionUtil.isBlank(id,"查询学校的id不能为空");
        DemandManage demandManage = demandManageDao.find(id);
        if (demandManage == null) {
//            log.error("查询名为" + id + "的学校记录，已不再数据库中了");
            throw new GlobalException("该条学校记录已被删除了");
        }
        return demandManage;
    }

    @Override
    public List<DemandManage> listAll() {
        return demandManageDao.findAll();
    }
}
