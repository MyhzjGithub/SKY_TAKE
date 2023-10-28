package com.source.data.server.service.dish;

import com.pojo.dish.DishFlavor;

import java.util.List;

public interface DishFlavorService {
    /**
     * 根据菜品id查询对应的口味信息
     * @return
     */
    List<DishFlavor> getDishId(Long id);

    /**
     * 修改口味信息
     * @param flavors
     */
    void updateDishFlavor(List<DishFlavor> flavors);
}
