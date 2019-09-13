package com.zdsub.entity.university;

import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.work.WorkDynamic;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    protected String id;
    protected String sch_name;
    protected String sup_history;
    protected String message;
    protected Integer rec_school;
    protected Integer is_zup;
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected transient Set<Adver> positions = new HashSet<>();
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected transient Set<WorkDynamic> workDynamics = new HashSet<>();
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String update_user;

}
