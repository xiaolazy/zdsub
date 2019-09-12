package com.zdsub.entity.supportTibet.increase;

import com.zdsub.entity.supportTibet.DemandManage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DemandManageInc extends DemandManage {
    protected String sch_name;

}
