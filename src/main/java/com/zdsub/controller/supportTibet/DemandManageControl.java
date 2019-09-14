package com.zdsub.controller.supportTibet;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.supportTibet.increase.DemandManageInc;
import com.zdsub.service.supportTibet.DemandManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/need/")
public class DemandManageControl {

    @Autowired
    private DemandManageService demandManageService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid DemandManageInc demandManageInc, BindingResult bindingResult) {
        try {
            demandManageService.add(demandManageInc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");

    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid DemandManageInc demandManageInc, BindingResult bindingResult) {
        try {
            demandManageService.edit(demandManageInc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.FAILD("修改错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");
    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        try {
            demandManageService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.FAILD("删除失败！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("删除成功！");


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
