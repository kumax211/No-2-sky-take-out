package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.AddressBook;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

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

@Mapper
public interface AddressMapper {

        @Select("select * from address_book where user_id = #{userId} ")
        List<AddressBook> getAddress(Long userId);
        @Insert("insert into address_book" +
                "        (user_id, consignee, phone, sex, province_code, province_name, city_code, city_name, district_code," +
                "         district_name, detail, label, is_default)" +
                "        values (#{userId}, #{consignee}, #{phone}, #{sex}, #{provinceCode}, #{provinceName}, #{cityCode}, #{cityName}," +
                "                #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
        void insert(AddressBook addressBook);
        @Select("select * from address_book where id = #{id}")
        AddressBook getById(Long id);

        void update(AddressBook addressBook);
        @Delete("delete from address_book where id =#{id}")
        void delete(Long id);
        @Update("update address_book set is_default = 0 where user_id = #{userId}")
        void updateIsDefaultByUserId(Long userId);
}

