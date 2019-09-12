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
    /*@description：分页查询权限
     *@Date：2019/9/12 13:13
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Role>page){
        return ResponseBean.PAGESUCCESS(roleService.getPage(page));
    }
    /*@description：查询所有权限
     *@Date：2019/9/12 13:13
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("findAll")
    public ResponseBean findAll(){
        return ResponseBean.SUCCESS(roleService.findAll());
    }
    /*@description：据ID查询权限
     *@Date：2019/9/12 13:13
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("findById")
    public ResponseBean findById(String id){
        return ResponseBean.SUCCESS(roleService.findById(id));
    }
    /*@description：新增
     *@Date：2019/9/12 13:14
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@Valid @RequestBody Role role, BindingResult b){
        try{
            roleService.add(role);
            return ResponseBean.SUCCESS("权限新增成功！");
        }catch (Exception e){
            return ResponseBean.FAILD("新增权限失败！请稍候重试");
        }
    }
    /*@description：修改
     *@Date：2019/9/12 13:14
     *@Param：
     *@Return：
     *@Author： lyy
     */
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
    /*@description：据ID删除
     *@Date：2019/9/12 13:14
     *@Param：
     *@Return：
     *@Author： lyy
     */
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
