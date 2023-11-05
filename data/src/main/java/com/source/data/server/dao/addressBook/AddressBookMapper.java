package com.source.data.server.dao.addressBook;

import com.pojo.address.AddressBook;
import com.pojo.address.WEBaddressBook.Address_add;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    void insert(Address_add addressAdd);

    @Select("select id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default from address_book where user_id = #{userId}")
    List<AddressBook> selectUserIDS(Long userId);

    @Select("select id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default from address_book where user_id = #{userId} and is_default = #{aDefault}")
    AddressBook selectUserID_default(Long userId, Integer aDefault);

    @Update("update address_book set is_default = #{notDefault} where user_id = #{userId}")
    void updateNotDefault(Integer notDefault, Long userId);

    @Update("update address_book set is_default = #{aDefault} where id = #{id} and user_id = #{userId}")
    void updateDefault(Integer aDefault, Long id, Long userId);

    void updateAddress(Address_add addressAdd);

    @Select("select id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default from address_book where id = #{id} and user_id = #{userId}")
    AddressBook selectUserID(Long id, Long userId);

    @Delete("delete from address_book where id = #{id} and user_id = #{userId}")
    void deleteAddress(Long id, Long userId);
}
