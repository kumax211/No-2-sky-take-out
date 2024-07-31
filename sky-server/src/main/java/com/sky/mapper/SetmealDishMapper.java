package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    //菜品id 查询 套餐id
//    @Select("select * from setmeal_dish where dish_id = #{dishId}")
//    List<Long> getSetmealIdByDishId(Long dishId);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteByDishId(Long setmealId);
    @AutoFill(value = OperationType.INSERT)
    void saveSetmealDish(List<SetmealDish> setmealDishes);



    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
    @ Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getByDishId(Long setmealId);
}
