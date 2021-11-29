package com.cloud.morsechat.aop;

import com.cloud.morsechat.domain.GlobalKey;
import com.cloud.morsechat.util.JWTUtils;
import com.cloud.morsechat.vo.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class AuthAspect extends GlobalKey {

    @Pointcut(value = "@annotation(permission)")
    public void Permiss(Permission permission){
    }

    // 环绕，在切入点前后
    @Around("Permiss(permission)")
    public Object PermissionCheck(ProceedingJoinPoint pjp, Permission permission) throws Throwable {

//        Method method = ((MethodSignature) pjp.getSignature()).getMethod(); //获取签名里的方法
//        Permission myPermission = method.getAnnotation(Permission.class); //获取该方法里的注解

//        String token = new HttpHeaders().getFirst("token"); //根本不起作用

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                                    .getRequestAttributes())).getRequest();

        String token = request.getHeader(TOKEN);


        if(StringUtils.isBlank(token) || StringUtils.isEmpty(token)){
            return new RestResponse<Map<String, String>>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),null);
        }

        try {
            JWTUtils.verify(token);
        } catch (Exception e){
            log.info("请求资源的权限不够");
            return new RestResponse<Map<String, String>>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),null);
        }

        return pjp.proceed();

    }

    @After("Permiss(permission)")
    public void PermissionPass(Permission permission){
        log.info("权限验证通过");
    }

}
