package com.zdsub.entity.university;

import com.zdsub.entity.university.Increase.SchoolInc;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity.university
 * @Author: ly
 * @CreateTime: 2019-09-06 10:51
 * @Description:
 */
@Entity
@Table(name = "school")
@Getter
@Setter
public class School {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuidGen")
    @GeneratedValue(generator = "uuidGen")
    protected String id ;
    protected String sch_name;
    protected String sup_history;
    protected String message;
    protected Integer rec_school;
    protected Integer is_zup;
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
