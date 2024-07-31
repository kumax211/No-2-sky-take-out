package com.sky.controller.admin;

import com.sky.properties.AliOssProperties;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @projectName: sky-take-out
 * @package: com.sky.controller.admin
 * @className: commonColler
 * @author: Eric
 * @description: TODO
 * @date: 2024/7/24 20:11
 * @version: 1.0
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    //文件上传
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);
        //文件名uuid重命名
        try {
            String originalFilename = file.getOriginalFilename();
            //获取文件后缀
            originalFilename.substring(0, originalFilename.lastIndexOf(".") + 1);


            //拼接 文件名 + 后缀   UUID.randomUUID().toString()
            String fileName = UUID.randomUUID().toString() + originalFilename;


            String filePath = null;

            filePath = aliOssUtil.upload(file.getBytes(), fileName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return Result.error("文件上传失败");
    }
}
