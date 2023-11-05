package com.source.data.server.client_controller.category;

import com.pojo.RESULT.Result;
import com.pojo.category.Category;
import com.pojo.category.Clientcategory.CategoryType;
import com.source.data.server.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * app端 : 分类模块
 */
@RestController("client-CategoryController")
@Slf4j
@RequestMapping("/user/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public Result list( CategoryType type){   // json格式
        log.info("查询类型为 : {}",type.getType());    // 可能会不带type进来 默认查询全部
        List<Category> categoryList = service.selectCategoryType(type.getType());
        return Result.success(categoryList);
    }
}
