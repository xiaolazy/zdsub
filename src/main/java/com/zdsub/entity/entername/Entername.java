package com.zdsub.entity.entername;

import com.zdsub.entity.university.School;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 19:23
 **/
@Entity @Table(name = "entername")@Getter@Setter
public class Entername{
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    protected String id;
    protected String telephone;
    protected String qq;
    protected String course_name;
    @OneToOne
    @JoinColumn(name = "sch_id")
    protected School school;
    protected String need_id;
    protected String user_name;
    protected String sch_name;
    protected Date create_time;
}
