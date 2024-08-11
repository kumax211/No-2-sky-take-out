package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.service.AddressService;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.user
 * @className: UserController
 * @author: Eric
 * @description: TODO
 * @date: 2024/8/2 18:25
 * @version: 1.0
 */

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "C端用户地址相关接口")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressService addressService;


    @GetMapping("/list")
    @ApiOperation("查看地址")
    public Result<List<AddressBook>> list() {
        return Result.success(addressService.list());
    }

    @PostMapping
    @ApiOperation("新增地址")
    public Result add(@RequestBody AddressBook addressBook) {
        addressService.add(addressBook);
        return Result.success();
    }
    //根据id 查询
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> getById(@PathVariable Long id) {
        return Result.success(addressService.getById(id));
    }
    //修改地址
    @PutMapping
    @ApiOperation("修改地址")
    public Result update(@RequestBody AddressBook addressBook) {
        addressService.update(addressBook);
        return Result.success();
    }
    //设置默认地址
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(@RequestBody AddressBook addressBook) {

        addressService.update(addressBook);
        return Result.success();
    }
    //删除地址
    @DeleteMapping
    @ApiOperation("删除地址")
    public Result delete(@RequestParam Long id) {
        addressService.delete(id);
        return Result.success();
    }
    //查询默认地址
    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    public Result<AddressBook> getDefault() {


        return Result.success( addressService.getAddress());
    }



}


    








