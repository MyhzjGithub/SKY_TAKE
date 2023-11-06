package com.source.data.server.service.dish;

import com.pojo.dish.DishFlavor;

import java.util.List;

public interface DishFlavorService {
    /**
     * 根据菜品id查询对应的口味信息
     * @return
     */
    List<DishFlavor> getDishId(Long dishId);

    /**
     * 修改口味信息
     * @param flavors
     */
    void insertDishFlavor(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除口味信息
     * @param id
     */
    void deleteDishId(Long id);
}
