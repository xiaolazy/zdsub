package com.zdsub.entity.work.Increase;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.work.Increase
 * @Author: ly
 * @CreateTime: 2019-09-07 15:27
 * @Description:
 */

@Getter
@Setter
public class ProcessInc {

    protected String id;
    @NotBlank(message = "路径不能为空")
    @Length(max = 48, message = "传入的长度不能大于48")
    protected String path_name;
    protected String context;
    protected String sch_id;
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
