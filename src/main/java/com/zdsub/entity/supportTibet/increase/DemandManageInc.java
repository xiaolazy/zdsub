package com.zdsub.entity.supportTibet.increase;

import com.zdsub.entity.supportTibet.DemandManage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DemandManageInc extends DemandManage {
    @NotBlank(message = "所选择的学校不能为空")
    protected String schId;
}
