package com.sky.controller.user;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: SetmealController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/28 18:11
 * @version: 1.0
 */

@Api(tags = "c端-套餐管理")
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private com.sky.service.SetmealService setmealService;

    //根据分类id查询套餐
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId")
    @ApiOperation("根据分类id查询套餐")
    @GetMapping("/list")
    public Result<List<Setmeal>> listBySetmeal(Long categoryId) {
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(1);
        log.info("根据分类id查询套餐");
        List<Setmeal> list = setmealService.list(setmeal);

        return Result.success(list);
    }
    //根据套餐id查询包含的菜品
    @ApiOperation("根据套餐id查询包含的菜品")
    @GetMapping("/dish/{id}")
    public Result<List<SetmealVO>> listBySetmealId(@PathVariable Long setmealId) {
        log.info("根据套餐id查询包含的菜品");
        List<SetmealVO> list = setmealService.getDishById(setmealId);
        return Result.success(list);
    }



}
