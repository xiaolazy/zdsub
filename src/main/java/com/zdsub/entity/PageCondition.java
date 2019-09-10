package com.zdsub.entity;

import com.zdsub.component.hibernate.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.entity
 * @Author: ly
 * @CreateTime: 2019-09-08 20:41
 * @Description:
 */
@Getter
@Setter
public class PageCondition<T> {
    private T condition;
    Page<T> page;


}
