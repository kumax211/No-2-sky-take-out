package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: CategoryController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/22 17:51
 * @version: 1.0
 */

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //新增分类
    @PostMapping
    @ApiOperation("新增分类")
    public Result save(CategoryDTO categoryDTO) {
        log.info("新增分类:{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    //分类分页查询
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQuery) {
        log.info("分类分页查询:{}", categoryPageQuery);
        PageResult pageResult = categoryService.page(categoryPageQuery);

        return Result.success(pageResult);
    }

    //根据id删除分类
    @DeleteMapping()
    @ApiOperation("根据id删除分类")
    public Result delete(Long id) {
        log.info("根据id删除分类:{}", id);
        categoryService.delete(id);
        return Result.success();
    }

    //修改分类
    @PutMapping()
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    //启用禁用分类
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result status(@PathVariable Integer status, Long id) {
        categoryService.status(status, id);
        return Result.success();
    }

    //根据类型查询分类
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type) {
       List<Category> list =  categoryService.list(type);
        return Result.success(list);
    }
}
