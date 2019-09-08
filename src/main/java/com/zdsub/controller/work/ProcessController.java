package com.zdsub.controller.work;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.entity.work.Process;
import com.zdsub.service.work.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.work
 * @Author: ly
 * @CreateTime: 2019-09-07 09:29
 * @Description:
 */
@RestController
@RequestMapping("/process/")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @PostMapping("add")
    public ResponseBean add(@RequestBody @Valid ProcessInc processInc) {
        processService.add(processInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    public ResponseBean edit(@RequestBody @Valid ProcessInc processInc) {
        processService.edit(processInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/remove")
    public ResponseBean remove(String id) {
        processService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/get")
    public ResponseBean get(String id) {
        return ResponseBean.SUCCESS(processService.get(id));
    }

    @PostMapping("page")
    public ResponseBean page(Page<Process> page) {
        return ResponseBean.SUCCESS(processService.page(page));
    }
}
