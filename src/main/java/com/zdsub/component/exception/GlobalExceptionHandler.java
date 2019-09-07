package com.zdsub.component.exception;

import com.zdsub.common.ResultBean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zdsub.common.constant.Common.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: zdsub
 * @description: 全局异常处理
 * @author: lyy
 * @generate: 2019-09-06 19:35
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseBean globalException(HttpServletRequest req,GlobalException g) throws Exception{
        g.printStackTrace();
        return ResponseBean.responseException(g.getErrorCode(),g.getMessage());
    }
}
