package com.zdsub.entity.manager.increase;

import com.zdsub.entity.manager.Manager;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-11 23:00
 **/
@Getter@Setter
public class ManagerSaveInc extends Manager {
    @NotBlank(message = "用户学校Id不能为空！")
    protected String schId;
    @NotBlank(message = "用户权限Id不能为空！")
    protected String roleId;
    @NotBlank(message = "电话号码不能为空！")
    protected String telephone;
}
