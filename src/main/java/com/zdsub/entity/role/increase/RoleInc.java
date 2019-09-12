package com.zdsub.entity.role.increase;

import com.zdsub.entity.role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 15:15
 **/
@Getter@Setter
public class RoleInc extends Role {
    protected List<String> ids;
}
