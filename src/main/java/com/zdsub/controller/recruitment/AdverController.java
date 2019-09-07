package com.zdsub.controller.recruitment;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.Hibernate.Page;
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
    public ResponseBean add(@RequestBody @Valid AdverInc adverInc, BindingResult bindingResult) {
        adverService.add(adverInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    public ResponseBean edit(@RequestBody @Valid AdverInc adverInc, BindingResult bindingResult) {
        adverService.edit(adverInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/remove")
    public ResponseBean remove(String id) {
        adverService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/get")
    public ResponseBean get(String id) {
        return ResponseBean.SUCCESS(adverService.get(id));
    }

    @GetMapping("/toAdverShow")
    public ResponseBean toAdverShow(String id) {
        return ResponseBean.SUCCESS(adverService.get(id));
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page page) {
        return ResponseBean.SUCCESS(adverService.page(page));
    }
}
