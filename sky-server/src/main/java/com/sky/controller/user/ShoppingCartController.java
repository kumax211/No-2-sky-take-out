package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.user
 * @className: ShopController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/31 19:32
 * @version: 1.0
 */

@RequestMapping("/user/shoppingCart")
@RestController
@Slf4j
@Api(tags = "c端购物车相关借口")
public class ShoppingCartController {

    @Autowired
    private com.sky.service.ShoppingCartService shoppingCartService;


    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车");
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> list() {
        log.info("查看购物车");
        List<ShoppingCart> list= shoppingCartService.list();
        return Result.success(list);
    }
    @PostMapping("/sub")
    @ApiOperation("删除购物车")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("删除购物车");
        shoppingCartService.sub(shoppingCartDTO);
        return Result.success();
    }
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result clean() {
        log.info("清空购物车");
        shoppingCartService.clean();
        return Result.success();
    }
}
