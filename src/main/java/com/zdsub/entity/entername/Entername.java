package com.zdsub.entity.entername;

import com.zdsub.entity.university.School;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "电话号码不能为空！")
    protected String telephone;
    @NotBlank(message = "QQ不能为空！")
    protected String qq;
    @NotBlank(message = "课程名不能为空！")
    protected String course_name;
    @OneToOne
    @JoinColumn(name = "sch_id")
    protected School school;
    protected String need_id;
    @NotBlank(message = "姓名不能为空！")
    protected String user_name;
    @NotBlank(message = "学校名称不能为空！")
    protected String sch_name;
    protected Date create_time;
}
