package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//启动类
@SpringBootApplication
//开启缓存
@EnableCaching
//开启注解式事务管理
@EnableTransactionManagement //开启注解方式的事务管理
//日志
@Slf4j
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started,启动成功,开搞");
    }
}
