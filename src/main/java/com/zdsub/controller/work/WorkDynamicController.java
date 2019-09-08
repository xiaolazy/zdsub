package com.zdsub.controller.work;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.work.WorkDynamic;
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
    public ResponseBean add(@RequestBody @Valid WorkDynamic workDynamic) {
        workDynamicService.add(workDynamic);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    public ResponseBean edit(@RequestBody @Valid WorkDynamic workDynamic) {
        workDynamicService.edit(workDynamic);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("remove")
    public ResponseBean remove(String id) {
        workDynamicService.remove(id);
        return ResponseBean.SUCCESS();
    }
    @GetMapping("/toAdverShow")
    public ResponseBean toAdverShow(String id) {
        return ResponseBean.SUCCESS(workDynamicService.toAdverShow(id));
    }
    @GetMapping("get")
    public ResponseBean get(String id) {
        return ResponseBean.SUCCESS(workDynamicService.get(id));
    }

    @PostMapping("page")
    public ResponseBean page(Page<WorkDynamic> page) {
        return ResponseBean.SUCCESS(workDynamicService.page(page));
    }
}
