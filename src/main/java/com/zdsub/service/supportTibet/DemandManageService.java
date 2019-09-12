package com.zdsub.service.supportTibet;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.PageCondition;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;


import java.util.List;

public interface DemandManageService {
    /**
     * 新增学校
     *
     * @param demandManageInc
     */
    void add(DemandManageInc demandManageInc);

    /**
     * 更新学校
     *
     * @param demandManageInc
     */
    void edit(DemandManageInc demandManageInc);

    /**
     * 删除学校
     *
     * @param id
     */
    void remove(String id);

    /**
     * 分页查询学校
     *
     * @param page
     * @return
     */
    Page<DemandManage> page(Page page);

    Page<DemandManage> page(PageCondition<DemandManage> demandManagePageCondition);

    /**
     * 查看学校
     * @param id
     * @return
     */
    DemandManage get(String id);


    /**
     * 查询全部学校
     *
     * @return
     */
    List<DemandManage> listAll();
}
