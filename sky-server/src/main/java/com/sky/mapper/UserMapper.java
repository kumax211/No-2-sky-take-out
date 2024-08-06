package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: sky-take-out
 * @package: com.sky.mapper
 * @className: UserMapper
 * @author: Eric
 * @description: TODO
 * @date: 2024/8/5 19:30
 * @version: 1.0
 */

@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */

    @Select("select * from user where openid= #{openId} ")
    User getByOpenid(String openid);
    @AutoFill(value = OperationType.INSERT)

    void insert(User user);
}

