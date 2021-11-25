package com.cloud.morsechat.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Pointcut(value = "@annotation(permission)")
    public void Permiss(Permission permission){
    }

    // 环绕，在切入点前后
    @Around("Permiss(permission)")
    public Object PermissionCheck(ProceedingJoinPoint pjp, Permission permission) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();


        Method method = ((MethodSignature) pjp.getSignature()).getMethod(); //获取签名里的方法
        Permission myPermission = method.getAnnotation(Permission.class); //获取该方法里的注解

//        return pjp.proceed();

        return null;

    }

    @After("Permiss(permission)")
    public void PermissionPass(Permission permission){
        log.info("=====<权限验证通过>=====");
    }

}
