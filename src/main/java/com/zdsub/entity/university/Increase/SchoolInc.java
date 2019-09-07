package com.zdsub.entity.university.Increase;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.university.Increase
 * @Author: ly
 * @CreateTime: 2019-09-07 14:22
 * @Description:
 */
@Getter
@Setter
public class SchoolInc {

    protected String id;
    @NotBlank(message = "学校的名称不能为空")
    @Length(max = 48, min = 1, message = "学校的名称不能超过48个字符")
    protected String sch_name;
    @NotBlank(message = "学校的援藏历史不能为空")
    protected String sup_history;
    @NotBlank(message = "援藏学校信息不能为空")
    protected String message;
    @NotBlank(message = "是否为接受援助高校不能为空")
    protected Integer rec_school;
    @NotBlank(message = "是否为援藏高校不能为空")
    protected Integer is_zup;

}
