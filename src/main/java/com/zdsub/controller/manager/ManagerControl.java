package com.zdsub.controller.manager;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.common.constant.Common;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.hibernate.PageCondition;
import com.zdsub.component.token.TokenBean;
import com.zdsub.component.token.TokenPermission;
import com.zdsub.entity.manager.increase.ManagerInc;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.manager.increase.ManagerSaveInc;
import com.zdsub.entity.role.Role;
import com.zdsub.service.manager.ManagerService;
import com.zdsub.service.role.RoleService;
import com.zdsub.utils.Base64Util;
import com.zdsub.utils.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import static com.zdsub.common.constant.Common.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.zdsub.utils.Md5Util.*;
/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 23:07
 **/
@RestController
@RequestMapping("/manager/")
public class ManagerControl {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ManagerService managerService;
    @Resource
    private RoleService roleService;
    /*@description：分页
     *@Date：2019/9/12 12:31
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @RequestMapping("login")
    @ValidLog
    public ResponseBean login(@Valid @RequestBody ManagerInc manager, BindingResult b,
                              HttpServletResponse res) {
        manager.setPass_word(Md5(manager.getPass_word()));
        Manager m = managerService.findUseLogin(manager);
        if (null == m)
            return ResponseBean.FAILD("用户名或密码错误！");
        String jwt = Jwt.createJWT(m.getUser_name(), m.getId(),
                m.getUser_name(), USER_LOGIN_TIME, Base64Util.Encoder(SALT));

        //存入用户登录的TOKEN
        TokenBean.getInstance().put(jwt, jwt);
        //用于前台可接收响应头中的TOKEN信息
        res.setHeader("Access-Control-Expose-Headers", Common.AUTHORIZATION);
        //设置响应头
        res.setHeader(AUTHORIZATION, jwt);
        //设置当前用户的权限
        roleService.showActivePermission(m.getRole_id());
        logger.debug(m.getUser_name() + "成功登录系统");
        return ResponseBean.SUCCESS("登录成功！", manager.getUser_name());
    }
    /*@description：登出系统
     *@Date：2019/9/12 22:21
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("logout")
    public ResponseBean logout(String key){
        if(TokenBean.getInstance().remove(key) == null)
            return ResponseBean.FAILD("注销失败,用户信息不存在!");
        TokenBean.activeUserId.set("");
        TokenBean.activeUser.set("");
        if(TokenPermission.getInstance().remove(TokenBean.activeUserId) == null)
            return ResponseBean.FAILD("用户权限信息不存在，但是已强制为您注销！");
        return ResponseBean.SUCCESS("欢迎下次再来!");
    }
    /*@description：带条件分页查询
     *@Date：2019/9/11 17:16
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Manager> page) {
        return ResponseBean.PAGESUCCESS(managerService.getPage(page));
    }

    /*@description：通过ID查询
     *@Date：2019/9/11 17:16
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("getById")
    public ResponseBean getById(String id) {
        return ResponseBean.SUCCESS(managerService.findById(id));
    }

    @GetMapping("delById")
    public ResponseBean delById(String id) {
        try {
            managerService.delById(id);
            return ResponseBean.SUCCESS("删除成功！");
        } catch (Exception e) {
            return ResponseBean.FAILD("删除用户失败！请稍候重试或联系管理");
        }

    }

    /*@description：新增
     *@Date：2019/9/12 12:31
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@Valid @RequestBody ManagerSaveInc m, BindingResult b) {
        try {
            managerService.add(m);
            return ResponseBean.SUCCESS("新增成功！");
        } catch (Exception e) {
            return ResponseBean.FAILD("新增失败，请稍候重试或联系管理员！");
        }
    }

    /*@description：修改
     *@Date：2019/9/12 12:32
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("update")
    @ValidLog
    public ResponseBean update(@Valid @RequestBody ManagerSaveInc m, BindingResult b) {
        try {
            managerService.update(m);
            return ResponseBean.SUCCESS("修改成功！");
        } catch (Exception e) {
            return ResponseBean.FAILD("修改失败，请稍候重试或联系管理员！");
        }
    }
    /*@description：验证用户名是否存在  用于用户新增时判断用户名称是否存在，true则是存在否则不存在
     *@Date：2019/9/12 12:32
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("verifyByName")
    public ResponseBean verifyByName(String name) {
        return ResponseBean.SUCCESS(managerService.findByName(name));
    }
}
