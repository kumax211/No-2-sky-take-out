package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.AddressMapper;
import com.sky.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: sky-take-out
 * @package: com.sky.service.impl
 * @className: UserServiceImpl
 * @author: Eric
 * @description: TODO
 * @date: 2024/8/5 17:30
 * @version: 1.0
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        List<AddressBook> address = addressMapper.getAddress(userId);
        return address;
    }

    @Override
    public void add(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressMapper.insert(addressBook);

    }

    @Override
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressMapper.getById(id);
        return addressBook;
    }

    @Override
    public void update(AddressBook addressBook) {

        addressBook.setIsDefault(0);
        Long userId = BaseContext.getCurrentId();
        addressBook.setUserId(userId);
        addressMapper.updateIsDefaultByUserId(userId);

        addressBook.setIsDefault(1);
        addressMapper.update(addressBook);
    }

    @Override
    public void delete(Long id) {
        addressMapper.delete(id);
    }

    @Override
    public AddressBook getAddress() {
        List<AddressBook> address = addressMapper.getAddress(BaseContext.getCurrentId());
        for (AddressBook addressBook : address) {
            if (addressBook.getIsDefault()==1) {
                return addressBook;
            }
        }
        //抛出异常
        throw new DeletionNotAllowedException(MessageConstant.ADDRESS_BOOK_IS_NULL);
    }
}





