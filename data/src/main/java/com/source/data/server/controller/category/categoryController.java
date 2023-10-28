package com.source.data.server.controller.category;

import com.pojo.Page.Pages;
import com.pojo.Query.categoryQuery;
import com.pojo.RESULT.Result;
import com.pojo.category.WEBcategory.Catinsert;
import com.pojo.category.Category;
import com.source.data.server.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 分类模块管理
 */
@RestController
@Slf4j
@RequestMapping("/admin/category")
public class categoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/page")
    public Result page(categoryQuery query){
        log.info("分页查询 : {}",query);
        Pages<Category> pages = service.Pages(query);
        return Result.success(pages);
    }

    @PostMapping
    public Result insert(@RequestBody Catinsert catinsert){
        log.info("新增分类 : {}",catinsert);
        service.insertCategory(catinsert);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result setStatus(@PathVariable("status") Integer status , Long id){
        log.info("修改分类状态 : {}",id);
        service.updateCategoryStatus(status,id);
        return Result.success();
    }

    @DeleteMapping
    public Result delCategory(Long id){
        log.info("删除id为 : {} 的分类",id);
        service.deleteCategory(id);
        return Result.success();
    }

    @PutMapping
    public Result updateCategory(@RequestBody Catinsert catinsert){
        log.info("修改分类 新内容为: {}",catinsert);
        service.updateCategory(catinsert);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectCategory(Integer type){
        log.info("查询类型为 : {} 的分类",type == 1 ? "菜品" : "套餐");
        List<Category> list = service.selectCategory(type);
        return Result.success(list);
    }
}
