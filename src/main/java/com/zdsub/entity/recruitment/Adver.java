package com.zdsub.entity.recruitment;

import com.zdsub.entity.manager.Manager;
import com.zdsub.entity.university.School;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.recruitment.Adver
 * @Author: ly
 * @CreateTime: 2019-09-06 09:33
 * @Description:
 */
@Getter
@Setter
@Entity
@Table(name = "advertises")
public class Adver {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id;
    protected String title;
    protected String context;
    protected int read_num;//阅读数
    protected String create_time;
    @ManyToOne
    @JoinColumn(name = "create_user")
    protected Manager create_user;
    protected String update_time;
    protected String update_user;
    @ManyToOne
    @JoinColumn(name = "sch_id")
    protected School school;

}

