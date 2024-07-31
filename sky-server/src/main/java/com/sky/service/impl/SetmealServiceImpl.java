package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.service.impl
 * @className: SetmealServiceImpl
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/28 18:13
 * @version: 1.0
 */

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {


    @Autowired
    private com.sky.mapper.SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private com.sky.mapper.DishMapper dishMapper;

    @Autowired
    private com.sky.mapper.CategoryMapper categoryMapper;


    // 新增套餐
    @Override
    public void saveSetmeal(SetmealDTO setmealDTO) {
        // 将dto转换为entity
        Setmeal setmeal = new Setmeal();
        // 将dto中的属性拷贝到entity中
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // 保存套餐
        setmealMapper.saveSetmeal(setmeal);

        Long setmealId = setmeal.getId();
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
        }
        // 保存套餐和菜品的关联关系
        setmealDishMapper.saveSetmealDish(setmealDishes);
    }

    // 套餐分页查询
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());

    }

    // 套餐起售停售
    @Override
    public void startOrStop(Integer status, Long id) {

        //起售套餐时，判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
        if (status == StatusConstant.ENABLE) {
            //select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = ?
            List<Dish> dishList = dishMapper.getBySetmealId(id);
            if (dishList != null && dishList.size() > 0) {
                dishList.forEach(dish -> {
                    if (StatusConstant.DISABLE == dish.getStatus()) {
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        setmeal.setId(id);

        setmealMapper.update(setmeal);

    }

    @Transactional
    // 批量删除套餐
    public void deleteAll(List<Long> ids) {

        //起售中的商品不可删除
        for (Long id : ids) {
            Setmeal setmeal = setmealMapper.getById(id);
            if (setmeal.getStatus() == 1) {
                throw new DeletionNotAllowedException("起售中的商品不可删除");
            }
        }

        for (Long id : ids) {
            setmealMapper.deleteAll(id);
            setmealDishMapper.deleteByDishId(id);

        }


    }

    @Override
    public SetmealVO getByDishId(Long id) {
        SetmealVO setmealVO = new SetmealVO();
        Setmeal setmeal = setmealMapper.getByDishId(id);
        List<SetmealDish> setmealDishes = setmealDishMapper.getByDishId(id);
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }


    // 修改套餐
    @Override
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.update(setmeal);

        Long setmealId = setmeal.getId();
        setmealDishMapper.deleteByDishId(setmealId);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0) {
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDish.setSetmealId(setmealId);
            }
            setmealDishMapper.saveSetmealDish(setmealDishes);
        }
    }
}
