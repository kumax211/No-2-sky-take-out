package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.mapper
 * @className: ShopMapper
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/31 19:07
 * @version: 1.0
 */
@Mapper

public interface ShoppingCartMapper {
    List<ShoppingCart> list(ShoppingCart shoppingCart);
    //更新购物车
@Update("update shopping_cart set number = #{number} where id =#{id}")
    void updateNumberById(ShoppingCart cart);
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount,number, create_time) " +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{amount},#{number},#{createTime})")
    void insert(ShoppingCart shoppingCart);
    @Delete("delete from shopping_cart where id in (#{id})")
    void deleteByIds(Long id);
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);
}
