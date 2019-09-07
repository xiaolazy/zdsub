package com.zdsub.entity.recruitment.Increase;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.recruitment.Increase
 * @Author: ly
 * @CreateTime: 2019-09-07 14:55
 * @Description:
 */
@Getter
@Setter
public class AdverInc {
    protected String id;
    @NotBlank(message = "标题不能为空")
    protected String title;
    @NotBlank(message = "详细信息不能为空")
    protected String context;
    protected int read_num;//阅读数
    @NotBlank(message = "所选择的学校不能为空")
    protected String schId;
}
