package com.zdsub.entity.work;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.work
 * @Author: ly
 * @CreateTime: 2019-09-07 09:13
 * @Description:
 */
@Entity
@Table(name = "process")
@Getter
@Setter
public class Process {
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id ;
    protected String path_name;
    protected String context;
    protected String sch_id;
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
