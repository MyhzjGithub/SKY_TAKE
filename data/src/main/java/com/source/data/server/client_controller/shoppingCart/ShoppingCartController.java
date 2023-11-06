package com.source.data.server.client_controller.shoppingCart;

import com.pojo.RESULT.Result;
import com.pojo.shopping.Client.ShoppingCartadd;
import com.pojo.shopping.ShoppingCart;
import com.source.data.server.service.shoppingCart.ShoppingCartService;
import com.utils.ThreadUtils.BeanThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * app端 : 购物车模块
 */
@RestController("client-ShoppingCartController")
@Slf4j
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    @GetMapping("/list")
    public Result list(){
        log.info("查询当前用户的购物车信息");
        List<ShoppingCart> list = service.selectUserIDShoppingCart();
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartadd shoppingCartadd){
        log.info("添加菜品信息进入购物车");
        service.insert(shoppingCartadd);
        return Result.success();
    }
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartadd shoppingCartadd){
        log.info("减少一个购物车的菜品");
        service.removeOne(shoppingCartadd);
        return Result.success();
    }
    @DeleteMapping("/clean")
    public Result removeUser(){
        log.info("清空当前用户的购物车信息");
        service.Delete();
        return Result.success();
    }
}
