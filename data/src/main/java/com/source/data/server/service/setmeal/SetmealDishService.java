package com.source.data.server.service.setmeal;

import com.pojo.setmeal.setmealDish;

import java.util.List;

public interface SetmealDishService {
    /**
     * 新增套餐菜品关系
     * @param dishes
     */
    void insertSetmealANDdish(List<setmealDish> dishes);

    /**
     * 根据套餐id删除数据
     * @param setmealId
     */
    void deleteSetmealId(Long setmealId);

    /**
     * 根据菜品id查询关系表数据
     * @param setmealID
     * @return
     */
    List<setmealDish> selectSetmealID(Long setmealID);

    /**
     * 修改套餐菜品关系表
     * @param setmealDishes
     */
    void updateSetmealDish(List<setmealDish> setmealDishes);

    /**
     * 根据套餐id返回套餐对应的菜品id
     * @param setmealId
     * @return 菜品的id
     */
    List<Long> getDishID(Long setmealId);
}
