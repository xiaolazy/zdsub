package com.zdsub.controller.university;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.university.School;
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
    @ValidLog
    public ResponseBean add(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        try{
            schollService.add(schoolInc);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");

    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        try{
            schollService.edit(schoolInc);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("修改错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");

    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        schollService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(schollService.get(id));
    }

    @GetMapping("listAll")
    public ResponseBean listAll() {
        return ResponseBean.SUCCESS(schollService.listAll());
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<School> page) {
        return ResponseBean.SUCCESS(schollService.page(page));
    }

}


