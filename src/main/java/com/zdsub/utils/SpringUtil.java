package com.zdsub.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-11 15:50
 **/

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }

    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //根据name
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //根据类型
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
