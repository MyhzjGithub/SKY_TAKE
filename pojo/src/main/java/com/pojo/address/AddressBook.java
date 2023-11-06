package com.pojo.address;

import lombok.Data;

/**
 * 地址类
 */
@Data
public class AddressBook {
    private Long id;    // 主键id
    private Long userId;    // 用户id
    private String consignee;   // 收货人
    private String sex;     // 性别
    private String phone;
    private String provinceCode;    // 省级区划分编号
    private String provinceName;    // 省级名称
    private String cityCode;        // 市级区划分编号
    private String cityName;        // 市级名称
    private String districtCode;    // 区级区划分编号
    private String districtName;    // 区级名称
    private String detail;          // 详细地址
    private String label;           // 标签
    private Integer isDefault;      // 默认 0 否  1 是
}
