package com.pojo.order.WEBorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order_updateCancel {

    private Long id;
    private String cancelReason;
}
