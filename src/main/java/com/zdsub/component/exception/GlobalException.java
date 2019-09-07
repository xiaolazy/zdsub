package com.zdsub.component.exception;
import lombok.Getter;

import static com.zdsub.common.constant.Common.*;
/**
 * @program: zdsub
 * @description: 全局异常
 * @author: lyy
 * @generate: 2019-09-06 19:27
 **/
@Getter
public class GlobalException extends RuntimeException {
    private int errorCode = FAIL;
    public GlobalException(String msg){
        super(msg);
    }
    public GlobalException(int errorCode,String msg){
        super(msg);
        this.errorCode = errorCode;
    }
}
