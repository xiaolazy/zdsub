package com.zdsub.service.manager;

import com.zdsub.component.hibernate.Page;
import com.zdsub.component.hibernate.PageCondition;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.manager.increase.ManagerSaveInc;
import com.zdsub.entity.menu.increase.MenuInc;

import java.util.List;

public interface ManagerService {
    /*@description：登录时的查询
     *@Date：2019/9/11 16:23
     *@Param:ManagerInc
     *@Return:
     *@Author lyy
     */
    Manager findUseLogin(ManagerInc m);
    ManagerInc findById(String id);
    /*@description：带条件分页查询
     *@Date：2019/9/11 16:24
     *@Param
     *@Return
     *@Author lyy
     */
    Page<ManagerInc> getPage(Page<Manager> page);
    /*@description：通过Id查询
     *@Date：2019/9/11 17:13
     *@Param：
     *@Return：
     *@Author： lyy
     */
    Manager getById(String id);
    /*@description：通过ID删除
     *@Date：2019/9/12 12:35
     *@Param：
     *@Return：
     *@Author： lyy
     */
    void delById(String id) throws Exception;
    void add(ManagerSaveInc m)throws Exception;
    void update(ManagerSaveInc m)throws Exception;
    /*@description：查询用户名
     *@Date：2019/9/12 12:36
     *@Param：
     *@Return：
     *@Author： lyy
     */
    boolean findByName(String name);
}
