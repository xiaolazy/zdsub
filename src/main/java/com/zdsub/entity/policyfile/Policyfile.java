package com.zdsub.entity.policyfile;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: zdsub
 * @description: 援藏政策
 * @author: lyy
 * @generate: 2019-09-11 23:36
 **/
@Entity@Table(name = "policyfile")@Getter@Setter
public class Policyfile {
    @Id
    @GenericGenerator(name = "native",strategy = "native")
    @GeneratedValue(generator = "native")
    protected String id;
    protected String title;
    protected String url;
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;
}
