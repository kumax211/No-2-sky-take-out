package com.sky.controller.user;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: DishController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 17:55
 * @version: 1.0
 */
@RestController("userDishController")
@Api(tags = "c端-菜品管理")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private com.sky.service.DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    //根据分类id查询菜品
    @ApiOperation("根据分类id查询菜品")
    @GetMapping("/list")
    public Result<List<DishVO>> listByDish(Long categoryId) {
        //redis中的key
        String key = "dish_" + categoryId;

        //查询redis缓存
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list != null && list.size() > 0) {
            //如果存在,直接返回,无需查数据库
            return Result.success(list);
        }
        //如果不能存在,查数据库
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(1);
        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(key, list);

        return Result.success(list);
    }


}
