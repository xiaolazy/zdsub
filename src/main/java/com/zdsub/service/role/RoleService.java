package com.zdsub.service.role;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.role.Role;
import com.zdsub.entity.role.increase.RoleInc;

import java.util.List;

public interface RoleService {
    Page<Role> getPage(Page<Role> page);
    List<Role> findAll();
    Role findById(String id);

    void add(Role role)throws Exception;
    void update(RoleInc role) throws Exception;
    void delById(String id) throws Exception;
    /*@description：更新当前用户登录的权限
     *@Date：2019/9/12 18:09
     *@Param：
     *@Return：
     *@Author： lyy
     */
    void showActivePermission(String id,String uId);
}
