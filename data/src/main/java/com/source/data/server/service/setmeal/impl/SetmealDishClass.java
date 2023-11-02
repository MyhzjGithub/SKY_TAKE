package com.source.data.server.service.setmeal.impl;

import com.pojo.setmeal.setmealDish;
import com.source.data.server.dao.setmeal.SetmealDishMapper;
import com.source.data.server.service.setmeal.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishClass implements SetmealDishService {
    @Autowired
    private SetmealDishMapper mapper;

    @Override
    public void insertSetmealANDdish(List<setmealDish> dishes) {
        mapper.insert(dishes);
    }

    @Override
    public void deleteSetmealId(Long setmealId) {
        mapper.deleteSetmealId(setmealId);
    }

    @Override
    public List<setmealDish> selectSetmealID(Long setmealID) {
        return mapper.selectSetmealID(setmealID);
    }

    @Override
    public void updateSetmealDish(List<setmealDish> setmealDishes) {
        for (setmealDish setmealDish : setmealDishes) {
            mapper.deleteSetmealId(setmealDish.getSetmealId());     // 删除当前套餐关联的菜品
        }
        mapper.insert(setmealDishes);   // 在批量添加菜品
    }
}
