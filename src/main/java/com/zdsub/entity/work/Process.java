package com.zdsub.entity.work;

import com.zdsub.entity.manager.Manager;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id ;
    protected String path_name;
    protected String context;
    protected String sch_id;
    protected String create_time;
    @ManyToOne
    @JoinColumn(name = "create_user")
    protected Manager create_user;
    protected String update_time;
    protected String update_user;

}
