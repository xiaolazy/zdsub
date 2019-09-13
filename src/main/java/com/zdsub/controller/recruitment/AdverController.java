package com.zdsub.controller.recruitment;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.recruitment.increase.AdverInc;
import com.zdsub.service.recruitment.AdverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.recruitment
 * @Author: ly
 * @CreateTime: 2019-09-06 20:43
 * @Description:
 */
@RestController
@RequestMapping("/adver/")
public class AdverController {
    @Autowired
    private AdverService adverService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid AdverInc adverInc, BindingResult bindingResult) {
        try{
            adverService.add(adverInc);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");

    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid AdverInc adverInc, BindingResult bindingResult) {
        try{
            adverService.edit(adverInc);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("修改错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");

    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        adverService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(adverService.get(id));
    }

    @GetMapping("/toAdverShow/{id}")
    public ResponseBean toAdverShow(@PathVariable String id) {
        return ResponseBean.SUCCESS(adverService.toAdverShow(id));
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<Adver> page) {
        return ResponseBean.SUCCESS(adverService.page(page));
    }
}
