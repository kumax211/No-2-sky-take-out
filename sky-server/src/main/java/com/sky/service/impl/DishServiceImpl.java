package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.AutoFill;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.service.impl
 * @className: DishServiceImpl
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 17:57
 * @version: 1.0
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {


    @Autowired
    private com.sky.mapper.DishMapper dishMapper;

    @Autowired
    private com.sky.mapper.DishFlavorMapper dishFlavorMapper;

    @Autowired
    private com.sky.mapper.SetmealDishMapper setmealDishMapper;
    @Override
    // 事务
    @Transactional()
    public void saveWithFlavor(DishDTO dishDTO) {
        log.info("新增菜品，菜品信息：{}", dishDTO.toString());
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //向菜品表插入一条数据
        dishMapper.saveWithFlavor(dish);
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();

        if (flavors != null && flavors.size() > 0){
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishId);
            }
            //向口味表插入n条数据
            dishFlavorMapper. saveWithFlavor(flavors);
        }
    }
    // 分页查询
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        return pageResult;
    }



    // 批量删除
    @Transactional
    public void deleteAll(List<Long> ids) {
        //起售中的商品不可删除
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == 1){
                throw new DeletionNotAllowedException("起售中的商品不可删除");
            }
        }
        //套餐中的商品不可删除
        List<Long> setmealDishIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealDishIds != null && setmealDishIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }

//        for (Long id : ids) {
//            List<Long> setmealIdByDishId = setmealDishMapper. List<Long> setmealDishIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
//        if (setmealDishIds != null && setmealDishIds.size() > 0) {
//            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
//        }(id);
//            int count = setmealIdByDishId.size();
//            if (count > 0){
//                throw new DeletionNotAllowedException("套餐中的商品不可删除");
//            }
//        }

//        //删除后口味也要删除
//        for (Long id : ids){
//            dishMapper.deleteAll(id);
//            dishFlavorMapper.deleteAll(id);
//        }
        //批量删除菜品

            dishMapper.deleteByIds(ids);
            dishFlavorMapper.deleteByDishId(ids);




    }
    // 根据id查询菜品
    @Override
    public DishVO getById(Long id) {
        DishVO dishVO = new DishVO();
        Dish  dish = dishMapper.getById(id);
        List<DishFlavor> dishFlavors= dishFlavorMapper.getById(id);
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }
    // 修改菜品
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);
        //删除后口味也要删除
        dishFlavorMapper.deleteAll(dishDTO.getId());
        //获取口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0){
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishDTO.getId());
            }
            //向口味表插入n条数据
            dishFlavorMapper. saveWithFlavor(flavors);
        }

    }
    // 起售停售
    @Override
    public void startOrStop(Integer status, Long id) {
//          Dish dish = Dish.builder().status(status)
//                    .id(id).build();
        Dish dish = new Dish();
        dish.setStatus(status);
        dish.setId(id);
        dishMapper.update(dish);

    }

    @Override
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder().categoryId(categoryId).status(StatusConstant.ENABLE).build();
        return dishMapper.list(dish);
    }


}
