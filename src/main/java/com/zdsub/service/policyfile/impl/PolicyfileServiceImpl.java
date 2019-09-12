package com.zdsub.service.policyfile.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.policyfile.PolicyfileDao;
import com.zdsub.entity.policyfile.Policyfile;
import com.zdsub.service.policyfile.PolicyfileService;
import com.zdsub.utils.DateUtil;
import static org.springframework.beans.BeanUtils.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import static com.zdsub.utils.PageUtil.*;
import static com.zdsub.utils.ExceptionUtil.*;
import javax.annotation.Resource;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 13:22
 **/
@Service
public class PolicyfileServiceImpl implements PolicyfileService {
    @Resource
    private PolicyfileDao policyfileDao;
    @Resource
    private ManagerDao managerDao;
    @Override
    public Page<Policyfile> getPage(Page<Policyfile> page) {
        if(page.getCondition() == null)
            return policyfileDao.findPage(page);
        else
            return policyfileDao.findPage(page,getRestrictions("title",page.getCondition().getTitle()));
    }
    @Override
    public Policyfile findById(String id) {
        isBlank(id,"查询ID不能为空！");
        return policyfileDao.find(id);
    }

    @Override
    public void add(Policyfile policyfile) throws Exception {
        policyfile.setCreate_user(managerDao.find(TokenBean.activeUserId.get()));
        setPolicyfile(policyfile);
        policyfileDao.save(policyfile);
    }

    @Override
    public void update(Policyfile policyfile) throws Exception {
        isBlank(policyfile.getId(),"修改失败，ID不能为空!");
        Policyfile newPo = policyfileDao.find(policyfile.getId());
        copyProperties(policyfile,newPo);
        newPo.setUpdate_user(TokenBean.activeUserId.get());
        newPo.setUpdate_time(DateUtil.getDate());
        policyfileDao.update(newPo);
    }

    private static void setPolicyfile(Policyfile policyfile){
        policyfile.setCreate_time(DateUtil.getDate());
        policyfile.setUpdate_time(DateUtil.getDate());
        policyfile.setUpdate_user(TokenBean.activeUserId.get());
    }
}
