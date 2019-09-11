package com.zdsub.utils;


import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @program: zdsub
 * @description: Base64加解密
 * @author: lyy
 * @generate: 2019-09-09 09:29
 **/
public class Base64Util {
    private final static Base64.Encoder encoder = Base64.getEncoder();
    private final static Base64.Decoder decoder = Base64.getDecoder();

    /*@description：编码
     *@Date：2019/9/9 9:47
     *@Param
     *@Return
     *@Author lyy
     */
    public  static String Encoder(String law) {
        if(StringUtils.isBlank(law))
            return "";
        try {
            return encoder.encodeToString(law.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /*@description：解码
     *@Date：2019/9/9 9:51
     *@Param
     *@Return
     *@Author lyy
     */
    public static String Decoder(String law){
        if(StringUtils.isBlank(law))
            return "";
        try {
            return new String(decoder.decode(law),"UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
