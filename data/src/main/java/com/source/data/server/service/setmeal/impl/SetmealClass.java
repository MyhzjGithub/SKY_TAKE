package com.source.data.server.service.setmeal.impl;

import com.pojo.Page.Pages;
import com.pojo.Query.SetmealQuery;
import com.pojo.category.Category;
import com.pojo.dish.Dish;
import com.pojo.setmeal.WEBsetmeal.*;
import com.pojo.setmeal.setmeal;
import com.pojo.setmeal.setmealDish;
import com.source.data.server.dao.setmeal.SetmealMapper;
import com.source.data.server.service.category.CategoryService;
import com.source.data.server.service.dish.DishService;
import com.source.data.server.service.setmeal.SetmealDishService;
import com.source.data.server.service.setmeal.SetmealService;
import com.utils.StatusUtils.DefaultStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealClass implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealMapper mapper;
    @Autowired
    private DishService dishService;

    @Override
    public Pages<SetmealPage> Page(SetmealQuery query) {
        Integer startPage = com.utils.PageUtils.startPage.getStartPage(query.getPage(), query.getPageSize());   // 起始页码
        query.setPage(startPage);
        Integer total = mapper.getCount();
        List<SetmealPage> list = mapper.Page(query);
        list.forEach(setmealPage -> {
            Category category = categoryService.getCategoryId(setmealPage.getCategoryId());
            setmealPage.setCategoryName(category.getName());
        });
        return new Pages<>(total, list);
    }

    @Override
    @Transactional
    public void insertSetmeal(Setmeal_Insert data) {
        List<setmealDish> dishes = data.getSetmealDishes(); // 套餐菜品关系数据
        setmeal setmeal = new setmeal();
        BeanUtils.copyProperties(data, setmeal);
        setmeal.setStatus(DefaultStatus.ZERO); // 默认禁用
        mapper.insertSetmeal(setmeal); // 新增后把新增的id返回到中
        dishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmeal.getId());  // 添加新增的套餐id
        });
        setmealDishService.insertSetmealANDdish(dishes);
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        mapper.updateStatus(status,id, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            // 根据id删除套餐
            mapper.deleteId(id);
            // 根据id删除套餐菜品关系表
            setmealDishService.deleteSetmealId(id);
        }
    }

    @Override
    public Setmeal_Select selectID(Long id) {
        setmeal setmeal = mapper.SelectId(id);
        List<setmealDish> setmealDishes = setmealDishService.selectSetmealID(id);
        Setmeal_Select setmealSelect = new Setmeal_Select();
        BeanUtils.copyProperties(setmeal, setmealSelect);
        setmealSelect.setSetmealDishes(setmealDishes);
        return setmealSelect;
    }

    @Override
    @Transactional
    public void updateSetmeal(Setmeal_update setmealUpdate) {
        setmeal setmeal = new setmeal();
        BeanUtils.copyProperties(setmealUpdate, setmeal);
//        categoryService
        List<setmealDish> setmealDishes = setmealUpdate.getSetmealDishes(); // 获取套餐菜品数据
        mapper.updateSetmeal(setmeal);
        setmealDishes.forEach(s->s.setSetmealId(setmeal.getId()));
        setmealDishService.updateSetmealDish(setmealDishes);
    }

    @Override
    public List<setmeal> selectCategoryId(Long categoryId) {
        return mapper.selectCategoryId(categoryId,DefaultStatus.ONE);
    }

    @Override
    public List<Setmeal_data> selectDishID(Long setmealId) {
        // 先获取套餐中的菜品id
        List<Long> list = setmealDishService.getDishID(setmealId);

        // 根据菜品id查询菜品表获取对应的菜品信息
        List<Dish> dishList  = dishService.getDishId(list);

        // 进行转换 变为需要的集合
        List<Setmeal_data> collect = dishList.stream().map(dish -> {
            Setmeal_data setmealData = new Setmeal_data();
            setmealData.setName(dish.getName());
            setmealData.setCopies(1);   // TODO: 2023/11/2 菜品份数需要从套餐菜品关系表中获取 由于都是1 则直接填写了
            setmealData.setDescription(dish.getDescription());
            setmealData.setImage(dish.getImage());
            return setmealData;
        }).collect(Collectors.toList());
        
        return collect;
    }

    @Override
    public setmeal getSetmealID(Long setmealId) {
        return mapper.SelectId(setmealId);
    }
}
