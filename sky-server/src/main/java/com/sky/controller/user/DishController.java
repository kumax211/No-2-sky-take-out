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

    //根据分类id查询菜品
    @ApiOperation("根据分类id查询菜品")
    @GetMapping("/list")
    public Result<List<DishVO>> listByDish(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(1);
        List<DishVO> list = dishService.listWithFlavor(dish);
        return Result.success(list);
    }


}
