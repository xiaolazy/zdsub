package com.zdsub.entity.recruitment;

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
@Entity
@Table(name = "advertises")
@Getter
@Setter
public class Adver {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id;
    protected String title;
    protected String context;
    protected int read_num;//阅读数
    protected Date createTime;
    protected String create_user;
    protected Date update_time;
    protected String update_user;
    @ManyToOne
    @JoinColumn(name = "sch_id")
    protected School school;

}
