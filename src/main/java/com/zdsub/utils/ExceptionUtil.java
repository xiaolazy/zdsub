package com.zdsub.utils;

import com.zdsub.component.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: zdsub
 * @description: 用于处理String判断的工具
 * @author: lyy
 * @generate: 2019-09-11 10:55
 **/
public class ExceptionUtil {
    /*@description：为空则抛出异常
     *@Date：2019/9/11 10:58
     *@Param:context
     *@Param:msg
     *@Return
     *@Author lyy
     */
    public static void isBlank(String context,String msg){
        if(StringUtils.isBlank(context))
            throw new GlobalException(msg);
    }
    public static void isBlank(String context,int errorCode,String msg){
        if(StringUtils.isBlank(context))
            throw new GlobalException(errorCode,msg);
    }
    public static boolean isBlank(String context){
        if(StringUtils.isBlank(context))
            return true;
        return false;
    }
    /*@description：对象为NULL则抛出异常
     *@Date：2019/9/11 11:07
     *@Param
     *@Return
     *@Author lyy
     */
    public static void isNull(Object o,String msg){
        if(null == o)
            throw new GlobalException(msg);
    }
    public static void isNull(String msg,Object...o){
        for (int i = 0; i < o.length; i++) {
            if(o[i] == null)
                throw new GlobalException(msg);
        }
    }
    public static void isNull(Object o,int errorCode,String msg){
        if(null == o)
            throw new GlobalException(errorCode,msg);
    }
    public static boolean isNull(Object o){
        if(null == o)
            return true;
        return false;
    }
    /*@description：
     *@Date：2019/9/11 16:38
     *@Param：
     *@Return：
     *@Author： lyy
     */
    public static void isNotSame(String tar,String sour,String msg){
        if(!tar.equals(sour))
            throw new GlobalException(msg);
    }
}
