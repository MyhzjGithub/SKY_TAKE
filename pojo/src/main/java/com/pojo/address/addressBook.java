package com.pojo.address;

import lombok.Data;

/**
 * 地址类
 */
@Data
public class addressBook {
    private Long id;
    private Long userId;
    private String consignee;
    private String sex;
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
