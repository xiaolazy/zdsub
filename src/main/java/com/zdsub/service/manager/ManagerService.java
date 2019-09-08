package com.zdsub.service.manager;

import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.increase.MenuInc;

import java.util.List;

public interface ManagerService {
    Manager findUseLogin(ManagerInc m);
}
