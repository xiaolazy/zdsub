package com.zdsub.common.ResultBean;

import static com.zdsub.common.constant.Common.*;
import com.zdsub.component.Hibernate.Page;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: zdsub
 * @description: 服务器返回前台格式类
 * @author: lyy
 * @generate: 2019-09-04 17:01
 **/
@Getter
@Setter
public class ResponseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status;
    private String msg;
    private Object data;
    private Page page;
    /**
     * 什么都不带的成功返回
     * */
    public static ResponseBean SUCCESS(){
        return new ResponseBean(SUCCESS);
    }
    /**
     * 带msg的成功返回
     * */
    public static ResponseBean SUCCESS(String msg){
        return new ResponseBean(SUCCESS,msg);
    }
    /**
     * 带data的成功返回
     * */
    public static ResponseBean SUCCESS(Object data){
        return new ResponseBean(SUCCESS,data);
    }
    /**
     * 带msg和data的成功返回
     * */
    public static ResponseBean SUCCESS(String msg,Object data){
        return new ResponseBean(SUCCESS,msg,data);
    }
    /**
     * 带msg和data的成功返回
     * */
    public static ResponseBean PAGESUCCESS(Page page){
        return new ResponseBean(SUCCESS,page);
    }
    /**
     * 什么都不带的失败返回
     * */
    public static ResponseBean FAILD(){
        return new ResponseBean(FAIL);
    }
    /**
     * 带msg的失败返回
     * */
    public static ResponseBean FAILD(String msg){
        return new ResponseBean(FAIL,msg);
    }
    public ResponseBean(int status){
        this.status = status;
    }
    public ResponseBean (int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    public ResponseBean (int status,Object data){
        this.status = status;
        this.data = data;
    }
    public ResponseBean (int status,String msg,Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public ResponseBean(int status,Page page){
        this.status = status;
        this.page = page;
    }
}
