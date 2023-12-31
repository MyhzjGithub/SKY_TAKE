package com.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询包装类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pages<T> implements Serializable {
    private Integer total;  // 总数
    private List<T> records;    // 数据
}
