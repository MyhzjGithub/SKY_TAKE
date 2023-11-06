package com.source.data.server.service.dish.impl;

import com.pojo.dish.DishFlavor;
import com.source.data.server.dao.dish.DishFlavorMapper;
import com.source.data.server.service.dish.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜品口味业务逻辑
 */
@Service
public class DishFlavorClass implements DishFlavorService {
    @Autowired
    private DishFlavorMapper mapper;

    @Override
    public List<DishFlavor> getDishId(Long dishId) {
        return mapper.getDishId(dishId);
    }

    /**
     * 由于口味比较复杂 本次使用 直接删除口味数据 然后新增新的口味数据
     * @param flavors
     */
    @Override
    @Transactional
    public void insertDishFlavor(List<DishFlavor> flavors) {
        if (flavors == null || flavors.size() == 0){   // 没有数据
            return;
        }
        // 删除原始菜品的口味数据
        mapper.deleteDishFlavorId(flavors.get(0).getDishId());
        // 新增菜品的口味数据
        mapper.insertDishFlavor(flavors);
    }

    @Override
    public void deleteDishId(Long id) {
        mapper.deleteDishFlavorId(id);
    }
}
