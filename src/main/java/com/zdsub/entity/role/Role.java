package com.zdsub.entity.role;


import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 11:27
 **/
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    protected String id;
    protected String role_name;
    protected String cdescriprition;
    protected String context;
    @ManyToMany(mappedBy = "roles")
    protected Set<Manager> managers = new HashSet<>();
    @ManyToMany(mappedBy = "roles")
    protected Set<Menu> menus = new HashSet<>();
}
