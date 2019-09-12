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
}
