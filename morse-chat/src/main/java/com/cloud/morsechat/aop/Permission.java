package com.cloud.morsechat.aop;

import java.lang.annotation.*;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.9.9
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
//    String value() default "";
}
