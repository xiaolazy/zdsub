package com.zdsub.service.work;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.entity.work.Process;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.work
 * @Author: ly
 * @CreateTime: 2019-09-07 09:20
 * @Description:
 */
public interface ProcessService {
    /**
     * 新增路线
     *
     * @param processInc
     */
    void add(ProcessInc processInc);

    /**
     * 更新路线
     *
     * @param processInc
     */
    void edit(ProcessInc processInc);

    /**
     * 删除路线
     *
     * @param id
     */
    void remove(String id);

    /**
     * 分页查询路线
     *
     * @param page
     * @return
     */
    Page<Process> page(Page<Process> page);

    /**
     * 查看路线
     *
     * @param id
     * @return
     */
    Process get(String id);
}
