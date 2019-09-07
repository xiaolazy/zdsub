package com.zdsub.service.work;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.work.WorkDynamic;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.work
 * @Author: ly
 * @CreateTime: 2019-09-07 21:54
 * @Description:
 */
public interface WorkDynamicService {
    /**
     * 新增工作动态
     *
     * @param workDynamic
     */
    void add(WorkDynamic workDynamic);

    /**
     * 更新工作动态
     *
     * @param workDynamic
     */
    void edit(WorkDynamic workDynamic);

    /**
     * 删除工作动态
     *
     * @param id
     */
    void remove(String id);

    /**
     * 分页查询工作动态
     *
     * @param page
     * @return
     */
    Page<WorkDynamic> page(Page<WorkDynamic> page);

    /**
     * 查看工作动态
     *
     * @param id
     * @return
     */
    WorkDynamic get(String id);
}
