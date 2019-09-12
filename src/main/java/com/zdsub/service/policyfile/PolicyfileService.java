package com.zdsub.service.policyfile;

import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.policyfile.Policyfile;

import java.util.List;

public interface PolicyfileService {
    Page<Policyfile> getPage(Page<Policyfile> page);
    Policyfile findById(String id);
}
