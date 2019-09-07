package com.zdsub.entity.Manager;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "userNAME不能为空！！")
    protected String user_name;
    protected String pass_word;
    protected String sch_id;
    protected String telephone;
    protected String role_id;
    protected String create_time;
    protected String create_user;
}
