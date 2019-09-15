package com.zdsub.service.supportTibet;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.PageCondition;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;
import com.zdsub.entity.work.WorkDynamic;


import java.util.List;

public interface DemandManageService {
    /**
     * 新增需求
     *
     * @param demandManageInc
     */
    void add(DemandManageInc demandManageInc);

    /**
     * 更新需求
     *
     * @param demandManageInc
     */
    void edit(DemandManageInc demandManageInc);

    /**
     * 删除需求
     *
     * @param id
     */
    void remove(String id);


    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<DemandManage> page(Page<DemandManage> page);

    /**
     * 查看需求
     *
     * @param id
     * @return
     */
    DemandManage get(String id);


    DemandManage updAdverShow(String id);

}
