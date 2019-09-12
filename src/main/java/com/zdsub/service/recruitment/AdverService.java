package com.zdsub.service.recruitment;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.recruitment.increase.AdverInc;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.recruitment
 * @Author: ly
 * @CreateTime: 2019-09-06 20:26
 * @Description:
 */
public interface AdverService {
    /**
     * 新增人才
     *
     * @param adverInc
     */
    void add(AdverInc adverInc);

    /**
     * 更新人才
     *
     * @param adverInc
     */
    void edit(AdverInc adverInc);

    /**
     * 删除人才
     *
     * @param id
     */
    void remove(String id);


    /**
     * 查看人才
     *
     * @param id
     * @return
     */
    Adver get(String id);

    /**
     * 分页查询人才
     *
     * @param page
     * @return
     */
    Page<Adver> page(Page<Adver> page);

    /**
     * 查看人才并增加阅读数
     * @param id
     * @return
     */
    Adver toAdverShow(String id);
}
