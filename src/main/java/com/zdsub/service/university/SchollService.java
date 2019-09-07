package com.zdsub.service.university;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.university.Increase.SchoolInc;
import com.zdsub.entity.university.School;

import java.util.List;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.service.university
 * @Author: ly
 * @CreateTime: 2019-09-06 11:05
 * @Description:
 */
public interface SchollService {

    /**
     * 新增学校
     *
     * @param schoolInc
     */
    void add(SchoolInc schoolInc);

    /**
     * 更新学校
     *
     * @param schoolInc
     */
    void edit(SchoolInc schoolInc);

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
    Page<School> page(Page page);

    /**
     * 查看学校
     * @param id
     * @return
     */
    School get(String id);


    /**
     * 查询全部学校
     *
     * @return
     */
    List<School> listAll();
}
