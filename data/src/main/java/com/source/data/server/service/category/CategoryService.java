package com.source.data.server.service.category;

import com.pojo.Page.Pages;
import com.pojo.Query.categoryQuery;
import com.pojo.category.WEBcategory.Catinsert;
import com.pojo.category.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 分页查询
     * @param query
     * @return
     */
    Pages<Category> Pages(categoryQuery query);

    /**
     * 新增分类
     * @param catinsert
     */
    void insertCategory(Catinsert catinsert);

    /**
     * 修改菜品状态
     * @param status
     * @param id
     */
    void updateCategoryStatus(Integer status, Long id);

    /**
     * 删除菜品分类
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> selectCategory(Integer type);

    /**
     * 修改分类
     * @param catinsert
     */
    void updateCategory(Catinsert catinsert);

    /**
     * 根据id查询分类信息
     * @return
     */
    Category getCategoryId(Long categoryId);
}
