package com.source.data.server.service.dish.impl;

import com.pojo.Page.Pages;
import com.pojo.Query.dishQuery;
import com.pojo.category.Category;
import com.pojo.dish.Dish;
import com.pojo.dish.DishFlavor;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;
import com.source.data.server.dao.dish.dishMapper;
import com.source.data.server.service.category.CategoryService;
import com.source.data.server.service.dish.DishFlavorService;
import com.source.data.server.service.dish.DishService;
import com.utils.PageUtils.startPage;
import com.utils.StatusUtils.defaultStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜品业务逻辑处理层
 */
@Service
public class DishClass implements DishService {
    @Autowired
    private dishMapper mapper;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Pages<Dish_page> Pages(dishQuery page) {
        Integer start = startPage.getStartPage(page.getPage(), page.getPageSize()); // 起始页码
        page.setPage(start);
        Integer total = mapper.getDishCount();

        List<Dish_page> list = mapper.Pages(page);
        list.forEach(listPage -> {
            Category category = categoryService.getCategoryId(listPage.getCategoryId());    // 每个菜品都去访问一下分类表 获取菜品的分类名称
            listPage.setCategoryName(category.getName());
        });
        return new Pages<>(total, list);
    }

    @Override
    @Transactional  // 事务注解
    public Dish_public getDishId(Long id) {
        Dish dish = mapper.getDishID(id);   // 根据id查询获取菜品信息
        Category category = categoryService.getCategoryId(dish.getCategoryId());    // 根据分类id查询分类信息
        List<DishFlavor> dishFlavor = dishFlavorService.getDishId(dish.getId());   // 根据菜品id获取对应的口味
        Dish_public dishPublic = new Dish_public();
        BeanUtils.copyProperties(dish, dishPublic);
        dishPublic.setCategoryName(category.getName());
        dishPublic.setFlavors(dishFlavor);
        return dishPublic;
    }

    @Override
    @Transactional
    public void updateDish(Dish_public dishPublic) {
        mapper.updateDish(dishPublic);
        List<DishFlavor> flavors = dishPublic.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishPublic.getId());
        }
        dishFlavorService.insertDishFlavor(flavors);
    }

    @Override
    @Transactional
    public void insertDish(Dish_public dishPublic) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishPublic, dish);
        dish.setStatus(defaultStatus.ZERO); // 默认为禁用
        mapper.insertDish(dish);
        List<DishFlavor> flavors = dishPublic.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dish.getId());
        }
        dishFlavorService.insertDishFlavor(flavors);
    }

    @Override
    @Transactional
    public void setDishStatus(Long id) {
        Dish dish = mapper.getDishID(id);   // 先根据当前id获取菜品信息
        if (dish.getStatus().equals(defaultStatus.ZERO)){   // 如果是 等于 0 修改为 1
            mapper.setDishStatus(defaultStatus.ONE, dish.getId());
        }else {
            mapper.setDishStatus(defaultStatus.ZERO, dish.getId());
        }
    }

    @Override
    @Transactional
    public void deleteDish(Long[] ids) {
        // 删除菜品信息
        for (Long id : ids) {
            mapper.deleteId(id);    // 删除菜品口味信息
            dishFlavorService.deleteDishId(id); // 每一个待删除的菜品id
        }
    }

    @Override
    public List<Dish> selectCategoryId(Long categoryId) {
        List<Dish> list = mapper.getCategoryId(categoryId);
        return list;
    }
}
