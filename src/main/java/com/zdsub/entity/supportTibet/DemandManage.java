package com.zdsub.entity.supportTibet;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "需求不能为空！")
    protected Integer level;
    @NotBlank(message = "发布学校不能为空！")
    protected String sch_id;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
