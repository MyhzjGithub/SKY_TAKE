package com.source.data.server.client_controller.setmeall;

import com.pojo.RESULT.Result;
import com.pojo.setmeal.WEBsetmeal.Setmeal_data;
import com.pojo.setmeal.setmeal;
import com.source.data.server.service.setmeal.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("client-SetmealController")
@RequestMapping("/user/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService service;

    @GetMapping("/list")
    @Cacheable(value = "client-Setmeal" , key = "#categoryId")
    public Result getSetmealList(Long categoryId){
        log.info("查询id为: {} 的套餐",categoryId);
        List<setmeal> setmeals = service.selectCategoryId(categoryId);
        return Result.success(setmeals);
    }

    @GetMapping("/dish/{id}")
    @Cacheable(value = "client-Setmeal" , key = "#id")
    public Result getDishId(@PathVariable("id") Long id){
        log.info("查询套餐id为: {} 的菜品数据",id);
        List<Setmeal_data> list = service.selectDishID(id);
        return Result.success(list);
    }
}
