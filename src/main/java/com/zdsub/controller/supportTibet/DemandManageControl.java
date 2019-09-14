package com.zdsub.controller.supportTibet;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;
import com.zdsub.service.supportTibet.DemandManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.zdsub.component.annotion.ValidLog;
import javax.validation.Valid;

@RestController
@RequestMapping("/need/")
public class DemandManageControl {

    @Autowired
    private DemandManageService demandManageService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid DemandManageInc demandManageInc, BindingResult bindingResult) {
        demandManageService.add(demandManageInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid DemandManageInc demandManageInc, BindingResult bindingResult) {
        demandManageService.edit(demandManageInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        demandManageService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(demandManageService.get(id));
    }


    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<DemandManage> page) {
        return ResponseBean.SUCCESS(demandManageService.page(page));
    }

}
