package com.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询包装类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PAGE<T> {
    private Integer total;
    private List<T> records;
}
