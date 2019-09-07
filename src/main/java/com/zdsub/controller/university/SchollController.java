package com.zdsub.controller.university;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.Hibernate.Page;
import com.zdsub.entity.university.increase.SchoolInc;
import com.zdsub.service.university.SchollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.university
 * @Author: ly
 * @CreateTime: 2019-09-06 19:08
 * @Description:
 */
@RestController
@RequestMapping("/school/")
public class SchollController {

    @Autowired
    private SchollService schollService;

    @PostMapping("add")
    public ResponseBean add(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        schollService.add(schoolInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    public ResponseBean edit(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        schollService.edit(schoolInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/remove")
    public ResponseBean remove(String id) {
        schollService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("/get")
    public ResponseBean get(String id) {
        return ResponseBean.SUCCESS(schollService.get(id));
    }

    @GetMapping("/listAll")
    public ResponseBean listAll() {
        return ResponseBean.SUCCESS(schollService.listAll());
    }

    @PostMapping("page")
    public ResponseBean page(Page page) {
        return ResponseBean.SUCCESS(schollService.page(page));
    }
}
