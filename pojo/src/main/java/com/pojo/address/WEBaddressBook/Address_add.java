package com.pojo.address.WEBaddressBook;

import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.LinkOption;

@Data
@ToString
public class Address_add {
    private String cityCode;
    private String cityName;
    private String consignee;   // 收货人
    private String detail;  // 详细地址
    private String districtCode;
    private String districtName;
    private Long id;    // 地址id
    private Integer isDefault;  // 是否为默认地址
    private String label;   // 标签
    private String phone;   // 手机号
    private String provinceCode;
    private String provinceName;
    private String sex; // 收货人性别
    private Long userId;    // 用户id
}
