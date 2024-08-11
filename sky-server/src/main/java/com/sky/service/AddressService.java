package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.entity.AddressBook;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.mapper
 * @className: UserMapper
 * @author: Eric
 * @description: TODO
 * @date: 2024/8/5 19:30
 * @version: 1.0
 */


public interface AddressService {

    List<AddressBook> list();

    void add(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void delete(Long id);

    AddressBook getAddress();
}

