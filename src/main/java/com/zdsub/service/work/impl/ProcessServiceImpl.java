package com.zdsub.service.work.impl;

import com.zdsub.component.hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.token.TokenBean;
import com.zdsub.dao.manager.ManagerDao;
import com.zdsub.dao.work.ProcessDao;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.service.work.ProcessService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.zdsub.utils.PageUtil.getRestrictions;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.work.impl
 * @Author: ly
 * @CreateTime: 2019-09-07 09:22
 * @Description:
 */
@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessDao processDao;

    @Autowired
    private ManagerDao managerDao;

    @Override
    public void add(ProcessInc processInc) {
        Process process = new Process();
        BeanUtils.copyProperties(processInc, process);
        process.setCreate_time(DateUtil.getDateTime());
        process.setCreate_user(managerDao.find(TokenBean.activeUserId.get()).getUser_name());
        process.setUpdate_time(DateUtil.getDateTime());
        process.setUpdate_user(TokenBean.activeUserId.get());
        processDao.save(process);
    }

    @Override
    public void edit(ProcessInc processInc) {
        Process process = get(processInc.getId());
        BeanUtils.copyProperties(processInc, process);
        process.setUpdate_user(TokenBean.activeUserId.get());
        process.setUpdate_time(DateUtil.getDateTime());
        processDao.update(process);
    }

    @Override
    public void remove(String id) {
        Process oldProcess = get(id);
        processDao.delete(oldProcess);
    }

    @Override
    public Page<Process> page(Page<Process> page) {
        Page<Process> processPage;
        if (page.getCondition() == null)
            processPage = processDao.findPage(page);
        else
            processPage = processDao.findPage(page, getRestrictions("path_name", page.getCondition().getPath_name()));

        return processPage;
    }

    @Override
    public Process get(String id) {
        if (id == null || id.equals("")) {
            log.error("查询路线的id不能为空");
            throw new GlobalException("查询路线的id不能为空");
        }
        Process process = processDao.find(id);
        if (process == null) {
            log.error("查询id为" + id + "的路线，已不再数据库中了");
            throw new GlobalException("该条路线记录已被删除了");
        }
        return process;
    }
}
