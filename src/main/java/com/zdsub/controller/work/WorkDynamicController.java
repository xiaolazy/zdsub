package com.zdsub.controller.work;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.service.work.ProcessService;
import com.zdsub.service.work.WorkDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.work
 * @Author: ly
 * @CreateTime: 2019-09-07 22:24
 * @Description:
 */
@RestController
@RequestMapping("/workDynamic/")
public class WorkDynamicController {
    @Autowired
    private WorkDynamicService workDynamicService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid WorkDynamic workDynamic) {
        workDynamicService.add(workDynamic);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid WorkDynamic workDynamic) {
        workDynamicService.edit(workDynamic);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable  String id) {
        workDynamicService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(workDynamicService.get(id));
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<WorkDynamic> page) {
        return ResponseBean.SUCCESS(workDynamicService.page(page));
    }
}
