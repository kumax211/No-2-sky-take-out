package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @projectName: sky-take-out
 * @package: com.sky.mapper
 * @className: SetmealMapper
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/22 20:41
 * @version: 1.0
 */

@Mapper
public interface SetmealMapper {
    @Select("select count(id) from setmeal where category_id =#{categoryId}")
    Integer countByCategoryId(Long categoryId);
}
