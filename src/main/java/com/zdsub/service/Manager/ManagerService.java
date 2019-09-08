package com.zdsub.service.Manager;

import com.zdsub.entity.manager.Increase.ManagerInc;
import com.zdsub.entity.manager.Manager;

public interface ManagerService {
    Manager findUseLogin(ManagerInc m);
}
