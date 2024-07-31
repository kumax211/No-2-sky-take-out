package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: ShopController
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/31 19:03
 * @version: 1.0
 */

@RestController("adminShopController")
@Slf4j
@Api(tags = "店铺相关接口")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @ApiOperation("设置店铺营业状态")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态:{}",status == 1 ? "营业" : "打烊");
        shopService.setStatus(status);
        return Result.success();
    }
    @ApiOperation("查询店铺营业状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = shopService.getStatus();
        log.info("查询店铺营业状态",status == 1 ? "营业" : "打烊");
        return Result.success(status);
    }
}
