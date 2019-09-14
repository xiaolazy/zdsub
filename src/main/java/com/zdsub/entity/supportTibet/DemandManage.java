package com.zdsub.entity.supportTibet;

import com.zdsub.entity.university.School;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "need")
@Getter
@Setter
public class DemandManage {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id;
    @NotBlank(message = "标题不能为空！")
    protected String title;
    protected String context;
    protected Integer read_num;
    protected String send_time;
    @NotNull(message = "需求不能为空！")
    protected Integer level;
    @ManyToOne
    @JoinColumn(name = "sch_id")
    protected School school;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
