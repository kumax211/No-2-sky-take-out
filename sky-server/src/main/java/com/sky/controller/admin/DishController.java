package com.sky.controller.admin;

import com.github.pagehelper.Page;
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
import java.util.Set;

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
@Api(tags = "菜品管理")
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private com.sky.service.DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    //新增菜品
    @PostMapping()
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        redisTemplate.delete("dish_" + dishDTO.getCategoryId());
        return Result.success();
    }
    //分页查询菜品
    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> getPage( DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询菜品");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);

        return Result.success(pageResult);
    }

    @DeleteMapping()
    @ApiOperation("批量删除菜品")
    public Result deleteAll(@RequestParam List<Long> ids){
        log.info("批量删除菜品：{}",ids);
        dishService.deleteAll(ids);
        redisAllDelete();
        return Result.success();
    }

    //根据id查询菜品
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品：{}",id);
        return Result.success(dishService.getById(id));
    }

    //修改菜品
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品：{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        redisAllDelete();
        return Result.success();
    }
    //批量起售停售
    @PostMapping("/status/{status}")
    @ApiOperation("批量起售停售")
    public Result startOrStop(@PathVariable Integer status,Long id){
       log.info("批量起售停售：{}",id);
       dishService.startOrStop(status,id);
        redisAllDelete();
        return Result.success();
    }



    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        return Result.success(dishService.list(categoryId));
    }
    private void redisAllDelete() {
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
    }
}
