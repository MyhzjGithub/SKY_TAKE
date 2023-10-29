package com.source.data.server.controller.dish;

import com.pojo.Page.Pages;
import com.pojo.Query.dishQuery;
import com.pojo.RESULT.Result;
import com.pojo.dish.Dish;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;
import com.source.data.server.service.dish.DishService;
import com.utils.StatusUtils.defaultStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    @PostMapping
    public Result insertDish(@RequestBody Dish_public dishPublic){
        log.info("新增菜品 : {}",dishPublic);
        service.insertDish(dishPublic);
        return Result.success();
    }

    @PostMapping("/status/{status}")    // todo 不知道为什么前端传递的status永远都是0
    public Result setStatus( Long id){
//        log.info("修改id为: {} 的菜品状态为 {}",id,status.equals(defaultStatus.ONE) ? "启售" : "停售");
        log.info("修改id为: {} 的菜品状态",id);
        service.setDishStatus(id);
        return Result.success();
    }
    @DeleteMapping
    public Result delDish(Long[] ids){
        log.info("删除id为 : {} 的菜品", Arrays.toString(ids));
        service.deleteDish(ids);
        return Result.success();
    }
    @GetMapping("/list")
    private Result selectCategoryIdGetDish(Long categoryId){
        log.info("查询分类id为: {}的菜品数据",categoryId);
        List<Dish> list = service.selectCategoryId(categoryId);
        return Result.success(list);
    }
}
