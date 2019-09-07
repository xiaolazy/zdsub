package com.zdsub.component.log;

import com.zdsub.component.exception.GlobalException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @program: zdsub
 * @description: 日志验证
 * @author: lyy
 * @generate: 2019-09-07 11:24
 **/
@Component
@Aspect
public class ValidLog {
    @Pointcut("execution(* com.zdsub.controller.*.*(..))||execution(* com.zdsub.controller.*.*.*(..))||execution(* com.zdsub.controller.*.*.*.*(..))")
    public void pointCut(){ }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        BindingResult bindingResult = null;
        for (Object object : joinPoint.getArgs()) {
            if (object instanceof BindingResult) {
                bindingResult = (BindingResult) object;
            }
        }
        if (bindingResult == null) {
            throw new RuntimeException("使用了Valid注解，方法参数必须定义org.springframework.validation.BindingResult");
        } else if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            System.out.println("DefaultMessage:::::"+fieldError.getDefaultMessage());
            throw new GlobalException(fieldError.getDefaultMessage());
        }
        return joinPoint.proceed();
    }
}
