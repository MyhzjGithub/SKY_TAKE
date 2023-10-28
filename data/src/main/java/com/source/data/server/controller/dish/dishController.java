package com.source.data.server.controller.dish;

import com.pojo.Page.Pages;
import com.pojo.Query.dishQuery;
import com.pojo.RESULT.Result;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;
import com.source.data.server.service.dish.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜品控制层
 */
@RestController
@Slf4j
@RequestMapping("/admin/dish")
public class dishController {
    @Autowired
    private DishService service;

    @GetMapping("/page")
    public Result page(dishQuery page){
        log.info("分页查询菜品 : {}",page);
        Pages<Dish_page> dishPagePages = service.Pages(page);
        return Result.success(dishPagePages);
    }

    @GetMapping("/{id}")
    public Result SelectDishId(@PathVariable("id") Long id){
        log.info("查询id为 {} 的数据",id);
        Dish_public dishPublic = service.getDishId(id);
        return Result.success(dishPublic);
    }

    @PutMapping
    public Result updateDish(@RequestBody Dish_public dishPublic){
        log.info("修改数据 : 修改的数据为{}",dishPublic);
        service.updateDish(dishPublic);
        return Result.success();
    }
}
