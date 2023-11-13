package com.source.data.server.client_controller.dish;

import com.pojo.RESULT.Result;
import com.pojo.dish.WEBdish.Dish_public;
import com.source.data.server.service.dish.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * app端 : 菜品模块
 */
@RestController("client-DishController")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishService service;

    @GetMapping("/list")
    @Cacheable(value = "client-Dish",key = "#categoryId")
    public Result getDishList(Long categoryId){
        log.info("查询分类id为 : {} 的菜品信息",categoryId);
        List<Dish_public> dishList = service.selectCategoryId(categoryId);
        return Result.success(dishList);
    }
}
