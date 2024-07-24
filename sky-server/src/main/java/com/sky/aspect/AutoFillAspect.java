package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @projectName: sky-take-out
 * @package: com.sky.aspect
 * @className: AutoFillAspect
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 18:16
 * @version: 1.0
 */

//自定义切面, 用于实现公共字段自动填充
    @Aspect
    @Component
    @Slf4j
public class AutoFillAspect {
        //切入点,拦截, 拦截com.sky.mapper包下的所有方法
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut(){


    }
    //前置通知,公共字段赋值
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始公共字段自动填充");
        //获取数据库操作类型

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = annotation.value();

        //获取方法参数-实体对象
        Object[] args = joinPoint.getArgs();
        if (args.length == 0|| args[0] == null){
            return;
        }
        Object entity = args[0];
        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //根据当前不同的操作类型,准备赋值的数据
        if (operationType == OperationType.INSERT){
           try {
               //获取属性值
               entity.getClass().getDeclaredField("createTime").set(entity,now);
               entity.getClass().getDeclaredField("createUser").set(entity,currentId);
               entity.getClass().getDeclaredField("updateTime").set(entity,now);
               entity.getClass().getDeclaredField("updateUser").set(entity,currentId);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }else if (operationType == OperationType.UPDATE){
           try {
               //获取属性值
               entity.getClass().getDeclaredField("updateTime").set(entity,now);
               entity.getClass().getDeclaredField("updateUser").set(entity,currentId);
           } catch (Exception e){
               e.printStackTrace();
           }
       }


    }



}
