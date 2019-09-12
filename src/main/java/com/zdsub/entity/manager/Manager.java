package com.zdsub.entity.manager;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zdsub.entity.role.Role;
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
 * @generate: 2019-09-04 15:20
 **/
@Entity
@Table(name = "manager")
@Getter
@Setter
public class Manager {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    protected String id;
    @NotBlank(message = "用户名不能为空！！")
    protected String user_name;
    @NotBlank(message = "密码不能为空！！")
    protected String pass_word;
    protected String sch_id;
    protected String telephone;
    protected String role_id;
    protected String create_time;
    protected String create_user;
//    @OneToOne
//    protected Role role;
}
