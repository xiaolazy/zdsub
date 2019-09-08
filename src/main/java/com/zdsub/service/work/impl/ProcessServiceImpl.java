package com.zdsub.service.work.impl;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.dao.work.ProcessDao;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.entity.work.Process;
import com.zdsub.service.work.ProcessService;
import com.zdsub.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void add(ProcessInc processInc) {
        Process process = new Process();
        BeanUtils.copyProperties(processInc, process);
        process.setCreate_time(DateUtil.getDateTime());
        process.setCreate_user("===========");
        process.setUpdate_time(DateUtil.getDateTime());
        process.setUpdate_user("===========");
        processDao.save(process);
    }

    @Override
    public void edit(ProcessInc processInc) {
        Process process = get(processInc.getId());
        BeanUtils.copyProperties(processInc, process);
        process.setUpdate_user("=========");
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
        Process process = (Process) page.getCondition();
        process.setPath_name("%" + process.getPath_name() + "%");
        return processDao.findPage(page);
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
