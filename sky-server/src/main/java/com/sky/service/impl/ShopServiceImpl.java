package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @projectName: sky-take-out
 * @package: com.sky.service.impl
 * @className: ShopServiceImpl
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/31 19:08
 * @version: 1.0
 */

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void setStatus(Integer status) {
        redisTemplate.opsForValue().set("SHOP_STATUS",status);
    }

    @Override
    public Integer getStatus() {
        return (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
    }
}
