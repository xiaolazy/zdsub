package com.zdsub.controller.role;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.role.Role;
import com.zdsub.entity.role.increase.RoleInc;
import com.zdsub.service.role.RoleService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-11 21:46
 **/

@RestController
@RequestMapping("/role/")
public class RoleControl {
    @Resource
    private RoleService roleService;
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Role>page){
        return ResponseBean.PAGESUCCESS(roleService.getPage(page));
    }
    @GetMapping("findAll")
    public ResponseBean findAll(){
        return ResponseBean.SUCCESS(roleService.findAll());
    }
    @GetMapping("findById")
    public ResponseBean findById(String id){
        return ResponseBean.SUCCESS(roleService.findById(id));
    }
    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@Valid @RequestBody Role role, BindingResult b) throws Exception{
        roleService.add(role);
        try{

            return ResponseBean.SUCCESS("权限新增成功！");
        }catch (Exception e){
            return ResponseBean.FAILD("新增权限失败！请稍候重试");
        }
    }
    @PostMapping("update")
    @ValidLog
    public ResponseBean update(@Valid @RequestBody RoleInc role, BindingResult b){
        try{
            roleService.update(role);
            return ResponseBean.SUCCESS("修改成功！");
        }catch (Exception e){
            return ResponseBean.FAILD("修改权限失败！请稍候重试");
        }
    }
    @GetMapping("delById")
    public ResponseBean delById(String id){
        try{
            roleService.delById(id);
            return ResponseBean.SUCCESS("删除成功！");
        }catch (Exception e){
            return ResponseBean.FAILD("删除权限失败！请稍候重试或联系管理");
        }

    }
}
