package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @projectName: sky-take-out
 * @package: com.sky.annotation
 * @className: AutoFill
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 18:11
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//自定义注解, 用于标识需要自动填充的公共字段
public @interface AutoFill {
    OperationType value();
}
