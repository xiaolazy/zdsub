package com.zdsub.controller.work;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.entity.work.increase.WorkDynamicInc;
import com.zdsub.service.work.WorkDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/work/")
public class WorkDynamicController {
    @Autowired
    private WorkDynamicService workDynamicService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid WorkDynamicInc workDynamicInc, BindingResult bindingResult) {
        try{
            workDynamicService.add(workDynamicInc);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");
    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid WorkDynamicInc workDynamic, BindingResult bindingResult) {
        try{
            workDynamicService.edit(workDynamic);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("修改错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");
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
