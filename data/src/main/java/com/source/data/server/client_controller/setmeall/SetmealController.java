package com.source.data.server.client_controller.setmeall;

import com.pojo.RESULT.Result;
import com.pojo.setmeal.setmeal;
import com.source.data.server.service.setmeal.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("client-SetmealController")
@RequestMapping("/user/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService service;

    @GetMapping("/list")
    public Result getSetmealList(Long categoryId){
        log.info("查询id为: {} 的套餐",categoryId);
        List<setmeal> setmeals = service.selectCategoryId(categoryId);
        return Result.success(setmeals);
    }
}
