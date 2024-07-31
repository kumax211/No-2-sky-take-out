package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void saveWithFlavor(List<DishFlavor> flavors);
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteAll(Long dishId );

    void deleteByDishId(List<Long> dishIds);

    // 根据菜品id查询菜品口味
    @Select("select * from dish_flavor where dish_id =#{dishId}")
    List<DishFlavor> getById(Long dishId);


}
