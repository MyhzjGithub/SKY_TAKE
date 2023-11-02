package com.source.data.server.client_controller.shop;

import com.pojo.RESULT.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("client_shopController")
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {
    @Autowired
    private com.source.data.server.server_controller.shop.ShopController shopController;
    @GetMapping("/status")
    public Result getShopStatus(){
        log.info("获取小程序店铺状态");
        Integer status = shopController.shopStatus; // 获取服务端店铺状态
        return Result.success(status);
    }
}
