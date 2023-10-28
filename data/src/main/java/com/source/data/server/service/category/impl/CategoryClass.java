package com.source.data.server.service.category.impl;

import com.pojo.Page.Pages;
import com.pojo.Query.categoryQuery;
import com.pojo.category.WEBcategory.Catinsert;
import com.pojo.category.Category;
import com.source.data.server.dao.category.categoryMapper;
import com.source.data.server.service.category.CategoryService;

import com.utils.PageUtils.startPage;
import com.utils.StatusUtils.defaultStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * category 分类业务逻辑层
 */
@Service
public class CategoryClass implements CategoryService {
    @Autowired
    private categoryMapper mapper;

    @Override
    public Pages<Category> Pages(categoryQuery query) {
        Integer total = mapper.getCategoryCount();
        Integer Page = startPage.getStartPage(query.getPage(), query.getPageSize());   // 起始页码
        query.setPage(Page);
        List<Category> list = mapper.Pages(query);
        return new Pages<Category>(total, list);
    }

    @Override
    public void insertCategory(Catinsert catinsert) {
        Category category = new Category();
        BeanUtils.copyProperties(catinsert, category);
        category.setStatus(defaultStatus.ZERO);  // 默认禁用
        mapper.insertCategory(category);
    }

    @Override
    public void updateCategoryStatus(Integer status, Long id) {
        mapper.setCategoryStatus(status,id);
    }

    @Override
    public void deleteCategory(Long id) {
        mapper.deleteCategory(id);
    }

    @Override
    public List<Category> selectCategory(Integer type) {
        List<Category> list = mapper.selectCategory(type);
        return list;
    }

    @Override
    public void updateCategory(Catinsert catinsert) {
        Category category = new Category();
        BeanUtils.copyProperties(catinsert, category);
        mapper.updateCategory(category);
    }

    @Override
    public Category getCategoryId(Long categoryId) {
        return mapper.getCategoryId(categoryId);
    }
}
