package com.zdsub.entity.work;

import com.zdsub.entity.university.School;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.work
 * @Author: ly
 * @CreateTime: 2019-09-07 21:43
 * @Description:
 */
@Entity
@Table(name = "workdynamic")
@Getter
@Setter
public class WorkDynamic {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id;
    @NotBlank(message = "标题不能为空")
    @Length(max = 48, message = "最大长度不能超过48")
    protected String title;
    @NotBlank(message = "内容不能为空")
    protected String context;
    protected int read_num;//阅读数
    @ManyToOne
    @JoinColumn(name = "sch_id")
    protected School school;
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;
}
