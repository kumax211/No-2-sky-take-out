package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: sky-take-out
 * @package: com.sky.config
 * @className: OssConfigration
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 20:31
 * @version: 1.0
 */

//配置类,用于创建AliossUtilUtil对象
    @Configuration
    @Slf4j
public class OssConfiguration {

        @Bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建阿里云文件上传工具类对象");
       return new AliOssUtil(aliOssProperties.getEndpoint()
                ,aliOssProperties.getAccessKeyId()
                 ,aliOssProperties.getAccessKeySecret()
                ,aliOssProperties.getBucketName());
    }
}
