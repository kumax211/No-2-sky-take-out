package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    void saveSetmeal(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void startOrStop(Integer status, Long id);





    void update(SetmealDTO setmealDTO);

    void deleteAll(List<Long> ids);



    SetmealVO getByDishId(Long id);

    List<Setmeal> list(Setmeal setmeal);

    List<SetmealVO> getDishById(Long setmealId);
}
