package com.zdsub.utils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * @program: zdsub
 * @description: 目前可用的page简单工具
 * @author: lyy
 * @generate: 2019-09-11 20:16
 **/
public class PageUtil {
    public static SimpleExpression getRestrictions(String name,String val){
        if(StringUtils.isNotBlank(val))
            return Restrictions.like(name, "%"+val+"%");
        return Restrictions.like(name, "%%");
    }
}
