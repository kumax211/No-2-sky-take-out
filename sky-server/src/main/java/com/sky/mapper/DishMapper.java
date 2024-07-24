package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @projectName: sky-take-out
 * @package: com.sky.mapper
 * @className: DishMapper
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/22 20:41
 * @version: 1.0
 */

@Mapper
public interface DishMapper {

    @Select("select count(id) from dish where category_id = #{categoryId} ")
    Integer countByCategoryId(Long categoryId);
}
