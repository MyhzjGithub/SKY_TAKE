package com.source.data.server.service.dish;


import com.pojo.Page.Pages;
import com.pojo.Query.DishQuery;
import com.pojo.dish.Dish;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;

import java.util.List;

public interface DishService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    Pages<Dish_page> Pages(DishQuery page);

    /**
     * 根据id查询菜品详情信息
     * @param id
     * @return
     */
    Dish_public getDishId(Long id);

    /**
     * 根据id查询菜品详情信息
     * @param id
     * @return
     */
    Dish getDeclaredDishId(Long id);

    /**
     * 根据id查询菜品详情信息
     * @param dishId
     * @return
     */
    List<Dish> getDishId(List<Long> dishId);

    /**
     * 修改数据
     * @param dishPublic
     */
    void updateDish(Dish_public dishPublic);

    /**
     * 新增菜品
     */
    void insertDish(Dish_public dishPublic);

    /**
     * 修改菜品状态
     * @param id
     */
    void setDishStatus( Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteDish(Long[] ids);

    /**
     * 根据分类id查询菜品信息并且附带口味
     * @param categoryId
     * @return
     */
    List<Dish_public> selectCategoryId(Long categoryId);

    /**
     * 通过菜品名称查询菜品信息
     * @param name
     * @return
     */
    Dish getDishName(String name);

    /**
     * 根据分类id查询菜品信息
     * @param categoryId
     * @return
     */
    List<Dish> selectCategoryIds(Long categoryId);

    /**
     * 获取指定状态的菜品数量
     * @param status
     * @return
     */
    Integer selectStatus(Integer status);
}
