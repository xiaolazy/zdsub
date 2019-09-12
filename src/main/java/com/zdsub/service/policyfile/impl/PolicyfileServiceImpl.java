package com.zdsub.service.policyfile.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.dao.policyfile.PolicyfileDao;
import com.zdsub.entity.policyfile.Policyfile;
import com.zdsub.service.policyfile.PolicyfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import static com.zdsub.utils.PageUtil.*;
import static com.zdsub.utils.ExceptionUtil.*;
import javax.annotation.Resource;
import java.util.List;

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
}
