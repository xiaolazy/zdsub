package com.zdsub.component.Hibernate;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-04 21:51
 **/
@Getter
@Setter
public class PageCondition<T extends Object> {
    protected T condition;
}
