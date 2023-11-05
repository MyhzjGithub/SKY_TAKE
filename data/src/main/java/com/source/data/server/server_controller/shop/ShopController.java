package com.source.data.server.server_controller.shop;

import com.pojo.RESULT.Result;
import com.utils.StatusUtils.DefaultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * web端 : 店铺营业模块
 */
@RestController("server_ShopController")
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {

    public Integer shopStatus = DefaultStatus.ONE;    // 店铺默认为营业

    @GetMapping("/status")
    public Result getStatus(){
        return Result.success(shopStatus);
    }

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable("status") Integer status){
        shopStatus = status;
        return Result.success();
    }
}
