package com.source.data.server.service.addressBook.impl;

import com.pojo.address.AddressBook;
import com.pojo.address.WEBaddressBook.Address_add;
import com.source.data.server.dao.addressBook.AddressBookMapper;
import com.source.data.server.service.addressBook.AddressBookService;
import com.utils.AddressBookUtils.defaultAddressBook;
import com.utils.StatusUtils.DefaultStatus;
import com.utils.ThreadUtils.BeanThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookClass implements AddressBookService {
    @Autowired
    private AddressBookMapper mapper;

    @Override
    public void insert(Address_add addressAdd) {
        // 获取当前用户id
        Long userId = BeanThread.getCurrentId();
        // 设置该地址不为默认
        addressAdd.setIsDefault(DefaultStatus.ZERO);
        // 添加用户id
        addressAdd.setUserId(userId);
        // 执行insert语法添加地址
        mapper.insert(addressAdd);
    }

    @Override
    public List<AddressBook> selectList() {
        return mapper.selectUserIDS(BeanThread.getCurrentId());
    }

    @Override
    public AddressBook selectDefault() {
        return mapper.selectUserID_default(BeanThread.getCurrentId(), defaultAddressBook.DEFAULT);
    }

    @Override
    public void updateDefault(Long id) {
        Long userId = BeanThread.getCurrentId();    // 当前登入用户的id
        // 先把当前用户的所有数据设置为 非默认地址
        mapper.updateNotDefault(defaultAddressBook.NOT_DEFAULT,userId);
        // 设置直接id的地址为默认地址
        mapper.updateDefault(defaultAddressBook.DEFAULT,id,userId);
    }

    @Override
    public void updateAddress(Address_add addressAdd) {
        addressAdd.setUserId(BeanThread.getCurrentId());
        mapper.updateAddress(addressAdd);
    }

    @Override
    public AddressBook selectID(Long id) {
        return mapper.selectUserID(id,BeanThread.getCurrentId());
    }

    @Override
    public void deleteAddress(Long id) {
        mapper.deleteAddress(id,BeanThread.getCurrentId());
    }
}
