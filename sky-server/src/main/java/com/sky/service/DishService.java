package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.service
 * @className: DishService
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 17:56
 * @version: 1.0
 */


public interface DishService {
    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteAll(List<Long> ids);

    DishVO getById(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    void startOrStop(Integer status, Long ids);

    List<Dish> list(Long categoryId);
}
