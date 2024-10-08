package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

@Api(tags = "套餐管理")
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private com.sky.service.SetmealService setmealService;

    //新增套餐(1)

    @CacheEvict(value = "setmealCache", key="#setmealDTO.categoryId")
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}", setmealDTO);
        setmealService.saveSetmeal(setmealDTO);
        return Result.success();
    }


    //分页查间(1)
    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐：{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }
    //套餐起售、停售(1)
    @CacheEvict(value = "setmealCache", allEntries = true)
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售、停售")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("套餐起售、停售：{}", status);
        setmealService.startOrStop(status, id);
        return Result.success();
    }
    //比量删除套餐(1)
    @CacheEvict(value = "setmealCache", allEntries = true)
    @DeleteMapping()
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("批量删除套餐：{}", ids);
        setmealService.deleteAll(ids);
        return Result.success();
    }

    //根据id查询套餐(1)
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐：{}", id);
        SetmealVO setmealVO = setmealService.getByDishId(id);
        return Result.success(setmealVO);
    }
    //修改套餐(1)
    @CacheEvict(value = "setmealCache", allEntries = true)
    @PutMapping
    @ApiOperation("修改套餐")

    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐：{}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
