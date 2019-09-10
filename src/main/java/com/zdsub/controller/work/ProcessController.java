package com.zdsub.controller.work;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.increase.ProcessInc;
import com.zdsub.service.work.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    @ValidLog
    public ResponseBean add(@RequestBody @Valid ProcessInc processInc, BindingResult bindingResult) {
        processService.add(processInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid ProcessInc processInc,BindingResult bindingResult) {
        processService.edit(processInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        processService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable  String id) {
        return ResponseBean.SUCCESS(processService.get(id));
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<Process> page) {
        return ResponseBean.SUCCESS(processService.page(page));
    }
}
