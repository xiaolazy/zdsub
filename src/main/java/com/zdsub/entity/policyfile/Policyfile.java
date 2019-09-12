package com.zdsub.entity.policyfile;

import com.zdsub.entity.manager.Manager;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @program: zdsub
 * @description: 援藏政策
 * @author: lyy
 * @generate: 2019-09-11 23:36
 **/
@Entity@Table(name = "policyfile")@Getter@Setter
public class Policyfile {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    protected String id;
    @NotBlank(message = "标题不能为空！")
    protected String title;
    @NotBlank(message = "文件URL不有为空！")
    protected String url;
    protected String create_time;
    @OneToOne
    @JoinColumn(name = "create_user")
    protected Manager create_user;
    protected String update_time;
    protected String update_user;
}
