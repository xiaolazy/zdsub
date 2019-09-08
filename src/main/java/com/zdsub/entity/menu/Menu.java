package com.zdsub.entity.menu;

import com.zdsub.entity.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 15:05
 **/

@Entity
@Table(name = "Menu")
@Getter
@Setter
public class Menu {
    @Id
    protected String id;
    @NotBlank(message = "菜单名称不能为空！")
    protected String menu_name;
    protected String menu_url;
    protected String cdescription;
    protected String create_time;
    protected String update_time;
    protected String create_user;
    protected String update_user;
    protected String pid;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MENU_ROLE",
    joinColumns = @JoinColumn(name = "MENU_ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    protected Set<Role> roles = new HashSet<>();
}
