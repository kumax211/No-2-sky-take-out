package com.sky.controller.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: DishController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 17:55
 * @version: 1.0
 */
@RestController
@Api
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private com.sky.service.DishService dishService;
}
