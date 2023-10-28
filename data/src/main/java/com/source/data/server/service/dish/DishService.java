package com.source.data.server.service.dish;


import com.pojo.Page.Pages;
import com.pojo.Query.dishQuery;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;

public interface DishService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    Pages<Dish_page> Pages(dishQuery page);

    /**
     * 根据id查询菜品详情信息
     * @param id
     * @return
     */
    Dish_public getDishId(Long id);

    /**
     * 修改数据
     * @param dishPublic
     */
    void updateDish(Dish_public dishPublic);
}
