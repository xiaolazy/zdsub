package com.zdsub.service.entername;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.entername.Entername;
import com.zdsub.entity.entername.increase.EnternameInc;

public interface EnternameService {
    /*@description：分页
     *@Date：2019/9/12 23:16
     *@Param： Page<Entername> page
     *@Return： Page<Entername>
     *@Author： lyy
     */
    Page<Entername> getPage(Page<Entername> page);
    void save(EnternameInc entername)throws Exception;
}
