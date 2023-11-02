package com.source.data.server.service.setmeal.impl;

import com.pojo.Page.Pages;
import com.pojo.Query.setmealQuery;
import com.pojo.category.Category;
import com.pojo.setmeal.WEBsetmeal.SetmealPage;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Insert;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Select;
import com.pojo.setmeal.WEBsetmeal.Setmeal_update;
import com.pojo.setmeal.setmeal;
import com.pojo.setmeal.setmealDish;
import com.source.data.server.dao.setmeal.SetmealMapper;
import com.source.data.server.service.category.CategoryService;
import com.source.data.server.service.setmeal.SetmealDishService;
import com.source.data.server.service.setmeal.SetmealService;
import com.utils.StatusUtils.DefaultStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetmealClass implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealMapper mapper;

    @Override
    public Pages<SetmealPage> Page(setmealQuery query) {
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
}
