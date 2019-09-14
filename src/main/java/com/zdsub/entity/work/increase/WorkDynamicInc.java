package com.zdsub.entity.work.increase;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.work.increase
 * @Author: ly
 * @CreateTime: 2019-09-13 10:36
 * @Description:
 */
@Getter
@Setter
public class WorkDynamicInc {

    protected String id;
    @NotBlank(message = "标题不能为空")
    @Length(max = 48, message = "最大长度不能超过48")
    protected String title;
    @NotBlank(message = "内容不能为空")
    protected String context;
    protected int read_num;//阅读数
    @NotBlank(message = "所选择的学校不能为空")
    protected String schId;
}
