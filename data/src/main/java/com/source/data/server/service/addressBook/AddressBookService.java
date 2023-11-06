package com.source.data.server.service.addressBook;

import com.pojo.address.AddressBook;
import com.pojo.address.WEBaddressBook.Address_add;

import java.util.List;

public interface AddressBookService {
    /**
     * 新增地址信息
     * @param addressAdd
     */
    void insert(Address_add addressAdd);

    /**'
     * 查询当前登入用户的地址簿
     * @return
     */
    List<AddressBook> selectList();

    /**
     * 查询默认地址
     * @return
     */
    AddressBook selectDefault();

    /**
     * 设置指定id地址为默认地址
     * @param id
     */
    void updateDefault(Long id);

    /**
     * 根据id修改当前用户的地址信息
     * @param addressAdd
     */
    void updateAddress(Address_add addressAdd);

    /**
     * 查询指定id的地址信息
     * @param id
     * @return
     */
    AddressBook selectID(Long id);

    /**
     * 删除当前登入用户下的指定id的地址
     * @param id
     */
    void deleteAddress(Long id);
}
