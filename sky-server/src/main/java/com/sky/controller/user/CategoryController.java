package com.sky.controller.user;

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

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
@Api(tags = "c端-分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //查询分类
    @ApiOperation("根据类型查询分类")
    //查询分类
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {
        log.info("根据类型查询分类");
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

}
