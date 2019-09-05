package com.zdsub.component.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: zdsub
 * @description: 响应字符处理器
 * **************在spring容器完成bean的实例化、配置之前或之后添加一些逻辑处理操作
 * **************接口中的两个方法都要将传入的bean返回、而不能返回Null。否则getBean将无法取得目标
 * **************实例化多少对象则会执行多少次
 * @author: lyy
 * @generate: 2019-09-05 19:21
 **/
@Component
public class CharacterBeanPostProcessor implements BeanPostProcessor {
    /******实例化、配置之前******/
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /******实例化、配置之后******/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof StringHttpMessageConverter) {
            MediaType mediaType = new MediaType("text", "plain", Charset.forName("UTF-8"));
            List<MediaType> types = new ArrayList<MediaType>();
            types.add(mediaType);
            ((StringHttpMessageConverter) bean).setSupportedMediaTypes(types);
        }
        return bean;
    }
}
