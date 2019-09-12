package com.zdsub.entity.role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@JsonIgnoreProperties({"menus"})
public class Role {
    @Id
    @GenericGenerator(name = "native",strategy = "native")
    @GeneratedValue(generator = "native")
    protected String id;
    @NotBlank(message = "权限名称不能为空！")
    protected String role_name;
    protected String cdescriprition;
    protected String context;
    @ManyToMany(mappedBy = "roles")
    protected Set<Menu> menus = new HashSet<>();
}
