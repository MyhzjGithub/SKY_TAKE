package com.pojo.Query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderClientQuery {
    private Integer page;
    private Integer pageSize;
    private Integer status;
    private Long userId;
}
