package com.zdsub.service.Manager;

import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.Manager.Manager;

import java.util.List;

public interface ManagerService {
    List<?> listAll();
    Object findManager(String id);
    Page<Manager> page(Page page);
}
