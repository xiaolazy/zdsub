package com.zdsub.entity.entername.increase;

import com.zdsub.entity.entername.Entername;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 19:23
 **/
@Getter@Setter
public class EnternameInc extends Entername {
    @NotBlank(message = "学校ID不能为空！")
    protected String sch_id;
}
